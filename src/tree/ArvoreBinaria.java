package tree;

public class ArvoreBinaria {
	
	public class No {
		private char valor;
		private No esquerdo;
		private No direito;
		
		public No(char valor) {
			this.valor = valor;
		}
	}
	
	private No raiz;
	
	public No getRaiz() {
		return raiz;
	}
	
	public No criarRaiz(char valor) {
		raiz = new No(valor);
		return raiz;
	}
	
	public No adicionarEsquerda(No pai, char valor) {
		if (pai == null) {
			System.out.println("Pai não existente");
			return null;
		}
		
		if (pai.esquerdo != null) {
		   System.out.println("Nó pai já possui filho esquerdo");
		   return null;
		}
		
		pai.esquerdo = new No(valor);
		return pai.esquerdo;
	}
	
	public No adicionarDireita(No pai, char valor) {
		if (pai == null) {
			System.out.println("Pai não existente");
			return null;
		}
		
		if (pai.direito != null) {
		   System.out.println("Nó pai já possui filho esquerdo");
		   return null;
		}
		
		pai.direito = new No(valor);
		return pai.direito;
	}
	
	public void preOrdem() {
		preOrdem(raiz);
		System.out.println();
	}
	
	private void preOrdem(No no) {
		if (no == null) {
			return;
		}
		
		System.out.println(no.valor +" ");
		preOrdem(no.esquerdo);
		preOrdem(no.direito);
	}
	
	public void emOrdem() {
		emOrdem(raiz);
		System.out.println();
	}
	
	private void emOrdem(No no) {
		if (no == null) {
			return;
		}
		
		emOrdem(no.esquerdo);
		System.out.println(no.valor +" ");
		emOrdem(no.direito);
	}
	
	public void posOrdem() {
		posOrdem(raiz);
		System.out.println();
	}
	
	private void posOrdem(No no) {
		if (no == null) {
			return;
		}
		
		posOrdem(no.esquerdo);
		posOrdem(no.direito);
		System.out.println(no.valor +" ");
	}
}
