public class SortsTrocas {
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
        int i=0;
        int j=1;
        int troca = 0;
        for (; i<n; i++) {
            for(; j<(n-i); j++) {
                if (vetor[j - 1] > vetor[j]) {
                    troca++;
                    temp = vetor[j-1];
                    vetor[j-1] = vetor[j];
                    vetor[j] = temp;   
                }       
            }
        }
        return troca;
    }

    public int mergeSort(int[] vetor) {
        return mergeSort(vetor, 0, tamanho(vetor)-1);
    }
    
    private int mergeSort(int[] vetor, int inicio, int fim) {
        int trocas = 0;
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            trocas += mergeSort(vetor, inicio, meio);
            trocas += mergeSort(vetor, meio+1, fim);
            trocas += merge(vetor, inicio, meio, fim);
        }
        return trocas;
    }

    private int merge(int[] vetor, int inicio, int meio, int fim) {
        int trocas = 0;
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
            trocas++;
            vetor[i] = temp[i-inicio];
        }
        return trocas;
    }

    public int quick(int[] vetor) {
        return quick(vetor, 0, tamanho(vetor)-1);
    }

    private int quick(int x[], int inferior, int superior) {
        int trocas = 0;
        if (inferior >= superior) // o array está ordenado
            return trocas;
        // encontra a posição correta para o pivo
        // o pivo pode ser considerado, por exemplo, o primeiro elemento do array
        int[] resultado = particiona(x, inferior, superior);
        // executa o mesmo proced. sobre as subarrays à esquerda e à direita do pivo
        trocas += quick(x, inferior, resultado[0] -1);
        trocas += quick(x, resultado[0] + 1, superior);

        return trocas+resultado[1];
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