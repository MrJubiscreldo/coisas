import java.util.Random;

class Main {
  public static void main(String[] args) {
      int seed = 1;
      Random random = new Random(seed);
      int tamanhoVetor = 20000;
      int chaves[] = new int[tamanhoVetor];
      for (int i=0;i<tamanhoVetor;i++) {
         chaves[i] = random.nextInt(1000000);
      }

      int tamanhoTabela = 10000;
      HashResto tabelaResto = new HashResto(tamanhoTabela);
      HashMult tabelaMult = new HashMult(tamanhoTabela);
      HashDobra tabelaDobra= new HashDobra(tamanhoTabela);

      // Resto inserir
      long startTime = System.currentTimeMillis();
      for (int i=0;i<tamanhoVetor;i++) {
            tabelaResto.inserir(chaves[i]);
      }
      long endTime = System.currentTimeMillis();
      System.out.print(endTime-startTime + ",");

      // Mult inserir
      startTime = System.currentTimeMillis();
      for (int i=0;i<tamanhoVetor;i++) {
            tabelaMult.inserir(chaves[i]);
      }
      endTime = System.currentTimeMillis();
      System.out.print(endTime-startTime + ",");

      // Dobra inserir
      startTime = System.currentTimeMillis();
      for (int i=0;i<tamanhoVetor;i++) {
              tabelaDobra.inserir(chaves[i]);
      }
      endTime = System.currentTimeMillis();
      System.out.print(endTime-startTime + ",");

      int comparacoes1 = 0;
      int comparacoes2 = 0;
      int comparacoes3 = 0;

      // Resto buscar
      startTime = System.currentTimeMillis();
      for(int j=0;j<5;j++) {
        for(int i=0;i<tamanhoVetor;i++) {
            tabelaResto.buscar(chaves[i]);
        }
      }
      endTime = System.currentTimeMillis();
      System.out.print((endTime-startTime)/5 + ",");
      // comparacao
      for(int i=0;i<tamanhoVetor;i++) {
            comparacoes1 += tabelaResto.buscar(chaves[i]);
      }

      // Mult buscar
      startTime = System.currentTimeMillis();
      for(int j=0;j<5;j++) {
          for(int i=0;i<tamanhoVetor;i++) {
              tabelaMult.buscar(chaves[i]);
          }
      }
      endTime = System.currentTimeMillis();
      System.out.print((endTime-startTime)/5 + ",");
      // comparacao
      for(int i=0;i<tamanhoVetor;i++) {
            comparacoes2 += tabelaMult.buscar(chaves[i]);
      }
      
    
      // Dobra buscar
      startTime = System.currentTimeMillis();
      for(int j=0;j<5;j++) {
          for(int i=0;i<tamanhoVetor;i++) {
              tabelaDobra.buscar(chaves[i]);
          }
      }
      endTime = System.currentTimeMillis();
      System.out.print((endTime-startTime)/5 + ",");
      // comparacao
      for(int i=0;i<tamanhoVetor;i++) {
            comparacoes3 += tabelaDobra.buscar(chaves[i]);
      }

      System.out.print(comparacoes1 + "," + comparacoes2 + "," + comparacoes3 + ",");
      System.out.print(tabelaResto.nColisoes() + "," + tabelaMult.nColisoes() + "," + tabelaDobra.nColisoes());
  }
}