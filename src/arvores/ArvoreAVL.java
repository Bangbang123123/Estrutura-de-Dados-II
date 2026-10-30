package arvores;

public class ArvoreAVL {

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
	
	public ArvoreAVL() {
		this.raiz = null;
	}
	
	public void inserirNo(int valor) {
		raiz = inserirNo(raiz, valor);
	}
	
	private No inserirNo(No atual, int valor) {
		if (atual == null) {
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
				atual.dir = rotacaoDireita(atual);
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
		No novaRaiz = raiz.dir;
		raiz.dir = novaRaiz.esq;
		novaRaiz.esq = raiz;
		
		// Atualizar as alturas
		atualizarAlturas(raiz);
		atualizarAlturas(novaRaiz);
		
		return novaRaiz;
	}
	
	private No rotacaoDireita(No raiz) {
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
	
	public void remover(int valor) {
		remover(raiz, valor);
	}
	
	private No remover(No no, int valor) {
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
	
	private No encontrarMenor(No no) {
		while (no.esq != null) {
			no = no.esq;
		}
		
		return no;
	}
}