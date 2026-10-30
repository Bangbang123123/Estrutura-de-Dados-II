package listas_lineares;

import listas_lineares.fila.*;

public class Caixa {
	IFila<Integer>[] caixas;
	
	
	@SuppressWarnings("unchecked")
	public Caixa(boolean dinamico) {
		if (dinamico) {
			this.caixas = (IFila<Integer>[]) new FilaDinamica[3];
			
			for (int i = 0; i < 3; i++) {
	            this.caixas[i] = new FilaDinamica<Integer>();
	        }
		} else {
			this.caixas = (IFila<Integer>[]) new FilaEstatica[3];
			
			for (int i = 0; i < 3; i++) {
	            this.caixas[i] = new FilaEstatica<Integer>(10);
	        }
		}
	}
	
	public String clienteChegou(int idCliente) {
		
		int menor = 0;
		int[] tamanhos = new int[3];
		for (int i = 0; i < 3; i++) {
			tamanhos[i] = caixas[i].getTamanho();
			if (tamanhos[i] < tamanhos[menor]) {
				menor = i;
			}
		}
		
		caixas[menor].enqueue(idCliente);
		tamanhos[menor]++;
		
		String content = "";
		for (int i = 0; i < 3; i++) {
			content += "["+ i +"] ("+ tamanhos[i] +"): {\n"+ caixas[i].ToString() +"\n}\n";
		}
		return content;
	}
}
