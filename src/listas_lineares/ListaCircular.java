package listas_lineares;

public class ListaCircular<T> {
	// Attributes
	private Nodo<T> inicio;
	private Nodo<T> fim;
	
	// Constructor
	public ListaCircular() {
		this.inicio = null;
		this.fim = null;
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
	
	
	public void addFirst(T valor) {
		Nodo<T> novo_nodo = new Nodo<T>(valor);
		
		if (inicio == null) {
			inicio = novo_nodo;
			fim = novo_nodo;
			fim.setProx(inicio);
			return;
		}
		
		novo_nodo.setProx(inicio);
		inicio = novo_nodo;
		fim.setProx(inicio);
	}
	
	public void addLast(T valor) {
		
		System.out.println("including "+ valor);
		Nodo<T> novo_nodo = new Nodo<T>(valor);
		
		if (inicio == null) {
			inicio = novo_nodo;
			fim = novo_nodo;
			fim.setProx(inicio);
			return;
		}
		
		fim.setProx(novo_nodo);
		fim = novo_nodo;
		fim.setProx(inicio);
		
	}
	
	public void removeValue(T valor) {
		if (inicio == null) {
			return;
		} else if (inicio.getDado().equals(valor)) {
			if (inicio == fim) {
				inicio = null;
				fim = null;
				return;
			}
			inicio = inicio.getProx();
			fim.setProx(inicio);
			return;
		}
		
		Nodo<T> search_nodo = inicio;
		while (search_nodo.getProx() != inicio) {
			if (search_nodo.getProx().getDado().equals(valor)) {
				search_nodo.setProx(search_nodo.getProx());
				
				if (search_nodo.getProx() == fim) {
					fim = search_nodo;
					fim.setProx(inicio);
				} else {
					search_nodo.setProx(search_nodo.getProx().getProx());
				}
				
				
			}
			search_nodo = search_nodo.getProx();
		}
	}
	
	
	
	public int tamanho() {
		if (inicio == null) {
			return 0;
		}
		
		Nodo<T> search_nodo = inicio;
		int tamanho = 0;
		while (search_nodo != fim) {
			tamanho++;
			search_nodo = search_nodo.getProx();
		}
		return tamanho + 1;
	}
	
	public Nodo<T> imprimirMeio() {
		if (inicio == null) {
			return null;
		}
		
		Nodo<T> tartaruga = inicio;
		Nodo<T> lebre = inicio;
		
		int aux = 0;
		while (lebre != fim) {
			aux++;
			lebre = lebre.getProx();
			if (aux % 2 == 0) {
				tartaruga = tartaruga.getProx();
			}
		}
		return tartaruga;
	}
	
	public void transformarEmCircular(Lista<T> list) {
		
		Nodo<T> nodo_inicio = list.getNodoStart();
		Nodo<T> nodo_fim = list.get(list.size()-1);
		
		nodo_fim.setProx(nodo_inicio);
		setInicio(nodo_inicio);
		setFim(nodo_fim);
	}
	
	public void concatenar(ListaCircular<T> outra_lista) {
		fim.setProx(outra_lista.getInicio());
		fim = outra_lista.getFim();
		fim.setProx(inicio);
	}
	
	public void inverter() {
		Nodo<T> aux = inicio;
		Nodo<T> aux_2 = aux.getProx();
		Nodo<T> aux_3 = aux_2.getProx();
		
		inicio.setProx(fim);
		
		while (aux_2 != inicio) {
			
			aux_2.setProx(aux);
			
			aux = aux_2;
			aux_2 = aux_3;
			aux_3 = aux_3.getProx();
		}
		
		aux = inicio;
		inicio = fim;
		fim = aux;
	}
	
	public T batataQuente(int passes) {
		Nodo<T> next_nodo = inicio;
		
		while (inicio != fim) {
			for (int i = 0; i < passes -1; i++) {
				next_nodo = next_nodo.getProx();
			}
			
			
			if (next_nodo.getProx() == inicio) {
				inicio = next_nodo.getProx().getProx();
			}
			if (next_nodo.getProx() == fim) {
				fim = next_nodo;
			}
			
			System.out.println("Nodo ["+ next_nodo.getProx().getDado() +"] removido");
			next_nodo.setProx(next_nodo.getProx().getProx());
			System.out.println(toString());
			
			next_nodo = next_nodo.getProx();
		}
		
		return next_nodo.getDado();
	}
	
	public String toString() {
		String content = "";
		
		Nodo<T> search_nodo = inicio;
		
		if (search_nodo == null) {
			return "";
		}
		
		int i = 0;
		do {
			content += "["+ i +"]: " + search_nodo.getDado() +"\n";
			search_nodo = search_nodo.getProx();
			i++;
		} while (search_nodo != inicio);
			
		
		return content;
	}
}
