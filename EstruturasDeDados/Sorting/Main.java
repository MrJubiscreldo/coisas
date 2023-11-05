import java.util.Random;
import java.util.Arrays;

class Main {
  public static void main(String[] args) {
        int seed = 1;
        Random random = new Random(seed);
        int n = 50;
        int[] vetor = new int[n];
        for (int i=0;i<n;i++) {
           vetor[i] = random.nextInt(n);
        }
      
        // Sorts sort = new Sorts();
        // int[] temp = vetor;
        // long startTime, endTime;
        // int nRodadas = 50000;
        // startTime = System.nanoTime();
        // for (int i=0;i<nRodadas;i++) {
        //   sort.bubbleSort(temp);
        // }
        // endTime = System.nanoTime();
        // System.out.println("Bubble Sort: " + ((endTime - startTime)/nRodadas) + " ns");
        // temp = vetor;
        // startTime = System.nanoTime();
        // for (int i=0;i<nRodadas;i++) {
        // sort.mergeSort(temp);
        // }
        // endTime = System.nanoTime();
        // System.out.println("Merge Sort: " + ((endTime - startTime)/nRodadas) + " ns");
        // temp = vetor;
        // startTime = System.nanoTime();
        // for (int i=0;i<nRodadas;i++) {
        // sort.quick(temp);
        // }
        // endTime = System.nanoTime();
        // System.out.println("Quick Sort: " + ((endTime - startTime)/nRodadas) + " ns");

        // SortsIteracoes sortIteracoes = new SortsIteracoes();
        // temp = vetor;
        // System.out.println("Bubble Sort: " + sortIteracoes.bubbleSort(temp) + " iterações");
        // temp = vetor;
        // System.out.println("Merge Sort: " + sortIteracoes.mergeSort(temp) + " iterações");
        // temp = vetor;
        // System.out.println("Quick Sort: " + sortIteracoes.quick(temp) + " iterações");

        // SortsTrocas sortTrocas = new SortsTrocas();
        // temp = vetor;
        // System.out.println("Bubble Sort: " + sortTrocas.bubbleSort(temp) + " Trocas");
        // temp = vetor;
        // System.out.println("Merge Sort: " + sortTrocas.mergeSort(temp) + " Trocas");
        // temp = vetor;
        // System.out.println("Quick Sort: " + sortTrocas.quick(temp) + " Trocas");

        Sorts sort = new Sorts();
        int[] temp = vetor;
        
        // sort.bubbleSort(temp);
        // System.out.println("Bubble Sort: " + Arrays.toString(temp));
        // temp = vetor;
        // sort.mergeSort(temp);
        // System.out.println("Merge Sort: " + Arrays.toString(temp));
        // temp = vetor;
        // sort.quick(temp);
        // System.out.println("Quick Sort: " + Arrays.toString(temp));
      
        
        // long startTime, endTime;
        // int nRodadas = 5000;
        // temp = vetor;
        // startTime = System.nanoTime();
        // for (int i=0;i<nRodadas;i++) {
        //   sort.bubbleSort(temp);
        // }
        // endTime = System.nanoTime();
        // System.out.print(((endTime - startTime)/nRodadas) + ",");
        // temp = vetor;
        // startTime = System.nanoTime();
        // for (int i=0;i<nRodadas;i++) {
        // sort.mergeSort(temp);
        // }
        // endTime = System.nanoTime();
        // System.out.print(((endTime - startTime)/nRodadas) + ",");
        // temp = vetor;
        // startTime = System.nanoTime();
        // for (int i=0;i<nRodadas;i++) {
        // sort.quick(temp);
        // }
        // endTime = System.nanoTime();
        // System.out.print(((endTime - startTime)/nRodadas) + ",");

        SortsIteracoes sortIteracoes = new SortsIteracoes();
        temp = vetor;
        System.out.print(sortIteracoes.bubbleSort(temp) + ",");
        temp = vetor;
        System.out.print(sortIteracoes.mergeSort(temp) + ",");
        temp = vetor;
        System.out.print(sortIteracoes.quick(temp) + ",");

        SortsTrocas sortTrocas = new SortsTrocas();
        temp = vetor;
        System.out.print(sortTrocas.bubbleSort(temp) + ",");
        temp = vetor;
        System.out.print(sortTrocas.mergeSort(temp) + ",");
        temp = vetor;
        System.out.print(sortTrocas.quick(temp));
      
  }
}