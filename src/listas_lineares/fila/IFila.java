package listas_lineares.fila;

public interface IFila<T> {
	public void enqueue(T dado);
	public T dequeue();
	public T peek();
	public boolean vazia();
	public String ToString();
	public int getTamanho();
}
