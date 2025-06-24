import os
import numpy as np
import pandas as pd
import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
import matplotlib.pyplot as plt
from tqdm import tqdm

# ====================== Configuration ======================
class Config:
    DATA_DIR = "../battery_data/"  # Directory containing 632 CSV files
    NUM_CLIENTS = 632           # Total number of clients (batteries)
    ROUNDS = 200                # Total number of rounds
    CLIENTS_PER_ROUND = 50      # Clients selected each round
    LOCAL_EPOCHS = 2            # Training epochs per client
    BATCH_SIZE = 32             # Batch size for client training
    LEARNING_RATE = 0.01       # Learning rate for client optimization
    MOMENTUM = 0.9              # Momentum factor for the SGD optimizer
    TEST_RATIO = 0.2            # Ratio of test data per client
    SEED = 100                  # Random seed for reproducibility
    PLOT_STYLE = 'seaborn-v0_8' # Plot style  
# ====================== Data Preparation ======================
class BatteryDataset(Dataset):
    def __init__(self, file_path, normalize_stats=None):
        df = pd.read_csv(file_path)
        df.drop(columns=['timestamp'], inplace=True)
        
        # Split into train/test
        if normalize_stats:
            self.X = (df[['v', 'c', 't']].values - normalize_stats['mean'][:3]) / normalize_stats['std'][:3]
            self.y = (df['soc'].values - normalize_stats['mean'][3]) / normalize_stats['std'][3]
        else:
            self.X = df[['v', 'c', 't']].values
            self.y = df['soc'].values
        
        self.X = self.X.astype(np.float32)
        self.y = self.y.astype(np.float32).reshape(-1, 1)
    
    def __len__(self):
        return len(self.y)
    
    def __getitem__(self, idx):
        return self.X[idx], self.y[idx]

def compute_global_stats(file_paths):
    all_data = []
    for f in tqdm(file_paths, desc="Computing global stats"):
        df = pd.read_csv(f)
        df.drop(columns=['timestamp'], inplace=True)
        all_data.append(df[['v', 'c', 't', 'soc']])
    
    full_df = pd.concat(all_data)
    return {
        'mean': full_df.mean().values,
        'std': full_df.std().values
    }

def prepare_client_loaders(file_paths, global_stats, test_ratio=0.2):
    client_loaders = {'train': [], 'test': []}
    for f in tqdm(file_paths, desc="Preparing client data"):
        full_dataset = BatteryDataset(f, global_stats)
        
        # Split into train/test
        test_size = int(len(full_dataset) * test_ratio)
        train_size = len(full_dataset) - test_size
        train_dataset, test_dataset = torch.utils.data.random_split(
            full_dataset, [train_size, test_size]
        )
        
        client_loaders['train'].append(DataLoader(
            train_dataset, batch_size=Config.BATCH_SIZE, shuffle=True
        ))
        client_loaders['test'].append(DataLoader(
            test_dataset, batch_size=Config.BATCH_SIZE, shuffle=False
        ))
    
    return client_loaders

# ====================== Model Definition ======================
class SOCPredictor(nn.Module):
    def __init__(self, input_size=3):
        super().__init__()
        self.net = nn.Sequential(
            nn.Linear(input_size, 64),
            nn.ReLU(),
            nn.Linear(64, 32),
            nn.ReLU(),
            nn.Linear(32, 16),
            nn.ReLU(),
            nn.Linear(16, 1)
        )
    
    def forward(self, x):
        return self.net(x)

# ====================== Federated Utilities ======================
def client_update(model, train_loader, epochs=1):
    model.train()
    criterion = nn.MSELoss()
    
    # SGD
    optimizer = optim.SGD(
        model.parameters(), 
        lr=Config.LEARNING_RATE,
        momentum=Config.MOMENTUM
    )
    
    for _ in range(epochs):
        for X, y in train_loader:
            optimizer.zero_grad()
            outputs = model(X)
            loss = criterion(outputs, y)
            loss.backward()
            optimizer.step()
    
    return model.state_dict()

def federated_averaging(global_model, client_weights):
    global_dict = global_model.state_dict()
    for key in global_dict:
        global_dict[key] = torch.stack(
            [client_weights[i][key] for i in range(len(client_weights))], 0
        ).mean(0)
    global_model.load_state_dict(global_dict)
    return global_model

