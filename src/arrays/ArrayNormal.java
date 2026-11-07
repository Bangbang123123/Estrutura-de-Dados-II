package arrays;
import comparacao.EstruturaDados;

public class ArrayNormal implements EstruturaDados {
    private int[] dados;
    private int tamanhoAtual;

    public ArrayNormal(int capacidadeInicial) {
        this.dados = new int[capacidadeInicial + (capacidadeInicial / 10) + 100];
        this.tamanhoAtual = 0;
    }


    @Override 
    public boolean inserir(int valor) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (dados[i] == valor) {
                return false;
            }
        }

        if (tamanhoAtual >= dados.length) {
            int[] novoArray = new int[dados.length * 2];
            for (int i = 0; i < tamanhoAtual; i++) {
                novoArray[i] = dados[i];
            }
            dados = novoArray;
        }

        dados[tamanhoAtual] = valor;
        tamanhoAtual++;
        return true;
    }

    @Override  
    public boolean buscar(int valor) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (dados[i] == valor) {
                return true;
            }
        }
        return false;
    }

    @Override 
    public boolean remover(int valor) {
        int indice = -1;
        for (int i = 0; i < tamanhoAtual; i++) {
            if (dados[i] == valor) {
                indice = i;
                break;
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