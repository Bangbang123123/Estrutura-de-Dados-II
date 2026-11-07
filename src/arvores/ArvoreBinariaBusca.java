package arvores;

import comparacao.EstruturaDados;


public class ArvoreBinariaBusca implements EstruturaDados{

	public class No {
		private int valor;
		private No esquerdo;
		private No direito;
		
		
		public No(int valor) {
			this.valor = valor;
			direito = esquerdo = null;
		}
		
	}
	
	public No raiz;
	private int tamanhoAtual = 0;
	
	@Override
	public boolean inserir(int valor) {
		if (raiz == null) {
			raiz = new No(valor);
			tamanhoAtual++;
			return true;
		}

		No atual = raiz;
		No pai = atual;

		while (atual != null) {
			if (valor == atual.valor) {
				return false;
			}
			pai = atual;
			if (valor < atual.valor) {
				atual = atual.esquerdo;
			} else {
				atual = atual.direito;
			}
		}

		No novoNo = new No(valor);
		if (valor < pai.valor) {
			pai.esquerdo = novoNo;
		} else {
			pai.direito = novoNo;
		}

		tamanhoAtual++;
		return true;
	}
	
	@Override 
	public boolean buscar(int valor) {
		No atual = raiz;
		
		while (atual != null) {
			if (valor == atual.valor) {
				return true;
			}
			
			if (valor < atual.valor) {
				atual = atual.esquerdo;
			} else {
				atual = atual.direito;
			}
		}
		
		return false;
	}

	@Override
	public boolean remover(int valor) {
		No atual = raiz;
		No pai = atual;
		boolean filhoEsquerdo = false;

		// 1. Procurar o nó a ser removido e manter o rastro do pai
		while (atual != null && atual.valor != valor) {
			pai = atual;
			if (valor < atual.valor) {
				atual = atual.esquerdo;
				filhoEsquerdo = true;
			} else {
				atual = atual.direito;
				filhoEsquerdo = false;
			}
		}

		// Se o valor não foi encontrado na árvore
		if (atual == null) {
			return false;
		}

		// Caso 1: O nó não tem filho esquerdo (pode ter 0 ou 1 filho à direita)
		if (atual.esquerdo == null) {
			if (atual == raiz) {
				raiz = atual.direito;
			} else if (filhoEsquerdo) {
				pai.esquerdo = atual.direito;
			} else {
				pai.direito = atual.direito;
			}
		}

		// Caso 2: O nó não tem filho direito (tem 1 filho à esquerda)
		else if (atual.direito == null) {
			if (atual == raiz) {
				raiz = atual.esquerdo;
			} else if (filhoEsquerdo) {
				pai.esquerdo = atual.esquerdo;
			} else {
				pai.direito = atual.esquerdo;
			}
		} 
		// Caso 3: O nó possui dois filhos
		else {
			// Encontrar o sucessor (o menor valor na sub-árvore direita)
			No sucessorPai = atual;
			No sucessor = atual.direito;
			
			while (sucessor.esquerdo != null) {
				sucessorPai = sucessor;
				sucessor = sucessor.esquerdo;
			}

			// Copia o valor do sucessor para o nó atual
			atual.valor = sucessor.valor;

			// Remove o sucessor físico da posição original dele
			// Como o sucessor nunca tem filho esquerdo, recaímos nos casos 1 ou 2 simples:
			if (sucessorPai == atual) {
				sucessorPai.direito = sucessor.direito;
			} else {
				sucessorPai.esquerdo = sucessor.direito;
			}
		}

		tamanhoAtual--;
		return true;
	}

	@Override
    public int consultarIntervalo(int min, int max) {
		if (raiz == null) {
			return 0;
		}

		int count = 0;
		java.util.Deque<No> pilha = new java.util.ArrayDeque<>();
		pilha.push(raiz);

		while (!pilha.isEmpty()) {
			No atual = pilha.pop();

			// 1. Verifica se o valor do nó atual está dentro do intervalo [min, max]
			if (atual.valor >= min && atual.valor <= max) {
				count++;
			}

			// 2. Otimização da ABB: Só vai para a esquerda se houver chance de ter valores >= min
			if (atual.valor > min && atual.esquerdo != null) {
				pilha.push(atual.esquerdo);
			}

			// 3. Otimização da ABB: Só vai para a direita se houver chance de ter valores <= max
			if (atual.valor < max && atual.direito != null) {
				pilha.push(atual.direito);
			}
		}

		return count;
	}

	@Override
	public void limpar() {
		raiz = null;
		tamanhoAtual = 0;
	}

	@Override 
	public int tamanho() {
		return tamanhoAtual;
	}


	private No encontrarMenor(No no) {
		while (no.esquerdo != null) {
			no = no.esquerdo;
		}
		
		return no;
	}
	
}