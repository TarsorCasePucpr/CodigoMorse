import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    private final Arvore arvore;

    public FileService(Arvore arvore) {
        this.arvore = arvore;
    }


    public String codificarArquivo(String caminho) throws IOException {
        String texto = new String(Files.readAllBytes(Paths.get(caminho.trim()))).trim().toUpperCase();
        if (texto.isEmpty()) throw new IllegalArgumentException("O arquivo esta vazio.");

        String[] palavras = texto.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palavras.length; i++) {
            for (char c : palavras[i].toCharArray()) {
                String codigo = arvore.buscarMorse(arvore.raiz, c);
                sb.append(codigo != null ? codigo : "?").append(' ');
            }
            if (i < palavras.length - 1) sb.append("/ ");
        }
        return sb.toString().trim();
    }


    public String decodificarArquivo(String caminho) throws IOException {
        String linha = new String(Files.readAllBytes(Paths.get(caminho.trim()))).replaceAll("[\r\n]+$", "");

        if (!linha.matches("[.\\-/ ]+")) {
            throw new IllegalArgumentException(
                "Arquivo invalido: deve ter uma unica linha apenas com . - / e espaco.");
        }

        StringBuilder sb = new StringBuilder();
        for (String token : linha.split(" ")) {
            if (token.isEmpty()) continue;
            sb.append(token.equals("/") ? ' ' : decifrar(token));
        }
        return sb.toString();
    }

    private char decifrar(String morse) {
        Arvore.No atual = arvore.raiz;
        for (char t : morse.toCharArray()) {
            atual = (t == '.') ? atual.esquerda : atual.direita;
            if (atual == null) return '?';
        }
        return atual.letra == ' ' ? '?' : atual.letra;
    }
}