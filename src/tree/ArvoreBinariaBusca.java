package tree;

import java.util.Queue;
import java.util.LinkedList;

public class ArvoreBinariaBusca {

	private class No {
		public int valor;
		public No esq;
		public No dir;
		
		public No(int valor) {
			this.valor = valor;
			dir = esq = null;
		}
	}
	
	No raiz;
	
	public void inserir(int valor) {
		raiz = inserir(raiz,valor);
	}
	
	private No inserir(No no, int valor) {
		if (no == null) {
			no = new No(valor);
			return no;
		}
		
		if (valor < no.valor) {
			no.esq = inserir(no.esq,valor);
		} else if (valor > no.valor) {
			no.dir = inserir(no.dir,valor);
		} else {
			System.out.println("Valor "+ valor +" já existente na árvore");
		}
		
		return no;
	}
	
	public void emOrdem() {
		emOrdem(raiz);
		System.out.println();
	}
	
	private void emOrdem(No no) {
		if (no == null) {
			return;
		}
		
		emOrdem(no.esq);
		System.out.print(no.valor +" ");
		emOrdem(no.dir);
	}
	
	
	public boolean buscar(int valor) {
		No atual = raiz;
		
		while (atual != null) {
			if (valor == atual.valor) {
				return true;
			}
			
			if (valor < atual.valor) {
				atual = atual.esq;
			} else {
				atual = atual.dir;
			}
		}
		
		return false;
	}
	
	public void remover(int valor) {
		if (!buscar(valor)) {
			return;
		}
		
		raiz = remover(raiz,valor);
	}
	
	private No remover(No no, int valor) {
		if (valor < no.valor) {
			no.esq = remover(no.esq,valor);
		} else if (valor > no.valor) {
			no.dir = remover(no.dir,valor);
		} else {
			if (no.esq == null && no.dir == null) {
				return null;
			}
			if (no.esq == null) {
				return no.dir;
			}
			if (no.dir == null) {
				return no.esq;
			}
			
			No sucessor = encontrarMenor(no.dir);
			no.valor = sucessor.valor;
			
			no.dir = remover(no.dir, sucessor.valor);
		}
		
		return no;
	}
	
	private No encontrarMenor(No no) {
		while (no.esq != null) {
			no = no.esq;
		}
		
		return no;
	}
	
	private No encontrarMaior(No no) {
		while (no.dir != null) {
			no = no.dir;
		}
		
		return no;
	}
	
	public void mostrarPorNivel() {
		int altura = 0;
		if (raiz == null) {
			System.out.println("Árvore Vazia");
			return;
		}
		
		Queue<No> fila = new LinkedList<No>();
		fila.add(raiz);
		
		while (!fila.isEmpty()) {
			int nivelSize = fila.size();
			
			for (int i = 0; i < nivelSize; i++) {
				No noAtual = fila.poll();
				if (noAtual != null) {
					System.out.print("["+ noAtual.valor +"]");
					fila.add(noAtual.esq);
					fila.add(noAtual.dir);
				} else {
					System.out.print("[ ]");
				}
			}
			
			altura++;
			System.out.println();
		}
		
		System.out.println("Altura: "+ (altura-1));
	}
	
	public void maior_e_menor() {
		if (raiz == null) {
			System.out.println("Sem valores de maior e menor");
		} else {
			System.out.println("Menor: "+ encontrarMenor(raiz).valor +"\nMaior: "+ encontrarMaior(raiz).valor);
		}
	}
	
	public int quantidadeNo() {
		return 1 + quantidadeNo(raiz.dir) + quantidadeNo(raiz.esq);
	}
	
	public int quantidadeNo(No no) {
		if (no == null) {
			return 0;
		}
		return 1 + quantidadeNo(no.dir) + quantidadeNo(no.esq);
	}
}