def evaluate(model, test_loader):
    model.eval()
    criterion = nn.MSELoss()
    total_loss = 0
    with torch.no_grad():
        for X, y in test_loader:
            outputs = model(X)
            total_loss += criterion(outputs, y).item()
    return total_loss / len(test_loader)

# ====================== Main Training Loop ======================
def main():
    # Set seeds for reproducibility
    torch.manual_seed(Config.SEED)
    np.random.seed(Config.SEED)
    
    # Load all client files
    client_files = [os.path.join(Config.DATA_DIR, f) 
                   for f in os.listdir(Config.DATA_DIR) 
                   if f.endswith('.csv')][:Config.NUM_CLIENTS]
    
    if not client_files:
        raise ValueError(f"No CSV files found in {Config.DATA_DIR}")
    
    print(f"Found {len(client_files)} client data files")
    
    # Compute global normalization statistics
    global_stats = compute_global_stats(client_files)

    # Prepare client data loaders
    client_loaders = prepare_client_loaders(
        client_files,
        global_stats,
        test_ratio=Config.TEST_RATIO
    )
    
    # Initialize global model
    global_model = SOCPredictor()
    best_loss = float('inf')
    
    round_numbers = []  
    round_losses = []   

    # Federated training
    round_pbar = tqdm(range(Config.ROUNDS), desc="Federated Rounds")
    
    for round in round_pbar:
        selected = np.random.choice(
            len(client_files), 
            Config.CLIENTS_PER_ROUND, 
            replace=False
        )
        
        client_weights = []
        round_train_loss = 0
        
        # Client training
        client_pbar = tqdm(selected, desc=f"Clients (Round {round+1})", leave=False)
        for client_idx in client_pbar:
            # Create local model with current global weights
            local_model = SOCPredictor()
            local_model.load_state_dict(global_model.state_dict())
            
            # Train and get updated weights
            weights = client_update(
                local_model,
                client_loaders['train'][client_idx],
                epochs=Config.LOCAL_EPOCHS
            )
            client_weights.append(weights)
            
            # Calculate training loss for the client
            local_model.eval()
            train_loss = evaluate(local_model, client_loaders['train'][client_idx])
            round_train_loss += train_loss
            
            # Update client progress
            client_pbar.set_postfix({'Client': client_idx, 'Loss': f'{train_loss:.4f}'})
        
        # Aggregate updates
        global_model = federated_averaging(global_model, client_weights)
        
        # Calculate average training loss across selected clients
        avg_train_loss = round_train_loss / len(selected)
        
        # Calculate test loss across all clients
        test_loss = 0
        global_model.eval()
        for test_loader in client_loaders['test']:
            test_loss += evaluate(global_model, test_loader)
        avg_test_loss = test_loss / len(client_loaders['test'])
        
        # Store metrics for plotting
        round_losses.append(avg_test_loss)
        round_numbers.append(round + 1)
        
        # Update round progress
        round_pbar.set_postfix({
            'Train Loss': f'{avg_train_loss:.6f}',
            'Test Loss': f'{avg_test_loss:.6f}'
        })
        print(f"\nRound {round+1}/{Config.ROUNDS}: "
              f"Train Loss = {avg_train_loss:.6f}, "
              f"Test Loss = {avg_test_loss:.6f}")
        
        # Save best model
        if avg_test_loss < best_loss:
            best_loss = avg_test_loss
            torch.save({
            'model_state_dict': global_model.state_dict(),
            'best_loss': best_loss,
            'round_losses': round_losses}, "sgd_metrics.pth")
            print(f"New best model saved with test loss: {best_loss:.6f}")
    
    # Plot
    plt.style.use(Config.PLOT_STYLE)
    plt.figure(figsize=(10, 5))
    plt.plot(round_numbers, round_losses, 'b-o', linewidth=2, markersize=8)
    plt.title('Federated Learning Progress', fontsize=14)
    plt.xlabel('Communication Round', fontsize=12)
    plt.ylabel('Average Test Loss (MSE)', fontsize=12)
    plt.grid(True)
    plt.tight_layout()
    plt.savefig(f"sgd_fl_{round+1}.png", dpi=300)
    plt.close()

    # Output
    print("Training complete! Best model saved to 'sgd_metrics.pth'")
    print(f"Final Test Loss: {round_losses[-1]:.6f}")

if __name__ == "__main__":
    main()
