//apenas introdução e exemplo de árvore, temos que adaptar para ser em forma de código morse, adicionar e traduzir
public class Arvore {

    static class No {
        int valor;
        No esquerda;
        No direita;

        No(int valor) {
            this.valor = valor;
            esquerda = null;
            direita = null;
        }
    }

    No raiz;

    // Inserir um valor na árvore
    void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    No inserirRecursivo(No atual, int valor) {

        if (atual == null) {
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } 
        else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }

        return atual;
    }

    // Percurso em ordem
    void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            emOrdem(no.direita);
        }
    }

    public static void main(String[] args) {

        Arvore arvore = new Arvore();

        arvore.inserir(13);
        arvore.inserir(7);
        arvore.inserir(15);
        arvore.inserir(3);
        arvore.inserir(8);
        arvore.inserir(14);
        arvore.inserir(19);
        arvore.inserir(18);

        System.out.println("Percurso em ordem:");
        arvore.emOrdem(arvore.raiz);
    }
}