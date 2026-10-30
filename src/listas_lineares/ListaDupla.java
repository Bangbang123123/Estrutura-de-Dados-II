package listas_lineares;

public class ListaDupla {
	public NodoDuplo inicio;
	public NodoDuplo fim;
	
	public ListaDupla() {
		inicio = null;
		fim = null;
	}

	public NodoDuplo getInicio() {
		return inicio;
	}
	public NodoDuplo getFim() {
		return fim;
	}

	public void setInicio(NodoDuplo inicio) {
		this.inicio = inicio;
	}
	public void setFim(NodoDuplo fim) {
		this.fim = fim;
	}
	
	public boolean vazia() {
		return inicio == null;
	}
	
	
	public void inserirInicio(int dado) {
		NodoDuplo novoNodo = new NodoDuplo(dado);
		
		if (vazia()) {
			inicio = novoNodo;
			fim = novoNodo;
			return;
		}
		
		novoNodo.setProx(inicio);
		inicio.setAnt(novoNodo);
		inicio = novoNodo;
	}
	
	public void inserirFim(int dado) {
		NodoDuplo novoNodo = new NodoDuplo(dado);
		
		if (vazia()) {
			inicio = novoNodo;
			fim = novoNodo;
			return;
		}
		
		
		novoNodo.setAnt(fim);
		fim.setProx(novoNodo);
		fim = novoNodo;
	}
	
	public void removerValor(int valor) {
		if (vazia()) return;
		
		if (inicio.getDado() == valor) {
			if (inicio == fim) {
				inicio = null;
				fim = null;
				return;
			}
			
			inicio = inicio.getProx();
			inicio.setAnt(null);
			return;
		}
		if (fim.getDado() == valor) {
			
			fim = fim.getAnt();
			fim.setProx(null);
			return;
		}
		
		NodoDuplo aux = inicio.getProx();
		while (aux != null) {
			if (aux.getDado() == valor) {
				aux.getAnt().setProx(aux.getProx());
				aux.getProx().setAnt(aux.getAnt());
				return;
			}
			aux = aux.getProx();
		}
	}
	
	public String maiorMenor() {
		if (vazia()) {
			return "Nenhum elemento\n";
		}
		
		NodoDuplo aux = inicio;
		int maior = inicio.getDado();
		int menor = inicio.getDado();
		
		while (aux != null) {
			if (maior < aux.getDado()) {
				maior = aux.getDado();
			} else if (menor > aux.getDado()) {
				menor = aux.getDado();
			}
			
			aux = aux.getProx();
		}
		return "Maior: ["+ maior +"] // Menor: ["+ menor +"]\n";
	}
	
	public boolean palindromo() {
		return ToString().equals(ToStringReverse());
	}
	
	
	
	public String ToString() {
		if (vazia()) {
			return "Lista Vazia";
		}
		
		NodoDuplo aux = inicio;
		String content = "";
		
		int i = 0;
		while (aux != null) {
			content += "["+ i +"]: " + aux.getDado() + "\n";
			aux = aux.getProx();
			i++;
		}
		return content;
	}
	
	public String ToStringReverse() {
		if (vazia()) {
			return "Lista Vazia";
		}
		
		NodoDuplo aux = fim;
		String content = "";
		
		int i = 0;
		while (aux != null) {
			content += "["+ i +"]: " + aux.getDado() + "\n";
			aux = aux.getAnt();
			i++;
		}
		return content;
	}
}
