import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Arvore morse = construirArvore();
        FileService fileService = new FileService(morse);

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\nMorse Menu");
            System.out.println("1.- Cipher (Texto a Morse)");
            System.out.println("2.- Decipher (Morse a Texto)");
            System.out.println("3.- Codificar arquivo");
            System.out.println("4.- Decodificar arquivo");
            System.out.println("5.- Exibir arvore");
            System.out.println("0.- Sair");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerOpcao(scanner);

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Texto a cifrar: ");
                        System.out.println("Resultado: " + morse.codificar(scanner.nextLine()));
                        break;
                    case 2:
                        System.out.print("Morse a decifrar: ");
                        System.out.println("Resultado: " + morse.decodificar(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.print("Caminho do arquivo .txt: ");
                        System.out.println("Resultado: " + fileService.codificarArquivo(scanner.nextLine()));
                        break;
                    case 4:
                        System.out.print("Caminho do arquivo Morse: ");
                        System.out.println("Resultado: " + fileService.decodificarArquivo(scanner.nextLine()));
                        break;
                    case 5:
                        morse.imprimirArvoreVertical();
                        break;
                    case 0:
                        executando = false;
                        System.out.println("Saindo do programa");
                        break;
                    default:
                        System.out.println("Opcao invalida. Tenta novamente.");
                        break;
                }
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static int lerOpcao(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static Arvore construirArvore() {
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

        return morse;
    }
}
