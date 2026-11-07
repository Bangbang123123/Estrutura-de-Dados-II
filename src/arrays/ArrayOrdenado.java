package arrays;
import comparacao.EstruturaDados;

public class ArrayOrdenado implements EstruturaDados {
    private int[] dados;
    private int tamanhoAtual;

    public ArrayOrdenado(int capacidadeInicial) {
        this.dados = new int[capacidadeInicial + (capacidadeInicial / 10) + 100];
        this.tamanhoAtual = 0;
    }

    @Override 
    public boolean inserir(int valor) {
        int esq = 0;
        int dir = tamanhoAtual - 1;
        int posInsercao = -1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            if (dados[meio] == valor) {
                return false;
            }
            if (dados[meio] < valor) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        posInsercao = esq;

        if (tamanhoAtual >= dados.length) {
            int[] novoArray = new int[dados.length * 2];
            for (int i = 0; i < tamanhoAtual; i++) {
                novoArray[i] = dados[i];
            }
            dados = novoArray;
        }

        for (int i = tamanhoAtual; i > posInsercao; i--) {
            dados[i] = dados[i - 1];
        }

        dados[posInsercao] = valor;
        tamanhoAtual++;
        return true;
    }

    @Override 
    public boolean buscar(int valor) {
        int esq = 0;
        int dir = tamanhoAtual - 1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            if (dados[meio] == valor) {
                return true;
            }
            if (dados[meio] < valor) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }
        return false;
    }

    @Override 
    public boolean remover(int valor) {
        int esq = 0;
        int dir = tamanhoAtual - 1;
        int indice = -1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2;
            if (dados[meio] == valor) {
                indice = meio;
                break;
            }
            if (dados[meio] < valor) {
                esq = meio + 1;
            } else {
                dir = meio - 1;
            }
        }

        if (indice == -1) {
            return false;
        }

        for (int i = indice; i < tamanhoAtual - 1; i++) {
            dados[i] = dados[i + 1];
        }
        tamanhoAtual--;
        return true;
    }

    @Override 
    public int consultarIntervalo(int min, int max) {
        int count = 0;
        for (int i = 0; i < tamanhoAtual; i++) {
            if (dados[i] >= min && dados[i] <= max) {
                count++;
            } else if (dados[i] > max) {
                break;
            }
        }
        return count;
    }

    @Override 
    public int tamanho() {
        return tamanhoAtual;
    }

    @Override
    public void limpar() {
        tamanhoAtual = 0;
    }
}
