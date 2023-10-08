public class ArvoreBinaria {
    public class Node {
        Node direita, esquerda;
        int dado;
    
        public Node(int dado) {
            this.direita = this.esquerda = null;
            this.dado = dado;
        }
    }
    
    private Node raiz;

    public ArvoreBinaria() { this.raiz = null; }

    public void inserir(int dado) {
        if (this.raiz == null) {
            this.raiz = new Node(dado);
        } else {
            Node p = this.raiz;
            Node q = null;
    
            do {
                q = p;
                if (dado >= p.dado) {
                    p = p.direita;
                } else {
                    p = p.esquerda;
                }
            } while (p != null);
            
            if (dado >= q.dado) {
                q.direita = new Node(dado);
            } else {
                q.esquerda = new Node(dado);
            }
        }
    }

    public void printInordem() {
        printInordem(this.raiz);
    }

    public void printInordem(Node p) {
        if (p != null) {
            printInordem(p.esquerda);
            System.out.print(p.dado + "->");
            printInordem(p.direita);
        }
    }

    public void buscar(int elemento) {
        Node atual = this.raiz;
        while (atual != null && atual.dado != elemento) {
            if (atual.dado > elemento) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }
    }

    public int removerMenor(Node raiz) {
        Node p = raiz.direita;
        Node q = raiz;
        
        while (p.esquerda != null) {
            q = p;
            p = p.esquerda;
        }

        if (q == raiz) {
            raiz = p.direita;
        } else {
            q.esquerda = p.direita;
        }
        return p.dado;
    }

    public void remover(int dado) {
        Node p = raiz;
        Node q = null;

        while (p != null && p.dado != dado) {
            q = p;
            if (dado >= p.dado) {
                p = p.direita;
            } else {
                p = p.esquerda;
            }
        }

        if (p != null) {
            if (p.direita == null) {
                if (q == null) {
                    this.raiz = p.esquerda;
                } else {
                    q.esquerda = p.esquerda;
                }
            } else {
                p.dado = removerMenor(p);
            }
        }
    }
}