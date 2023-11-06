public class Node {
    private Integer informacao;
    private Node proximo;

    public Node() {
        this.informacao = null;
        this.proximo = null;
    }

    public Integer getInformacao() {
        return this.informacao;
    }

    public void setInformacao(Integer informacao) {
        this.informacao = informacao;
    }

    public Node getProximo() {
        return this.proximo;
    }

    public void setProximo(Node proximo) {
        this.proximo = proximo;
    }

    public void insertProximo(Node no) {
        if (this.proximo == null) {
            this.proximo = no;
        }
        else {
            this.proximo.insertProximo(no);
        }
    }
}
