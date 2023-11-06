public class HashResto extends TabelaHash {
    public HashResto(int tamanho) {
        super(tamanho);
    }
    
    public int hash(int chave) {
        return chave % super.tamanho;
    }

    public void inserir(int chave) {
        super.inserir(hash(chave), chave);
    }
    
    public int buscar(int chave) {
        return super.buscar(hash(chave), chave);
    }

    public int nColisoes() {
        return super.nColisoes();
    }
}