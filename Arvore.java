public class Arvore {

    MorseNode raiz;

    private int proximoX;

    public Arvore() {
        raiz = new MorseNode(' ', "");
    }

    void inserir(char letra, String morse) {
        MorseNode atual = raiz;
        for (char f : morse.toCharArray()) {
            if (f == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new MorseNode(' ', "");
                }
                atual = atual.esquerda;
            }
            else if (f == '-') {
                if (atual.direita == null) {
                    atual.direita = new MorseNode(' ', "");
                }
                atual = atual.direita;
            }
        }
        atual.letra = letra;
        atual.m = morse;
    }

    char decipherLetra(String morse) {
        MorseNode atual = raiz;
        for (char t : morse.toCharArray()) {
            if (t == '.') {
                atual = atual.esquerda;
            } else if (t == '-') {
                atual = atual.direita;
            } else {
                return '?';
            }
            if (atual == null) return '?';
        }
        return atual.letra == ' ' ? '?' : atual.letra;
    }

    String buscarMorse(MorseNode no, char letraBuscada) {
        if (no == null) return null;
        if (no.letra == letraBuscada) return no.m;

        String achouEsq = buscarMorse(no.esquerda, letraBuscada);
        if (achouEsq != null) return achouEsq;

        return buscarMorse(no.direita, letraBuscada);
    }

    String codificar(String texto) {
        String limpo = texto.trim().toUpperCase();
        if (limpo.isEmpty()) throw new IllegalArgumentException("O texto esta vazio.");

        String[] palavras = limpo.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palavras.length; i++) {
            for (char c : palavras[i].toCharArray()) {
                String codigo = buscarMorse(raiz, c);
                sb.append(codigo != null ? codigo : "?").append(' ');
            }
            if (i < palavras.length - 1) sb.append("/ ");
        }
        return sb.toString().trim();
    }

    String decodificar(String morse) {
        if (!morse.matches("[.\\-/ ]+") || morse.isBlank()) {
            throw new IllegalArgumentException(
                "Entrada invalida: use uma unica linha apenas com . - / e espaco.");
        }

        StringBuilder sb = new StringBuilder();
        for (String token : morse.split(" ")) {
            if (token.isEmpty()) continue;
            sb.append(token.equals("/") ? ' ' : decipherLetra(token));
        }
        return sb.toString();
    }

    void imprimirArvoreVertical() {
        int d = profundidade(raiz);
        char[][] grade = new char[3 * d + 2][largura(raiz, "")];
        for (char[] linha : grade) java.util.Arrays.fill(linha, ' ');
        proximoX = 0;
        desenhar(grade, raiz, 0, "");
        for (char[] linha : grade) {
            System.out.println(new String(linha).replaceAll("\\s+$", ""));
        }
    }

    private int profundidade(MorseNode no) {
        if (no == null) return -1;
        return 1 + Math.max(profundidade(no.esquerda), profundidade(no.direita));
    }

    private int largura(MorseNode no, String cod) {
        if (no == null) return 0;
        return largura(no.esquerda, cod + ".") + Math.max(1, cod.length()) + 1
                + largura(no.direita, cod + "-");
    }

    private void escrever(char[][] grade, int linha, int col, String texto) {
        int inicio = col - texto.length() / 2;
        for (int i = 0; i < texto.length(); i++) grade[linha][inicio + i] = texto.charAt(i);
    }

    private int desenhar(char[][] grade, MorseNode no, int nivel, String cod) {
        int l = no.esquerda != null ? desenhar(grade, no.esquerda, nivel + 1, cod + ".") : -1;
        int w = Math.max(1, cod.length()) + 1;
        int col = proximoX + w / 2;
        proximoX += w;
        int r = no.direita != null ? desenhar(grade, no.direita, nivel + 1, cod + "-") : -1;

        int linha = 3 * nivel;
        escrever(grade, linha, col, no == raiz ? "R" : (no.letra == ' ' ? "*" : String.valueOf(no.letra)));
        escrever(grade, linha + 1, col, cod);

        int ln = linha + 2;
        if (l >= 0) {
            grade[ln][l] = '┌';
            for (int x = l + 1; x < col; x++) grade[ln][x] = '─';
            grade[ln][l + 1] = '.';
        }
        if (r >= 0) {
            grade[ln][r] = '┐';
            for (int x = col + 1; x < r; x++) grade[ln][x] = '─';
            grade[ln][r - 1] = '-';
        }
        if (l >= 0 || r >= 0) {
            grade[ln][col] = l >= 0 && r >= 0 ? '┴' : l >= 0 ? '┘' : '└';
        }
        return col;
    }
}
