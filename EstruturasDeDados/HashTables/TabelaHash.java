class TabelaHash {
    protected int tamanho;
    private Lista tabela[];

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new Lista[tamanho];
        
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new Lista();
        }
    }
    
    public void inserir(int index, int chave) {
        tabela[index].inserir(chave);
    }

    public int buscar(int index, int chave) {
        return tabela[index].buscarChave(chave);
    }
    
    public int nColisoes() {
        int nColisoes = 0;
        int tamanhoLista;
        for (int i = 0; i < tamanho; i++) {
            tamanhoLista = tabela[i].tamanho();
            if (tamanhoLista > 1) {
                nColisoes += tamanhoLista - 1;
            }
        }
        return nColisoes;
    }
}