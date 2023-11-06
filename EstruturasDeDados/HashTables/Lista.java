public class Lista {
    private Node lista;

    public Lista() {
        this.lista = null;
    }

    public void imprimir() {
        Node atual = this.lista;
        while(atual != null) {
            System.out.println(atual.getInformacao());
            atual = atual.getProximo();
        }
    }

    public int buscar(int index) {
        Node atual = this.lista;
        for(int i = 0; i < index; i++) {
            atual = atual.getProximo();
        }
        if (atual == null) {
            return -1;
        }
        return atual.getInformacao();
    }

    public int buscarChave(int chave) {	
        Node atual = this.lista;
        int i = 0;
        while(atual != null) {
            if (atual.getInformacao() == chave) {
                return i;
            }
            atual = atual.getProximo();
            i++;
        }
        return -1;
    }

    public void inserir(Integer informacao) {
        if (this.lista == null) {
            this.lista = new Node();
            this.lista.setInformacao(informacao);
        }
        else {
            Node no = new Node();
            no.setInformacao(informacao);
            this.lista.insertProximo(no);
        }
    }

    public void remover(int index) {
        Node atual = this.lista;
        for(int i=0;i<index;i++) {
            atual = atual.getProximo();
        }
        Node proximo = atual.getProximo();
        if(proximo != null) {
            atual.setInformacao(proximo.getInformacao());
            atual.setProximo(proximo.getProximo());
        }
        else {
            atual.setInformacao(null);
            atual.setProximo(null);
        }
    }

    public int tamanho() {
        int tamanho = 0;
        Node atual = this.lista;
        while(atual != null) {
            tamanho++;
            atual = atual.getProximo();
        }
        return tamanho;
    }
}
