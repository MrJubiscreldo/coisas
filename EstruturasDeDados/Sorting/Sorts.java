public class Sorts {
    private int tamanho(int[] vetor) {
        int tamanho = 0;
        for (int i : vetor) {
            tamanho++;
        }
        return tamanho;
    }
    
    public void bubbleSort(int[] vetor) {
        int n = tamanho(vetor);
        int temp = 0;
        for (int i=0; i<n; i++) {
            for(int j=1; j<(n-i); j++) {
                if (vetor[j - 1] > vetor[j]) {
                    temp = vetor[j-1];
                    vetor[j-1] = vetor[j];
                    vetor[j] = temp;   
                }       
            }
        }
    }

    public void mergeSort(int[] vetor) {
        mergeSort(vetor, 0, tamanho(vetor)-1);
    }
    
    private void mergeSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio+1, fim);
            
            merge(vetor, inicio, meio, fim);
        }
    }

    private void merge(int[] vetor, int inicio, int meio, int fim) {
        int[] temp = new int[fim - inicio + 1];

        int i = inicio;
        int j = meio + 1;
        int k = 0;
        
        while (i <= meio && j <= fim) {
            if (vetor[i] <= vetor[j]) {
                temp[k++] = vetor[i++];
            } else {
                temp[k++] = vetor[j++];
            }
        }

        while (i <= meio) {
            temp[k++] = vetor[i++];
        }

        while (j <= fim) {
            temp[k++] = vetor[j++];
        }

        for (i=inicio; i<=fim; i++) {
            vetor[i] = temp[i-inicio];
        }
    }

    public void quick(int[] vetor) {
        quick(vetor, 0, tamanho(vetor)-1);
    }

    private void quick(int x[], int inferior, int superior) {
        if (inferior >= superior) // o array está ordenado
            return;
        // encontra a posição correta para o pivo
        // o pivo pode ser considerado, por exemplo, o primeiro elemento do array
        int pivo = particiona(x, inferior, superior);
        // executa o mesmo proced. sobre as subarrays à esquerda e à direita do pivo
        quick(x, inferior, pivo -1);
        quick(x, pivo + 1,superior);
    }

    private int particiona(int x[], int inferior, int superior) {
        int pivo = x[inferior];
        int inf = inferior;
        int sup = superior;
        
        while (inf < sup) {
            while (x[inf] <= pivo && inf < sup) {
                    inf++;
            }
            while (x[sup] > pivo && inf < sup) {
                    sup--;
            }
            if (inf < sup) {
                int temp = x[inf];
                x[inf] = x[sup];
                x[sup] = temp;
            }
        }
        
        x[inferior] = x[sup];
        x[sup] = pivo;
        return sup;
    }
}