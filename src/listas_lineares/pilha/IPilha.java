package listas_lineares.pilha;

public interface IPilha<T> {
	public void push(T dado);
	public T pop();
	public T peek();
	public boolean vazia();
	public String ToString();
}
