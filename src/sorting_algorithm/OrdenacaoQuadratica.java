package sorting_algorithm;
import java.util.Random;

public class OrdenacaoQuadratica {
	
	
	public static void bubbleSort(int[] vetor, int k) {
		MetricasOrdenacao mo = new MetricasOrdenacao();
		boolean trocou;
		
		if (k > vetor.length) k = vetor.length;
		else if (k < 0) k = 0;
		
		int temp;
		for (int i = 0; i < k-1; i++) {
			trocou = false;
			for (int j = 0; j < k-1-i; j++) {
				mo.comparacoes += 1;
				if (vetor[j] > vetor[j+1]) {
					mo.trocas += 1;
					temp = vetor[j];
					vetor[j] = vetor[j+1];
					vetor[j+1] = temp;
					trocou = true;
				}
			}
			if (!trocou) break;
		}
		
		System.out.println(mo.toString());
	}
	
	public static void selectionSort(int[] vetor, int k) {
		
		MetricasOrdenacao mo = new MetricasOrdenacao();
		
		if (k > vetor.length) k = vetor.length;
		else if (k < 0) k = 0;
		
		int temp;
		int menor;
		for (int i = 0; i < k-1; i++) {
			menor = i;
			
			for (int j = i; j < k; j++) {
				mo.comparacoes += 1;
				if (vetor[menor] > vetor[j]) {
					menor = j;
				}
			}
			
			if (i != menor) {
				mo.trocas += 1;
				temp = vetor[i];
				vetor[i] = vetor[menor];
				vetor[menor] = temp;
			}
			
		}
		System.out.println(mo.toString());
	}
	
	public static void insertionSort(int[] vetor, int k) {
		MetricasOrdenacao mo = new MetricasOrdenacao();
		
		if (k > vetor.length) k = vetor.length;
		else if (k < 0) k = 0;
		
		for (int i = 1; i < k; i++) {
			int chave = vetor[i];
			int j = i - 1;
			while (j >= 0 && vetor[j] > chave) {
				mo.comparacoes += 1;
				mo.trocas += 1;
				vetor[j+1] = vetor[j];
				j--;
			}
			mo.comparacoes += 1;
			mo.trocas += 1;
			
			vetor[j+1] = chave;
		}
		System.out.println(mo.toString()+"\n");
	}
	
	public static void cleanAndOrder(int[] vetor) {

		int jumps = 0;
		for (int i = 0; i < vetor.length; i++) {
			if (i + jumps >= vetor.length) {
				vetor[i] = -999;
			} else {
				if (vetor[i+jumps] == -999)
					jumps++;
					if (i + jumps >= vetor.length)
						continue;
				vetor[i] = vetor[i+jumps];
			}
		}
		printArray(vetor);
		boolean trocou;
		jumps = vetor.length - jumps;
		
		int temp;
		for (int i = 0; i < jumps-1; i++) {
			trocou = false;
			for (int j = 0; j < jumps-1-i; j++) {
				if (vetor[j] > vetor[j+1]) {
					temp = vetor[j];
					vetor[j] = vetor[j+1];
					vetor[j+1] = temp;
					trocou = true;
				}
			}
			if (!trocou) break;
		}
	}
	
	
	public static void printArray(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + " // ");
		}
		System.out.println(array[array.length - 1]);
	}
	
	
	public static void main(String[] args) {
		int size = 100;
		int[] dados = {22, 24, -999, 23, 25, -999, 21, 26};
		
		printArray(dados);
		cleanAndOrder(dados);
		printArray(dados);
		
		/*
		int[] dados = new int[size];
		Random rand = new Random();
		
		
		for (int i = 0; i < size; i++) {
			dados[i] = rand.nextInt(0, 1000);
		}
		
		int k = 20;
		
		int[] dadosBubble = dados.clone();
		bubbleSort(dadosBubble,k);
		
		int[] dadosSelection = dados.clone();
		selectionSort(dadosSelection,k);
		
		int[] dadosInsertion = dados.clone();
		insertionSort(dadosInsertion,k);
		
		printArray(dados);
		printArray(dadosBubble);
		*/
	}
}
