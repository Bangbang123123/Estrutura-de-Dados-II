package listas_lineares;

public class NodoDuplo {
	// Attributes
	private int dado;
	private NodoDuplo prox;
	private NodoDuplo ant;
	
	// Constructor
	public NodoDuplo(int dado) {
		setDado(dado);
		setProx(null);
		setAnt(null);
	}
	
	// Getters
	public int getDado() {
		return dado;
	}
	public NodoDuplo getProx() {
		return prox;
	}
	public NodoDuplo getAnt() {
		return ant;
	}
	
	// Setters
	public void setDado(int dado) {
		this.dado = dado;
	}
	public void setProx(NodoDuplo prox) {
		this.prox = prox;
	}
	public void setAnt(NodoDuplo ant) {
		this.ant = ant;
	}
	
	// To String
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NodoDuplo [dado=");
		builder.append(dado);
		builder.append(", prox=");
		builder.append(prox);
		builder.append(", ant=");
		builder.append(ant);
		builder.append("]");
		return builder.toString();
	}
}
