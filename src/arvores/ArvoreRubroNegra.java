package arvores;

import comparacao.EstruturaDados;

public class ArvoreRubroNegra implements EstruturaDados{

	private enum Cor {
		VERMELHO,
		PRETO
	}
	
	private class No {
		private int valor;
		private Cor cor;
		private No esq, dir, pai;
		
		public No(int valor) {
			this.valor = valor;
			cor = Cor.VERMELHO;
			esq = dir = pai = null;
		}
	}
	
	private No raiz;
	private int tamanhoAtual;
	
	public ArvoreRubroNegra() {
		raiz = null;
	}

	@Override
    public int consultarIntervalo(int min, int max) {
        return consultarIntervaloRec(raiz, min, max);
    }

    private int consultarIntervaloRec(No atual, int min, int max) {
        if (atual == null) {
            return 0;
        }

        int count = 0;

        if (atual.valor >= min && atual.valor <= max) {
            count = 1;
        }

        if (atual.valor > min) {
            count += consultarIntervaloRec(atual.esq, min, max);
        }

        if (atual.valor < max) {
            count += consultarIntervaloRec(atual.dir, min, max);
        }

        return count;
    }
	
	@Override
	public boolean inserir(int valor) {
		if (buscar(valor)) {
			return false;
		}
		
		No novoNo = new No(valor);
		raiz = inserirNo(raiz, novoNo);
		corrigirInsercao(novoNo);
		tamanhoAtual++;
		return true;
	}
	
	// Insere como em uma árvore binária de busca
	private No inserirNo(No atual, No novoNo) {
		if (atual == null) {
			return novoNo;
		}
		
		if (novoNo.valor < atual.valor) {
			atual.esq = inserirNo(atual.esq, novoNo);
			atual.esq.pai = atual;
		} else if (novoNo.valor > atual.valor ) {
			atual.dir = inserirNo(atual.dir, novoNo);
			atual.dir.pai = atual;
		}
		
		return atual;
	}
	
	private void corrigirInsercao(No no) {
		// A raiz será pinteda de preto ao final
		// Nas demais posições, só há conflito quando o pai é vermelho
		
		while (no != raiz && no.pai.cor == Cor.VERMELHO) {
			No pai = no.pai;
			No avo = pai.pai;
			
			if (pai == avo.esq) {
				No tio = avo.dir;
				
				// Caso 1: Tio vermelho, recolore e continua pelo avô
				if (cor(tio) == Cor.VERMELHO) {
					pai.cor = Cor.PRETO;
					tio.cor = Cor.PRETO;
					avo.cor = Cor.VERMELHO;
					no = avo;
				} else {
					// Caso 2: Triângulo: Transforma em uma linha
					if (no == pai.dir) {
						no = pai;
						rotacaoEsquerda(no);
						
						// A rotação mudou as relações entre os nós
						pai = no.pai;
						avo = pai.pai;
					}
					
					// Caso 3: Linha: Recolore e gira sobre o avô
					pai.cor = Cor.PRETO;
					avo.cor = Cor.VERMELHO;
					rotacaoDireita(avo);
				}
				
			} else {
				// Caso simétricos: Pai e filho direito do avô
				No tio = avo.esq;
				
				// Caso 1: Tio vermelho
				if (cor(tio) == Cor.VERMELHO) {
					pai.cor = Cor.PRETO;
					tio.cor = Cor.PRETO;
					avo.cor = Cor.VERMELHO;
					no = avo;
				} else {
					// Caso 2: Triângulo: Transforma em uma linha
					if (no == pai.esq) {
						no = pai;
						rotacaoDireita(no);
						
						pai = no.pai;
						avo = pai.pai;
					}
					
					// Caso 3: Linha
					pai.cor = Cor.PRETO;
					avo.cor = Cor.VERMELHO;
					rotacaoEsquerda(avo);
				}
				
			}
		}
		
		raiz.cor = Cor.PRETO;
	}
	
	private void rotacaoEsquerda(No no) {
		No novaRaiz = no.dir;
		no.dir = novaRaiz.esq;
		
		if (novaRaiz.esq != null) {
			novaRaiz.esq.pai = no;
		}
		
		novaRaiz.pai = no.pai;
		
		if (no.pai == null) {
			raiz = novaRaiz;
		} else if (no == no.pai.esq) {
			no.pai.esq = novaRaiz;
		} else {
			no.pai.dir = novaRaiz;
		}
		
		novaRaiz.esq = no;
		no.pai = novaRaiz;
	}
	
	private void rotacaoDireita(No no) {
		No novaRaiz = no.esq;
		no.esq = novaRaiz.dir;
		
		if (novaRaiz.dir != null) {
			novaRaiz.dir.pai = no;
		}
		
		novaRaiz.pai = no.pai;
		
		if (no.pai == null) {
			raiz = novaRaiz;
		} else if (no == no.pai.dir) {
			no.pai.dir = novaRaiz;
		} else {
			no.pai.esq = novaRaiz;
		}
		
		novaRaiz.dir = no;
		no.pai = novaRaiz;
	}
	
	@Override 
	public boolean buscar(int valor) {
		return buscarNo(raiz, valor) != null;
	}
	
	private No buscarNo(No atual, int valor) {
		if (atual == null || atual.valor == valor) {
			return atual;
		}
		
		if (valor < atual.valor) {
			return buscarNo(atual.esq, valor);
		} else {
			return buscarNo(atual.dir, valor);
		}
	}
	
	private Cor cor(No no) {
		return no == null ? Cor.PRETO : no.cor;
	}
	
	public void mostrar() {
		mostrar(raiz);
	}
	
