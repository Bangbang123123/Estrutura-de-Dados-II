

public class ComparacaoMain {

    public static void main(String[] args) throws IOException {
        int[] tamanhos = {5000, 10000, 100000};
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

                Map<String, EstruturaDados> estruturas = new LinkedHashMap<>();
                estruturas.put("HashSet", new EstruturaHash());
                estruturas.put("ArrayOrdenado", new EstruturaArrayOrdenado(n));

                for (Map.Entry<String, EstruturaDados> entry : estruturas.entrySet()) {
                    String nomeEstrutura = entry.getKey();
                    EstruturaDados estrutura = entry.getValue();

                    executarTestes(n, ordem, nomeEstrutura, estrutura, dadosIniciais, linhasBrutas, linhasResumo);
                }
            }
        }

        salvarCSV("resultados_brutos.csv", linhasBrutas);
        salvarCSV("resumo.csv", linhasResumo);
        System.out.println("Testes concluídos e arquivos CSV gerados com sucesso!");
    }

    private static int[] prepararOrdem(int[] base, String ordem) {
        int[] copia;
        if (ordem.equals("crescente")){
            copia = base.clone();
        } else if (ordem.equals("decrescente")) {
            copia = new int[base.length];
            for (int i = 0; i < base.length; i++) {
                copia[i] = base[base.length - i - 1];
            }
        } else if (ordem.equals("aleatoria")) {
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

            rodarLote(teste, estrutura, dadosIniciais, n, buscasPresenteVals, buscasAusenteVals,
                    insercoesVals, remocoesVals, intervalMins);

            for (int rep = 0; rep < 5; rep++) {
                long inicio = System.nanoTime();
                long resultadoCheck = rodarLote(teste, estrutura, dadosIniciais, n, buscasPresenteVals, buscasAusenteVals, insercoesVals, remocoesVals, intervalMins);
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

            resumo.add(String.format(Locale.BR, "%d;%s;%s;%s;%.3f;%.2f", n, ordem, nomeEstrutura, teste, medianaMs, medianaPorOpNs));
        }
    }

    private static long rodarLote(String teste, EstruturaDados estrutura, int[] dadosIniciais, int n, int[] bPres, int[] bAus, int[] ins, int[] rem, int[] intMins) {
        long checkSum = 0;

        switch (teste) {
            case "Construcao":
                estrutura.limpar();
                for (int i = 0; i < n; i++) {
                    estrutura.inserir(dadosIniciais[i]);
                }
                checkSum = estrutura.tamanho();
                break;

            case "BuscaPresente":
                estrutura.limpar();
                for (int v : dadosIniciais) estrutura.inserir(v);
                for (int v : bPres) {
                    if (estrutura.buscar(v)) checkSum++;
                }
                break;

            case "BuscaAusente":
                estrutura.limpar();
                for (int v : dadosIniciais) estrutura.inserir(v);
                for (int v : bAus) {
                    if (!estrutura.buscar(v)) checkSum++;
                }
                break;

            case "Insercao":
                estrutura.limpar();
                for (int v : dadosIniciais) estrutura.inserir(v);
                for (int v : ins) {
                    if (estrutura.inserir(v)) checkSum++;
                }
                break;

            case "Remocao":
                estrutura.limpar();
                for (int v : dadosIniciais) estrutura.inserir(v);
                for (int v : rem) {
                    if (estrutura.remover(v)) checkSum++;
                }
                break;

            case "ConsultaIntervalo":
                estrutura.limpar();
                for (int v : dadosIniciais) estrutura.inserir(v);
                for (int min : intMins) {
                    int max = min + 100;
                    checkSum += estrutura.consultarIntervalo(min, max);
                }
                break;
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
        try (FileWriter writer = new FileWriter(caminho)) {
            for (String linha : linhas) {
                writer.write(linha + "\n");
            }
        }
    }
}