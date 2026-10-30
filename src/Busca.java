import java.util.Arrays;
import java.util.Random;

public class Busca {

	public static int buscaSequencial(int[] array, int valorProcurado) {
		int comp = 0;
		
		for (int i = 0; i < array.length; i++) {
			comp++;
			if (array[i] == valorProcurado) {
				System.out.println("Comparações: "+ comp);
				return i;
			}
		}
		
		System.out.println("Comparações: "+ comp);
		return -1;
	}
	
	
	public static int buscaBinaria(int[] array, int valorProcurado) {
		int comp = 0;
		
		int inicio = 0;
		int fim = array.length - 1;
		int meio = 0;
		
		while (inicio <= fim) {
			comp++;
			meio = (inicio + fim)/2;
			
			comp++;
			if (array[meio] == valorProcurado) {
				System.out.println("Comparações: "+ comp);
				return meio;
			}
			
			comp++;
			if (valorProcurado > array[meio]) {
				inicio = meio + 1;
			} else if (valorProcurado < array[meio]) {
				fim = meio - 1;
			}
		}
		
		System.out.println("Comparações: "+ comp);
		return -meio-1;
	}
	
	
	
	public static void main(String[] args) {
		int[] array = new int[10000];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		
		Random rand = new Random();
		int valorProcurado = 10000;
		
		int posicaoSeq = buscaSequencial(array,valorProcurado);
		int posicaoBin = buscaBinaria(array,valorProcurado);
		
		if (posicaoSeq != -1) {
			System.out.println("Valor ["+ valorProcurado +"]: "+ posicaoSeq +" // "+ posicaoBin);
		} else {
			System.out.println("Valor ["+ valorProcurado +"] não encontrado // "+ posicaoBin);
		}
	}

}
