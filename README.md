# Código Morse com Árvore Binária

TDE 2 — Estrutura de Dados (PUCPR). Aplicação em Java que representa o Código Morse em uma árvore binária construída dinamicamente.

- Ponto (`.`) → filho esquerdo
- Traço (`-`) → filho direito
- O caractere fica armazenado na posição alcançada pela sequência.

## Requisitos

- JDK 11 ou superior (`javac` e `java` no PATH)
- Nenhuma biblioteca externa

## Estrutura

| Arquivo | Responsabilidade |
|---|---|
| `MorseNode.java` | Nó da árvore (caractere, código Morse, referências esquerda e direita) |
| `Arvore.java` | Inserção, codificação, decodificação e diagrama da árvore |
| `FileService.java` | Leitura de arquivos `.txt` para codificar/decodificar |
| `Main.java` | Menu e construção da árvore (chamadas manuais de `inserir`) |
| `testes/` | Arquivos de entrada usados nos testes |

## Compilar

```bash
javac *.java
```

## Executar

```bash
java Main
```

## Menu

```
1.- Cipher (Texto a Morse)
2.- Decipher (Morse a Texto)
3.- Codificar arquivo
4.- Decodificar arquivo
5.- Exibir arvore
0.- Sair
```

Após cada operação o programa volta ao menu.

## Formato do Morse

- Letras separadas por **um espaço**.
- Palavras separadas por **`/`** (cercada por espaços).
- Na decodificação, a entrada deve ter **uma única linha** com apenas `.`, `-`, `/` e espaço. Qualquer outro caractere gera mensagem de erro.
- Na codificação, símbolos fora da tabela (letras A–Z e dígitos 0–9) são representados por `?`.

## Exemplos

| Entrada | Saída |
|---|---|
| `SOS` | `... --- ...` |
| `OLA MUNDO` | `--- .-.. .- / -- ..- -. -.. ---` |
| `... --- ...` | `SOS` |
| `--- .-.. .- / -- ..- -. -.. ---` | `OLA MUNDO` |

## Testar com arquivos

Com o programa executado na pasta do projeto:

- Opção 3, caminho: `testes/texto.txt`
- Opção 4, caminho: `testes/morse.txt`

## Referências

- Vídeo sobre árvore de Código Morse: <https://www.youtube.com/watch?v=BqeX-4TshEA>

## Equipe

- Gerard Gonzalez Arana
- Kauã Rubbo
- Bismark Otto
