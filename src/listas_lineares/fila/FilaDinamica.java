package listas_lineares.fila;

import listas_lineares.Nodo;

public class FilaDinamica<T> implements IFila<T>{
	// Attributes
	private Nodo<T> inicio;
	private Nodo<T> fim;
	
	// Constructor
	public FilaDinamica() {
		inicio = null;
		fim = null;
	}
	
	
	// Getters
	public Nodo<T> getInicio() {
		return inicio;
	}
	public Nodo<T> getFim() {
		return fim;
	}


	// Setters
	public void setInicio(Nodo<T> inicio) {
		this.inicio = inicio;
	}
	public void setFim(Nodo<T> fim) {
		this.fim = fim;
	}

	// Methods
	@Override
	public void enqueue(T dado) {
		Nodo<T> novoNodo = new Nodo<T>(dado);
		
		if (vazia()) {
			inicio = novoNodo;
			fim = novoNodo;
			return;
		}
		
		fim.setProx(novoNodo);
		fim = novoNodo;
		
	}

	@Override
	public T dequeue() {
		if (vazia()) {
			return null;
		}
		
		T dado = inicio.getDado();
		inicio = inicio.getProx();
		
		if (inicio == null) {
			fim = null;
		}
		
		return dado;
	}

	@Override
	public T peek() {
		if (vazia()) {
			return null;
		}
		return inicio.getDado();
	}

	@Override
	public boolean vazia() {
		return inicio == null;
	}
	
	@Override
	public int getTamanho() {
		if (vazia()) {
			return 0;
		}
		
		Nodo<T> aux = inicio;
		int count = 0;
		while (aux != null) {
			aux = aux.getProx();
			count++;
		}
		
		return count;
	}

	@Override
	public String ToString() {
		if (vazia()) {
			return "Fila vazia";
		}
		
		String content = "";
		
		Nodo<T> aux = inicio;
		int i = 0;
		while (aux != null) {
			content += "["+ i +"]: "+ aux.getDado() +"\n";
			
			aux = aux.getProx();
			i++;
		}
		
		return content;
	}
	
}
