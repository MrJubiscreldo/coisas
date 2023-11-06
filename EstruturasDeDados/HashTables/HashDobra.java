public class HashDobra extends TabelaHash {
    public HashDobra(int tamanho) {
        super(tamanho);
    }

    public int hash(int chave) {
        int hash = 0;
        while (chave > 1) {
            hash += chave % super.tamanho;
            chave /= super.tamanho;
        }
        return hash % super.tamanho;
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