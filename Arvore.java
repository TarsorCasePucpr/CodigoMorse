import java.util.Scanner;

public class Arvore {

    static class No {
        char letra;
        String m;
        No esquerda;
        No direita;

        No(char letra, String m) {
            this.letra = letra;
            this.m = m;
            esquerda = null;
            direita = null;
        }
    }

    No raiz;

    public Arvore() {
        raiz = new No(' ', "");
    }

    void inserir(char letra, String morse) {
        No atual = raiz;
        for (char f : morse.toCharArray()) {
            if (f == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new No(' ', "");
                }
                atual = atual.esquerda;
            } 
            else if (f == '-') {
                if (atual.direita == null) {
                    atual.direita = new No(' ', "");
                }
                atual = atual.direita;
            }
        }
        atual.letra = letra;
        atual.m = morse;
    }

    char decipherLetra(String morse) {
        No atual = raiz;
        for (char t : morse.toCharArray()){
            if (t == '.'){
                atual = atual.esquerda;
            } else if(t == '-'){
                atual = atual.direita;
            }
            if (atual == null) return '?'; 
        }
        return atual.letra;
    }

    String buscarMorse(No no, char letraBuscada) {
        if (no == null) return null;
        if (no.letra == letraBuscada) return no.m;

        String achouEsq = buscarMorse(no.esquerda, letraBuscada);
        if (achouEsq != null) return achouEsq;

        return buscarMorse(no.direita, letraBuscada);
    }

    public static void main(String[] args) {
        Arvore morse = new Arvore();

        morse.inserir('A', ".-");
        morse.inserir('B', "-...");
        morse.inserir('C', "-.-.");
        morse.inserir('D', "-..");
        morse.inserir('E', ".");
        morse.inserir('F', "..-.");
        morse.inserir('G', "--.");
        morse.inserir('H', "....");
        morse.inserir('I', "..");
        morse.inserir('J', ".---");
        morse.inserir('K', "-.-");
        morse.inserir('L', ".-..");
        morse.inserir('M', "--");
        morse.inserir('N', "-.");
        morse.inserir('O', "---");
        morse.inserir('P', ".--.");
        morse.inserir('Q', "--.-");
        morse.inserir('R', ".-.");
        morse.inserir('S', "...");
        morse.inserir('T', "-");
        morse.inserir('U', "..-");
        morse.inserir('V', "...-");
        morse.inserir('W', ".--");
        morse.inserir('X', "-..-");
        morse.inserir('Y', "-.--");
        morse.inserir('Z', "--..");

        morse.inserir('0', "-----");
        morse.inserir('1', ".----");
        morse.inserir('2', "..---");
        morse.inserir('3', "...--");
        morse.inserir('4', "....-");
        morse.inserir('5', ".....");
        morse.inserir('6', "-....");
        morse.inserir('7', "--...");
        morse.inserir('8', "---..");
        morse.inserir('9', "----.");

        FileService fileService = new FileService(morse); // NOVO

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\nMorse Menu");
            System.out.println("1.- Cipher (Texto a Morse)");
            System.out.println("2.- Decipher (Morse a Texto)");
            System.out.println("3.- Codificar arquivo");   // NOVO
            System.out.println("4.- Decodificar arquivo"); // NOVO
            System.out.println("0.- Sair");                // ALTERADO (era 3)
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt(); 
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Texto a cifrar: ");
                    String texto = scanner.nextLine().toUpperCase();
                    System.out.print("Resultado: ");
                    for (char c : texto.toCharArray()) {
                        if (c == ' ') {
                            System.out.print(" / ");
                        } else {
                            String codigo = morse.buscarMorse(morse.raiz, c);
                            System.out.print((codigo != null ? codigo : "?") + " ");
                        }
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.print("Morse a decifrar: ");
                    String valor = scanner.nextLine();
                    System.out.print("Resultado: ");
                    String[] letrasMorse = valor.split(" ");
                    for (String m : letrasMorse) {
                        if (m.equals("/")) {
                            System.out.print(" ");
                        } else {
                            System.out.print(morse.decipherLetra(m));
                        }
                    }
                    System.out.println();
                    break;

                case 3: // codificar arquivo
                    System.out.print("Caminho do arquivo .txt: ");
                    try {
                        System.out.println("Resultado: " + fileService.codificarArquivo(scanner.nextLine()));
                    } catch (java.io.IOException | IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 4: // decodificar arquivo
                    System.out.print("Caminho do arquivo Morse: ");
                    try {
                        System.out.println("Resultado: " + fileService.decodificarArquivo(scanner.nextLine()));
                    } catch (java.io.IOException | IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case 0: // ALTERADO (era case 3)
                    executando = false;
                    System.out.println("Saindo do programa");
                    break;

                default:
                    System.out.println("Tenta novamente.");
                    break;
            }
        }
        scanner.close();
    }
}