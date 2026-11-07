package hash.encadeamento;
import comparacao.EstruturaDados;

public class HashFechada implements EstruturaDados {

    private static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
        }
    }

    private No[] tabela;
    private int capacidade;
    private int tamanhoAtual;

    public HashFechada(int capacidadeInicial) {
        this.capacidade = capacidadeInicial;
        this.tabela = new No[capacidade];
        this.tamanhoAtual = 0;
    }

    private int hash(int valor) {
        return Math.abs(valor) % capacidade;
    }

    @Override
    public void limpar() {
        for (int i = 0; i < capacidade; i++) {
            tabela[i] = null;
        }
        tamanhoAtual = 0;
    }

    @Override
    public boolean inserir(int valor) {
        int indice = hash(valor);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.valor == valor) {
                return false;
            }
            atual = atual.proximo;
        }

        No novoNo = new No(valor);
        novoNo.proximo = tabela[indice];
        tabela[indice] = novoNo;
        tamanhoAtual++;
        return true;
    }

    @Override
    public boolean buscar(int valor) {
        int indice = hash(valor);
        No atual = tabela[indice];

        while (atual != null) {
            if (atual.valor == valor) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    @Override
    public boolean remover(int valor) {
        int indice = hash(valor);
        No atual = tabela[indice];
        No anterior = null;

        while (atual != null) {
            if (atual.valor == valor) {
                if (anterior == null) {
                    tabela[indice] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanhoAtual--;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    @Override
    public int consultarIntervalo(int min, int max) {
        int count = 0;
        for (int i = 0; i < capacidade; i++) {
            No atual = tabela[i];
            while (atual != null) {
                if (atual.valor >= min && atual.valor <= max) {
                    count++;
                }
                atual = atual.proximo;
            }
        }
        return count;
    }

    @Override
    public int tamanho() {
        return tamanhoAtual;
    }

}
