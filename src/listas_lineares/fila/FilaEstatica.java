package listas_lineares.fila;

public class FilaEstatica<T> implements IFila<T> {
	// Attributes
	private int inicio;
	private int fim;
	private int tamanho;
	private int capacidade;
	private T[] dados;
	
	// Constructor
	@SuppressWarnings("unchecked")
	public FilaEstatica(int capacidade) {
		this.capacidade = capacidade;
		dados = (T[]) new Object[capacidade];
		inicio = 0;
		fim = -1;
		tamanho = 0;
	}
	
	// Getters
	public int getInicio() {
		return inicio;
	}
	public int getFim() {
		return fim;
	}
	public int getTamanho() {
		return tamanho;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public T[] getDados() {
		return dados;
	}

	// Setters
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	public void setFim(int fim) {
		this.fim = fim;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public void setDados(T[] dados) {
		this.dados = dados;
	}

	// Methods
	@Override
	public void enqueue(T dado) {
		if (cheia()) {
			System.out.println("Fila cheia");
			return;
		}
		
		fim = (fim + 1) % capacidade;
		dados[fim] = dado;
		tamanho++;
	}

	@Override
	public T dequeue() {
		if (vazia()) {
			System.out.println("Fila vazia");
			return null;
		}
		
		T dado = dados[inicio];
		inicio = (inicio + 1) % capacidade;
		tamanho--;
		return dado;
	}

	@Override
	public T peek() {
		return dados[inicio];
	}

	@Override
	public boolean vazia() {
		return tamanho == 0;
	}
	
	public boolean cheia() {
		return tamanho == capacidade;
	}

	@Override
	public String ToString() {
		if (vazia()) {
			return "Fila vazia";
		}
		
		String content = "";
		for (int i = 0; i < tamanho; i++) {
			content += "["+ i +"]:"+ dados[(i + inicio) % capacidade] +"\n";
		}
		return content;
	}

}
