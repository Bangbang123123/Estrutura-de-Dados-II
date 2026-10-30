package listas_lineares.fila;

import listas_lineares.Caixa;
import listas_lineares.pilha.IPilha;
import listas_lineares.pilha.PilhaDinamica;

public class Main {
	public static void main(String[] args) {
		FilaDinamica<Integer> filaD = new FilaDinamica<Integer>();
		
		filaD.enqueue(10);
		filaD.enqueue(20);
		filaD.enqueue(30);
		
		while (!filaD.vazia()) {
			System.out.println("Fila:\n"+ filaD.ToString());
			filaD.dequeue();
		}
		
		FilaEstatica<Integer> filaE = new FilaEstatica<Integer>(3);
		
		filaE.enqueue(10);
		filaE.enqueue(20);
		filaE.enqueue(30);
		
		System.out.println("Fila:\n"+ filaE.ToString());
		inverterFila(filaE);
		System.out.println("Fila:\n"+ filaE.ToString());
		
		
		
		Caixa caixa = new Caixa(true);
		
		for (int i = 0; i < 20; i++) {
			System.out.println(caixa.clienteChegou(i));
		}
		
	}
	
	
	public static <T> void inverterFila(IFila<T> fila) {
		IPilha<T> aux = new PilhaDinamica<T>();
		
		while (!fila.vazia()) {
			aux.push(fila.dequeue());
		}
		while (!aux.vazia()) {
			fila.enqueue(aux.pop());
		}
	}
}
