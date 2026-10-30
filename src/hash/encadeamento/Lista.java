package hash.encadeamento;

public class Lista {
	private class Nodo {
		// Atributos
		private String chave;
		private String valor;
		private Nodo prox;
		
		// Construtor
		public Nodo(String chave, String valor) {
			this.chave = chave;
			this.valor = valor;
			this.prox = null;
		}

		// Getters
		public String getChave() {
			return chave;
		}
		public String getValor() {
			return valor;
		}
		public Nodo getProx() {
			return prox;
		}

		// Setters
		public void setChave(String chave) {
			this.chave = chave;
		}
		public void setValor(String valor) {
			this.valor = valor;
		}
		public void setProx(Nodo prox) {
			this.prox = prox;
		}
	}
	
	private Nodo inicio;
	
	public Lista(String chave, String valor) {
		this.inicio = new Nodo(chave,valor);
	}
	
	public void atualizarOuAdicionar(String chave, String valor) {
		Nodo aux = inicio;
		
		while (aux != null) {
			if (aux.getChave().equals(chave)) {
				aux.setValor(valor);
				return;
			}
			aux = aux.getProx();
		}
		
		Nodo novoNodo = new Nodo(chave,valor);
		novoNodo.setProx(inicio);
		inicio = novoNodo;
	}
	
	public String buscaPorChave(String chave) {
		Nodo aux = inicio;
		
		while (aux != null) {
			if (aux.getChave().equals(chave)) {
				return aux.getValor();
			}
			
			aux = aux.getProx();
		}
		
		return null;
	}
	
	public void removerChave(String chave) {
		if (inicio == null) return;
		if (inicio.getChave().equals(chave)) {
			inicio = inicio.prox;
			return;
		}
		
		Nodo aux = inicio;
		
		while (aux.getProx() != null) {
			if (aux.getProx().getChave().equals(chave)) {
				aux.setProx(aux.getProx().getProx());
				return;
			}
			
			aux = aux.getProx();
		}
		
	}
	
	public String toString() {
		String content = "";
		
		Nodo aux = inicio;
		while (aux != null) {
			content += "   \""+ aux.getChave() +"\":\""+ aux.getValor() +"\",\n";
			aux = aux.getProx();
		}
		return content.subSequence(0, content.length() - 2).toString();
	}
}
