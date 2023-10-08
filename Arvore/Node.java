public class Node {
    Node direita, esquerda;
    int dado, altura;

    public Node(int dado) {
        this.direita = this.esquerda = null;
        this.dado = dado;
        this.altura = 1;
    }
}