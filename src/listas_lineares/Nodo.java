package listas_lineares;

public class Nodo<T> {
	// Attributes
    private T dado;
	private Nodo<T> prox;
	
	// Constructor
	public Nodo(T dado) {
		this.dado = dado;
		this.prox = null;
	}
	
	// Getters
	public T getDado() {
		return dado;
	}
	public Nodo<T> getProx() {
		return prox;
	}
	
	// Setters
	public void setDado(T dado) {
		this.dado = dado;
	}
	public void setProx(Nodo<T> prox) {
		this.prox = prox;
	}
}
