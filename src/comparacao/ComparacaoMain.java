package comparacao;
import arrays.*;
import arvores.*;
import hash.encadeamento.HashFechada;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import listas_lineares.*;

public class ComparacaoMain {

    public static void main(String[] args){
        int[] tamanhos = {5000,10000,100000};
        String[] ordens = {"crescente", "decrescente", "aleatoria"};
        
        List<String> linhasBrutas = new ArrayList<>();
        List<String> linhasResumo = new ArrayList<>();

        linhasBrutas.add("tamanho;ordem;estrutura;teste;repeticao;operacoes;tempo_ns");
        linhasResumo.add("tamanho;ordem;estrutura;teste;mediana_tempo_ms;mediana_tempo_por_op_ns");

        for (int n : tamanhos) {
            int[] baseDados = new int[n];
            for (int i = 0; i < n; i++) {
                baseDados[i] = (i + 1) * 2;
            }

            for (String ordem : ordens) {
                int[] dadosIniciais = prepararOrdem(baseDados, ordem);

                executarTestes(n, ordem, "Array Normal", new ArrayNormal(dadosIniciais.length), dadosIniciais, linhasBrutas, linhasResumo);
                executarTestes(n, ordem, "Array Ordenado", new ArrayOrdenado(dadosIniciais.length), dadosIniciais, linhasBrutas, linhasResumo);
                executarTestes(n, ordem, "Lista Encadeada Dupla", new ListaDupla(), dadosIniciais, linhasBrutas, linhasResumo);
                executarTestes(n, ordem, "Tabela Hash", new HashFechada(dadosIniciais.length * 2), dadosIniciais, linhasBrutas, linhasResumo);
                executarTestes(n, ordem, "Arvore Binária de Busca", new ArvoreBinariaBusca(), dadosIniciais, linhasBrutas, linhasResumo);
                executarTestes(n, ordem, "Arvore AVL", new ArvoreAVL(), dadosIniciais, linhasBrutas, linhasResumo);
                executarTestes(n, ordem, "Arvore Rubro-Negra", new ArvoreRubroNegra(), dadosIniciais, linhasBrutas, linhasResumo);

            }
        }

        try {
            salvarCSV("resultados_brutos.csv", linhasBrutas);
            salvarCSV("resumo.csv", linhasResumo);
            System.out.println("Testes concluídos e arquivos CSV gerados com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private static int[] prepararOrdem(int[] base, String ordem) {
        int[] copia = null;
        if (ordem.equals("crescente")){
            copia = base.clone();
        } else if (ordem.equals("decrescente")) {
            copia = new int[base.length];
            for (int i = 0; i < base.length; i++) {
                copia[i] = base[base.length - i - 1];
            }
        } else if (ordem.equals("aleatoria")) {
            copia = base.clone();
            Random rand = new Random(42);
            for (int i = copia.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                int temp = copia[i];
                copia[i] = copia[j];
                copia[j] = temp;
            }
        }
        return copia;
    }

    private static void executarTestes(int n, String ordem, String nomeEstrutura, EstruturaDados estrutura, int[] dadosIniciais, List<String> brutos, List<String> resumo) {
        Random rand = new Random(42);

        int[] buscasPresenteVals = new int[2000];
        for (int i = 0; i < 2000; i++) {
            buscasPresenteVals[i] = dadosIniciais[rand.nextInt(n)];
        }

        int[] buscasAusenteVals = new int[2000];
        for (int i = 0; i < 2000; i++) {
            buscasAusenteVals[i] = 1 + 2 * rand.nextInt(n);
        }

        int qtd10porc = (int) (n * 0.1);
        int[] insercoesVals = new int[qtd10porc];
        for (int i = 0; i < qtd10porc; i++) {
            insercoesVals[i] = 1 + 2 * rand.nextInt(n);
        }

        int[] remocoesVals = new int[qtd10porc];
        Set<Integer> indicesRem = new HashSet<>();
        while (indicesRem.size() < qtd10porc) {
            indicesRem.add(rand.nextInt(n));
        }
        int idxR = 0;
        for (int ind : indicesRem) {
            remocoesVals[idxR++] = dadosIniciais[ind];
        }

        int[] intervalMins = new int[200];
        for (int i = 0; i < 200; i++) {
            intervalMins[i] = 1 + rand.nextInt(2 * n - 100);
        }

        String[] testes = {"Construcao", "BuscaPresente", "BuscaAusente", "Insercao", "Remocao", "ConsultaIntervalo"};

        for (String teste : testes) {
            long[] temposNs = new long[5];

            if (!teste.equals("Construcao")) {
                estrutura.limpar();
                for (int val : dadosIniciais) {
                    estrutura.inserir(val);
                }
            }
            rodarLote(teste, estrutura, dadosIniciais, n, buscasPresenteVals, buscasAusenteVals, insercoesVals, remocoesVals, intervalMins);

            for (int rep = 0; rep < 5; rep++) {
                if (!teste.equals("Construcao")) {
                    estrutura.limpar();
                    for (int val : dadosIniciais) {
                        estrutura.inserir(val);
                    }
                }

                long inicio = System.nanoTime();
                long resultadoCheck = rodarLote(teste, estrutura, dadosIniciais, n, buscasPresenteVals, buscasAusenteVals, insercoesVals, remocoesVals, intervalMins);
                System.out.println("Resultado ["+ n +" // "+ ordem +" // "+ nomeEstrutura +" // "+ teste +"]: "+ resultadoCheck);
                long fim = System.nanoTime();

                temposNs[rep] = fim - inicio;
                long qtdOps = obterQtdOps(teste, n);

                brutos.add(n + ";" + ordem + ";" + nomeEstrutura + ";" + teste + ";" + (rep + 1) + ";" + qtdOps + ";" + temposNs[rep]);
            }

            Arrays.sort(temposNs);
            long medianaNs = temposNs[2];
            double medianaMs = medianaNs / 1000000.0;
            long qtdOps = obterQtdOps(teste, n);
            double medianaPorOpNs = (double) medianaNs / qtdOps;

            resumo.add(String.format(Locale.US, "%d;%s;%s;%s;%.3f;%.2f", n, ordem, nomeEstrutura, teste, medianaMs, medianaPorOpNs));
        }
    }

    private static long rodarLote(String teste, EstruturaDados estrutura, int[] dadosIniciais, int n, int[] bPres, int[] bAus, int[] ins, int[] rem, int[] intMins) {
        long checkSum = 0;

        switch (teste) {
            case "Construcao" -> {
                estrutura.limpar();
                for (int i = 0; i < n; i++) {
                    if (estrutura.inserir(dadosIniciais[i])) checkSum++;
                }
            }

            case "BuscaPresente" -> {
                for (int v : bPres) {
                    if (estrutura.buscar(v)) checkSum++;
                }
            }

            case "BuscaAusente" -> {
                for (int v : bAus) {
                    if (!estrutura.buscar(v)) checkSum++;
                }
            }

            case "Insercao" -> {
                for (int v : ins) {
                    if (estrutura.inserir(v)) checkSum++;
                }
            }

            case "Remocao" -> {
                for (int v : rem) {
                    if (estrutura.remover(v)) checkSum++;
                }
            }

            case "ConsultaIntervalo" -> {
                for (int min : intMins) {
                    int max = min + 100;
                    checkSum += estrutura.consultarIntervalo(min, max);
                }
            }
        }
        return checkSum;
    }

    private static long obterQtdOps(String teste, int n) {
        switch (teste) {
            case "Construcao": return n;
            case "BuscaPresente":
            case "BuscaAusente": return 2000;
            case "Insercao":
            case "Remocao": return (long) (n * 0.1);
            case "ConsultaIntervalo": return 200;
            default: return 1;
        }
    }

    private static void salvarCSV(String caminho, List<String> linhas) throws IOException {
        java.io.File arquivoTeste = new java.io.File("resultados_brutos.csv");
        System.out.println("O arquivo será salvo em: " + arquivoTeste.getAbsolutePath());
        try (FileWriter writer = new FileWriter(caminho)) {
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
        }
    }
}