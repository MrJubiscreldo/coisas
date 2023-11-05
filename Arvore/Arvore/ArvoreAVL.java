public class ArvoreAVL {
    public class Node {
        Node direita, esquerda;
        int dado, altura;
    
        public Node(int dado) {
            this.direita = this.esquerda = null;
            this.dado = dado;
            this.altura = 1;
        }
    }
    
    private Node raiz;

    public ArvoreAVL() { this.raiz = null; }

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

            balancear(q, dado);
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
            
            balancear(p, dado);
        }
    }

    private int altura(Node no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    private int balanceamento(Node no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerda) - altura(no.direita);
    }

    private Node rotacaoDir(Node raiz) {
        Node novaRaiz = raiz.esquerda;
        Node temp = novaRaiz.direita;
        novaRaiz.direita = raiz;
        raiz.esquerda = temp;

        raiz.altura = Math.max(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        novaRaiz.altura = Math.max(altura(novaRaiz.esquerda), altura(novaRaiz.direita)) + 1;

        return novaRaiz;
    }

    private Node rotacaoEsq(Node raiz) {
        Node novaRaiz = raiz.direita;
        Node temp = novaRaiz.esquerda;
        novaRaiz.esquerda = raiz;
        raiz.direita = temp;

        raiz.altura = Math.max(altura(raiz.esquerda), altura(raiz.direita)) + 1;
        novaRaiz.altura = Math.max(altura(novaRaiz.esquerda), altura(novaRaiz.direita)) + 1;

        return novaRaiz;
    }

    private void balancear(Node no, int dado) {
        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita));
        int altura = balanceamento(no);

        if (altura > 1) {
            if (dado < no.esquerda.dado) {
                this.raiz = rotacaoDir(no);
            } else {
                no.esquerda = rotacaoEsq(no.esquerda);
                this.raiz = rotacaoDir(no);
            }
        }

        if (altura < -1) {
            if (dado > no.direita.dado) {
                this.raiz = rotacaoEsq(no);
            } else {
                no.direita = rotacaoDir(no.direita);
                this.raiz = rotacaoEsq(no);
            }
        }
    }
}
