package listas_lineares;

import comparacao.EstruturaDados;

public class ListaDupla implements EstruturaDados{
	public NodoDuplo inicio;
	public NodoDuplo fim;
	private int tamanhoAtual = 0;
	
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

	@Override
	public void limpar() {
		inicio = null;
		fim = null;
		tamanhoAtual = 0;
	}

	@Override
	public int tamanho() {
		return tamanhoAtual;
	}

	@Override
    public boolean inserir(int valor) {
		if (buscar(valor)) return false;
		tamanhoAtual++;
		return inserirInicio(valor);
	}

	@Override
    public boolean buscar(int valor) {
        NodoDuplo atual = inicio;
        while (atual != null) {
            if (atual.getDado() == valor) {
                return true;
            }
            atual = atual.getProx();
        }
        return false;
    }
    
    @Override
    public int consultarIntervalo(int min, int max) {
        int count = 0;
        NodoDuplo atual = inicio;
        while (atual != null) {
            if (atual.getDado() >= min && atual.getDado() <= max) {
                count++;
            }
            atual = atual.getProx();
        }
        return count;
    }

	
	public boolean inserirInicio(int dado) {
		NodoDuplo novoNodo = new NodoDuplo(dado);
		
		if (vazia()) {
			inicio = novoNodo;
			fim = novoNodo;
			return true;
		}
		
		novoNodo.setProx(inicio);
		inicio.setAnt(novoNodo);
		inicio = novoNodo;
		return true;
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
	
	@Override 
	public boolean remover(int valor) {
		if (vazia()) return false;
		
		if (inicio.getDado() == valor) {
			if (inicio == fim) {
				inicio = null;
				fim = null;
				tamanhoAtual--;
				return true;
			}
			
			inicio = inicio.getProx();
			inicio.setAnt(null);
			tamanhoAtual--;
			return true;
		}
		if (fim.getDado() == valor) {
			
			fim = fim.getAnt();
			fim.setProx(null);
			tamanhoAtual--;
			return true;
		}
		
		NodoDuplo aux = inicio.getProx();
		while (aux != null) {
			if (aux.getDado() == valor) {
				aux.getAnt().setProx(aux.getProx());
				aux.getProx().setAnt(aux.getAnt());
				tamanhoAtual--;
				return true;
			}
			aux = aux.getProx();
		}

		return false;
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
