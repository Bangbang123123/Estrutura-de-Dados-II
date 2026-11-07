package arvores;

import comparacao.EstruturaDados;

public class ArvoreAVL implements EstruturaDados{

	private class No {
		private int valor;
		public No esq, dir;
		public int alturaEsq;
		public int alturaDir;
		
		public No(int valor) {
			this.valor = valor;
			esq = dir = null;
			alturaEsq = alturaDir = 0;
		}
	}
	
	private No raiz;
	private int tamanhoAtual = 0;
	
	public ArvoreAVL() {
		this.raiz = null;
	}
	
	@Override 
	public boolean inserir(int valor) {
		int tamanho = tamanhoAtual;
		raiz = inserirNo(raiz, valor);
		return tamanho + 1 == tamanhoAtual;
	}

	private No inserirNo(No atual, int valor) {
		if (atual == null) {
			tamanhoAtual++;
			return new No(valor);
		}
		
		// Insersão na subárvore esquerda (valores menores)
		if (valor < atual.valor) {
			atual.esq = inserirNo(atual.esq, valor);
			atual.alturaEsq = Math.max(atual.esq.alturaEsq, atual.esq.alturaDir)+1;
		}
		
		// Insersão na subárvore direita (valores maiores)
		if (valor > atual.valor) {
			atual.dir = inserirNo(atual.dir, valor);
			atual.alturaDir = Math.max(atual.dir.alturaEsq, atual.dir.alturaDir)+1;
		}
		
		return balancear(atual);
	}
	
	private No balancear(No atual) {
		int fatorBalanceamento = atual.alturaDir - atual.alturaEsq;
		
		// Desbalanceamento à direia
		if (fatorBalanceamento == 2) {
			// Se o filho à direita também fator positivo
			// (Rotação simples à esquerda)
			if (atual.dir.alturaDir >= atual.dir.alturaEsq) {
				return rotacaoEsquerda(atual);
			} else {
				atual.dir = rotacaoDireita(atual.dir);
				return rotacaoEsquerda(atual);
			}
		} else if (fatorBalanceamento == -2) {
			// Rotação simples à direita
			if (atual.esq.alturaEsq >= atual.esq.alturaDir) {
				return rotacaoDireita(atual);
			} else {
				// Caso contrário, rotação dupla esquerda-direita
				atual.esq = rotacaoEsquerda(atual.esq);
				return rotacaoDireita(atual);
			}
		}
		
		// Retorna nó atual balanceado se necessário
		return atual;
	}

	private No rotacaoEsquerda(No raiz) {
		if (raiz == null || raiz.dir == null) {
			return raiz;
		}

		No novaRaiz = raiz.dir;
		raiz.dir = novaRaiz.esq;
		novaRaiz.esq = raiz;
		
		// Atualizar as alturas
		atualizarAlturas(raiz);
		atualizarAlturas(novaRaiz);
		
		return novaRaiz;
	}
	
	private No rotacaoDireita(No raiz) {
		if (raiz == null || raiz.esq == null) {
			return raiz;
		}

		No novaRaiz = raiz.esq;  //Filho esquerdo se torna nova raiz
		raiz.esq = novaRaiz.dir;
		novaRaiz.dir = raiz;
		
		// Atualizar alturas
		atualizarAlturas(raiz);
		atualizarAlturas(novaRaiz);
		
		return novaRaiz;
	}
	
	private void atualizarAlturas(No no) {
		if (no != null) {
			no.alturaEsq = (no.esq == null) ? 0 : Math.max(no.esq.alturaEsq, no.esq.alturaDir)+1;
			
			no.alturaDir = (no.dir == null) ? 0 : Math.max(no.dir.alturaEsq, no.dir.alturaDir)+1;
		}
	}
	
	public void mostrar() {
		mostrar(raiz);
	}
	
	private void mostrar(No no) {
		if (no != null) {
			mostrar(no.esq);
			System.out.println(no.valor);
			mostrar(no.dir);
		}
	}
	
	@Override 
	public boolean remover(int valor) {
		if (!buscar(valor)) {
			return false;
		}
		
		int tamanho = tamanhoAtual;
		raiz = remover(raiz, valor);
		return tamanho-1 == tamanhoAtual;
	}
	
	private No remover(No no, int valor) {
		if (no == null) {
			return null;
		}
		// Procurando pelo No
		
		if (valor < no.valor) {
			no.esq = remover(no.esq, valor);
		} else if (valor > no.valor) {
			no.dir = remover(no.dir, valor);
		} else {
			// Caso 1: No folha
			if (no.esq == null && no.dir == null) {
				return null;
			}
			
			//Caso 2: possui somente um filho
			if (no.esq == null) {
				return no.dir;
			} else if (no.dir == null) {
				return no.esq;
			}
			
			// Caso 3: possui filhos
			No sucessor = encontrarMenor(no.dir);
			
			// Copia o valor do sucessor
			no.valor = sucessor.valor;
			
			// Remover o sucessor da posicao original
			no.dir = remover(no.dir, sucessor.valor);
		}
		
		atualizarAlturas(no);
		return balancear(no);
	}

	
	@Override 
	public boolean buscar(int valor) {
		No atual = raiz;
        
        while (atual != null) {
            if (valor == atual.valor) {
                return true;
            }
            
            if (valor < atual.valor) {
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }
        
        return false;
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
	public int tamanho() {
		return tamanhoAtual;
	}

	@Override
	public void limpar() {
		raiz = null;
		tamanhoAtual = 0;
	}

	private No encontrarMenor(No no) {
		while (no.esq != null) {
			no = no.esq;
		}
		
		return no;
	}
}