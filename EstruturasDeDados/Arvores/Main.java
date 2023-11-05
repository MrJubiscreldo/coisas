import java.util.Random;

class Main {
  public static void main(String[] args) {
        ArvoreBinaria arvBinaria = new ArvoreBinaria();
        ArvoreAVL arvAVL = new ArvoreAVL();
        Random random = new Random();
        long startTime, stopTime;

        // INSERIR
      
        startTime = System.nanoTime();
        for (int i=0;i<100;i++) {
            arvBinaria.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria inserir 100: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<500;i++) {
            arvBinaria.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria inserir 500: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<1000;i++) {
            arvBinaria.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria inserir 1000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<10000;i++) {
            arvBinaria.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria inserir 10000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<20000;i++) {
            arvBinaria.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria inserir 20000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<100;i++) {
            arvAVL.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL inserir 100: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<500;i++) {
            arvAVL.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL inserir 500: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<1000;i++) {
            arvAVL.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL inserir 1000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<10000;i++) {
            arvAVL.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL inserir 10000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<20000;i++) {
            arvAVL.inserir(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL inserir 20000: " + (stopTime - startTime));

        // BUSCAR

        startTime = System.nanoTime();
        for (int i=0;i<100;i++) {
            arvBinaria.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria buscar 100: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<500;i++) {
            arvBinaria.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria buscar 500: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<1000;i++) {
            arvBinaria.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria buscar 1000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<10000;i++) {
            arvBinaria.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria buscar 10000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<20000;i++) {
            arvBinaria.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria buscar 20000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<100;i++) {
            arvAVL.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL buscar 100: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<500;i++) {
            arvAVL.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL buscar 500: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<1000;i++) {
            arvAVL.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL buscar 1000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<10000;i++) {
            arvAVL.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL buscar 10000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<20000;i++) {
            arvAVL.buscar(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL buscar 20000: " + (stopTime - startTime));

        // REMOVER      

        startTime = System.nanoTime();
        for (int i=0;i<100;i++) {
            arvBinaria.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria remover 100: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<500;i++) {
            arvBinaria.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria remover 500: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<1000;i++) {
            arvBinaria.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria remover 1000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<10000;i++) {
            arvBinaria.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria remover 10000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<20000;i++) {
            arvBinaria.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvBinaria remover 20000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<100;i++) {
            arvAVL.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL remover 100: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<500;i++) {
            arvAVL.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL remover 500: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<1000;i++) {
            arvAVL.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL remover 1000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<10000;i++) {
            arvAVL.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL remover 10000: " + (stopTime - startTime));

        startTime = System.nanoTime();
        for (int i=0;i<20000;i++) {
            arvAVL.remover(random.nextInt());
        }
        stopTime = System.nanoTime();
        System.out.println("arvAVL remover 20000: " + (stopTime - startTime));
    }
}
