public class HashMult extends TabelaHash {
    public HashMult(int tamanho) {
        super(tamanho);
    }

    public int hash(int chave) {
        return (int) (super.tamanho * ((chave * 0.357840) % 1));
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