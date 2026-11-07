package comparacao;

public interface EstruturaDados {
    void limpar();
    boolean inserir(int valor);
    boolean buscar(int valor);
    boolean remover(int valor);
    int consultarIntervalo(int min, int max);
    int tamanho();
}