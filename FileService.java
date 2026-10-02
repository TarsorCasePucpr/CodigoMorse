import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class FileService {

    private final Arvore arvore;

    public FileService(Arvore arvore) {
        this.arvore = arvore;
    }

    public String codificarArquivo(String caminho) throws IOException {
        return arvore.codificar(ler(caminho));
    }

    public String decodificarArquivo(String caminho) throws IOException {
        return arvore.decodificar(ler(caminho).replaceAll("[\r\n]+$", ""));
    }

    private String ler(String caminho) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(caminho.trim())), StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            throw new IllegalArgumentException("Arquivo nao encontrado: " + caminho.trim());
        }
    }
}
