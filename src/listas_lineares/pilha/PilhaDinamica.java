package listas_lineares.pilha;

import listas_lineares.Nodo;

public class PilhaDinamica<T> implements IPilha<T>{
	// Attributes
	private Nodo<T> topo;
	
	// Constructor
	public PilhaDinamica() {
		topo = null;
	}
	
	// Setter
	public void setTopo(Nodo<T> topo) {
		this.topo = topo;
	}
	
	// Getter
	public Nodo<T> getTopo() {
		return topo;
	}
	
	// Methods
	public void push(T dado) {
		Nodo<T> novo_nodo = new Nodo<T>(dado);
		novo_nodo.setProx(topo);
		topo = novo_nodo;
	}
	
	public T pop() {
		if (vazia()) {
			System.out.print("Pilha vazia");
			return null;
		}
			
		T valor_removido = topo.getDado();
		topo = topo.getProx();
		
		return valor_removido;
	}
	
	public T peek() {
		if (vazia()) {
			System.out.print("Pilha vazia");
			return null;
		}
		
		return topo.getDado();
	}
	
	public boolean vazia() {
		return topo == null;
	}
	
	public static String inverterTexto(String texto) {
		PilhaDinamica<Character> texto_pilha = new PilhaDinamica<Character>();
		
		for (Character c : texto.toCharArray()) {
			texto_pilha.push(c);
		}
		
		String content = "";
		while (texto_pilha.getTopo() != null) {
			content += texto_pilha.pop();
		}
		return content;
	}
	
	public static String intToBinary(int num) {
		PilhaDinamica<Integer> pilha = new PilhaDinamica<Integer>();
		while (num != 0) {
			pilha.push(num % 2);
			num /= 2;
		}
		
		String content = "";
		while (pilha.getTopo() != null) {
			content += pilha.pop();
		}
		return content;
	}
	
	
	public static boolean testOpenEnd(String sequence) {
		PilhaDinamica<Character> pilha = new PilhaDinamica<Character>();
		
		for (Character c : sequence.toCharArray()) {
			switch (c) {
			case '[':
				pilha.push(c);
				break;
			case '{':
				pilha.push(c);
				break;
			case ']':
				if (! pilha.pop().equals('[')) {
					return false;
				}
				break;
			case '}':
				if (! pilha.pop().equals('{')) {
					return false;
				}
				break;
			}
		}
		
		return pilha.vazia();
	}
	
	public String ToString() {
		if (vazia()) {
			return "Pilha Vazia";
		}
		
		String content = "Pilha:\n";
		Nodo<T> aux = topo;
		
		while (aux != null) {
			content += "* "+ aux.getDado() +"\n";
			aux = aux.getProx();
		}
		
		return content;
	}
}
