package arvores;


public class ArvoreBinariaBusca {

	public class No {
		private int valor;
		private No esquerdo;
		private No direito;
		
		
		public No(int valor) {
			this.valor = valor;
			direito = esquerdo = null;
		}
		
	}
	
	No raiz;
	
	public void inserir(int valor) {
		raiz = inserir(raiz, valor);
	}
	
	public No inserir(No no, int valor) {
		if (no == null) {
			no = new No(valor);
			return no;
		}
		
		if (valor < no.valor) {
			no.esquerdo = inserir(no.esquerdo, valor);
		} else if (valor > no.valor) {
			no.direito = inserir(no.direito, valor);
		} else {
			System.out.println("O valor "+valor+" ja existe na arvore");
		}
		
		return no;
	}
	
	public void emOrdem() {
		emOrdem(raiz);
	}
	
	private void emOrdem(No no) {
		if (no != null) {
			emOrdem(no.esquerdo);
			System.out.println(no.valor);
			emOrdem(no.direito);
		}
	}
	
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
	
	public void remover(int valor) {
		if (!buscar(valor)) {
			System.out.println("Valor "+valor+" nao encontrado na arvore");
			return;
		}
		
		raiz = remover(raiz, valor);
	}
	
	private No remover(No no, int valor) {
		// Procurando pelo No
		
		if (valor < no.valor) {
			no.esquerdo = remover(no.esquerdo, valor);
		} else if (valor > no.valor) {
			no.direito = remover(no.direito, valor);
		} else {
			// Caso 1: No folha
			if (no.esquerdo == null && no.direito == null) {
				return null;
			}
			
			//Caso 2: possui somente um filho
			if (no.esquerdo == null) {
				return no.direito;
			} else if (no.direito == null) {
				return no.esquerdo;
			}
			
			// Caso 3: possui filhos
			No sucessor = encontrarMenor(no.direito);
			
			// Copia o valor do sucessor
			no.valor = sucessor.valor;
			
			// Remover o sucessor da posicao original
			no.direito = remover(no.direito, sucessor.valor);
		}
		
		return no;
	}
	
	private No encontrarMenor(No no) {
		while (no.esquerdo != null) {
			no = no.esquerdo;
		}
		
		return no;
	}
	
}