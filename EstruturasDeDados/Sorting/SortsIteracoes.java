public class SortsIteracoes {
    private int tamanho(int[] vetor) {
        int tamanho = 0;
        for (int i : vetor) {
            tamanho++;
        }
        return tamanho;
    }
    
    public int bubbleSort(int[] vetor) {
        int n = tamanho(vetor);
        int temp = 0;
        int iteracoes = 0;
        for (int i=0; i<n; i++) {
            for(int j=1; j<(n-i); j++) {
                iteracoes++;
                if (vetor[j - 1] > vetor[j]) {
                    temp = vetor[j-1];
                    vetor[j-1] = vetor[j];
                    vetor[j] = temp;   
                }       
            }
        }
        return iteracoes;
    }

    public int mergeSort(int[] vetor) {
        return mergeSort(vetor, 0, tamanho(vetor)-1);
    }
    
    private int mergeSort(int[] vetor, int inicio, int fim) {
        int iteracoes = 0;
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            iteracoes += mergeSort(vetor, inicio, meio);
            iteracoes += mergeSort(vetor, meio+1, fim);
            merge(vetor, inicio, meio, fim);

            return iteracoes+1;
        }
        return iteracoes+1;
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

    public int quick(int[] vetor) {
        return quick(vetor, 0, tamanho(vetor)-1);
    }

    private int quick(int x[], int inferior, int superior) {
        int iteracoes = 0;
        if (inferior >= superior) // o array está ordenado
            return iteracoes+1;
        // encontra a posição correta para o pivo
        // o pivo pode ser considerado, por exemplo, o primeiro elemento do array
        int[] resultado = particiona(x, inferior, superior);
        // executa o mesmo proced. sobre as subarrays à esquerda e à direita do pivo
        iteracoes += quick(x, inferior, resultado[0] -1);
        iteracoes += quick(x, resultado[0] + 1,superior);

        return iteracoes+1;
    }

    private int[] particiona(int x[], int inferior, int superior) {
        int trocas = 0;
        
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
                trocas++;
                int temp = x[inf];
                x[inf] = x[sup];
                x[sup] = temp;
            }
        }
        trocas++;
        x[inferior] = x[sup];
        x[sup] = pivo;

        int[] resultado = {sup, trocas};
        return resultado;
    }
}