	private void mostrar(No raiz) {
		int preto = 0;
		int vermelho = 0;
		
		if (raiz != null) {
			mostrar(raiz.esq);
			
			if (raiz.cor == Cor.PRETO) {
				preto++;
			} else {
				vermelho++;
			}
			System.out.println(raiz.valor+" ("+raiz.cor+")");
			mostrar(raiz.dir);
		} else {
			System.out.println("Pretos : "+preto);
			System.out.println("Vermelhos: "+vermelho);
		}
	}
	
	@Override 
	public boolean remover(int valor) {
        No no = buscarNo(raiz, valor);
        if (no == null) {
            return false;
        }
        removerNo(no);
		tamanhoAtual--;
		return true;
    }

    private void removerNo(No no) {
        // Como na AVL: com dois filhos, copia apenas o valor do sucessor.
        // A remoção física passa a ocorrer na posição do sucessor.
    	
        
        if (no.esq != null && no.dir != null) {
            No sucessor = minimo(no.dir);
            no.valor = sucessor.valor;
            no = sucessor;
        }

        // Agora no possui, no máximo, um filho.
        No substituto;
        if (no.esq != null) {
            substituto = no.esq;
        } else {
            substituto = no.dir;
        }

        Cor corRemovida = no.cor;

        // Guarda o pai, pois substituto pode ser null e não ter campo pai.
        No paiSubstituto = no.pai;
        transplantar(no, substituto);

        // Corrige também quando substituto é null: uma folha preta
        // removida pode deixar um caminho com um nó preto a menos.
        if (corRemovida == Cor.PRETO) {
            corrigirRemocao(substituto, paiSubstituto);
        }
    }

    private void transplantar(No removido, No substituto) {
        if (removido.pai == null) {
            raiz = substituto;
        } else if (removido == removido.pai.esq) {
            removido.pai.esq = substituto;
        } else {
            removido.pai.dir = substituto;
        }

        if (substituto != null) {
            substituto.pai = removido.pai;
        }
    }

    private No minimo(No no) {
        while (no.esq != null) {
            no = no.esq;
        }
        return no;
    }

    private void corrigirRemocao(No no, No pai) {
        // No indica a posição cujo caminho perdeu um preto.
        // Pai é passado separadamente porque nó pode ser null.
      
        while (no != raiz && cor(no) == Cor.PRETO) {
            if (no == pai.esq) {
                No irmao = pai.dir;

				// CASO 1: irmão vermelho. Transforma em irmão preto.
				if (cor(irmao) == Cor.VERMELHO) {
					irmao.cor = Cor.PRETO;
					pai.cor = Cor.VERMELHO;
					rotacaoEsquerda(pai);
					irmao = pai.dir;
				}

				// Se o irmão agora for null, ele é tratado como preto com filhos pretos (Caso 2)
				if (irmao == null) {
					no = pai;
					pai = no.pai;
				} else {
					// CASO 2: irmão preto e seus dois filhos pretos.
					if (cor(irmao.esq) == Cor.PRETO && cor(irmao.dir) == Cor.PRETO) {
						irmao.cor = Cor.VERMELHO;
						no = pai;
						pai = no.pai;
					} else {
						// CASO 3: sobrinho distante preto e próximo vermelho.
						if (cor(irmao.dir) == Cor.PRETO) {
							if (irmao.esq != null) irmao.esq.cor = Cor.PRETO;
							irmao.cor = Cor.VERMELHO;
							rotacaoDireita(irmao);
							irmao = pai.dir;
						}

						// CASO 4: sobrinho distante vermelho.
						irmao.cor = pai.cor;
						pai.cor = Cor.PRETO;
						if (irmao.dir != null) irmao.dir.cor = Cor.PRETO;
						rotacaoEsquerda(pai);
						no = raiz; // Encerra o laço: correção concluída.
					}
				}
            } else {
                No irmao = pai.esq;

				// CASO 1: irmão vermelho.
				if (cor(irmao) == Cor.VERMELHO) {
					irmao.cor = Cor.PRETO;
					pai.cor = Cor.VERMELHO;
					rotacaoDireita(pai);
					irmao = pai.esq;
				}

				// Se o irmão agora for null, trata como Caso 2
				if (irmao == null) {
					no = pai;
					pai = no.pai;
				} else {
					// CASO 2: irmão preto e seus dois filhos pretos.
					if (cor(irmao.dir) == Cor.PRETO && cor(irmao.esq) == Cor.PRETO) {
						irmao.cor = Cor.VERMELHO;
						no = pai;
						pai = no.pai;
					} else {
						// CASO 3: sobrinho distante preto e próximo vermelho.
						if (cor(irmao.esq) == Cor.PRETO) {
							if (irmao.dir != null) irmao.dir.cor = Cor.PRETO;
							irmao.cor = Cor.VERMELHO;
							rotacaoEsquerda(irmao);
							irmao = pai.esq;
						}

						// CASO 4: sobrinho distante vermelho.
						irmao.cor = pai.cor;
						pai.cor = Cor.PRETO;
						if (irmao.esq != null) irmao.esq.cor = Cor.PRETO;
						rotacaoDireita(pai);
						no = raiz;
					}
				}
            }
        }

        // Um substituto vermelho absorve a falta de um preto.
        // Se a correção chegou à raiz, mantém a raiz preta.
        if (no != null) {
            no.cor = Cor.PRETO;
        }
    }

	@Override 
	public int tamanho() {
		return tamanhoAtual;
	}

	@Override
	public void limpar() {
		raiz = null;
		tamanhoAtual = 0;
	}
}