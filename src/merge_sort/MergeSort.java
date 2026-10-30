package merge_sort;

public class MergeSort {

	public static void quickSort(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}
		quickSortRec(array,0,array.length - 1);
	}
	
	private static void quickSortRec(int[] array, int inicio, int fim) {
		if (inicio < fim) {
			int meio = particao(array,inicio,fim);
			
			quickSortRec(array,inicio,meio);
			quickSortRec(array,meio+1,fim);
		}
	}
	
	private static int particao(int[] array, int i, int j) {
		int meio = (i + j)/2;
		int pivo = array[meio];
		
		while (true) {
			
			while (array[i] < pivo) {
				i++;
			}
			while (array[j] > pivo) {
				j--;
			}
			if (i >= j) {
				return j;
			}
			
			int aux = array[i];
			array[i] = array[j];
			array[j] = aux;
			i++;
			j--;
			
		}
	}
	
	
	public static void mergeSort(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}
		mergeSortRec(array,0,array.length - 1);
	}
	
	private static void mergeSortRec(int[] array, int inicio, int fim) {
		if (inicio < fim) {
			int meio = (inicio + fim)/2;
			mergeSortRec(array,inicio,meio);
			
			mergeSortRec(array,meio+1,fim);
			
			merge(array,inicio,meio,fim);
		}
	}
	
	private static void merge(int[] array, int inicio, int meio, int fim) {
		
		int[] array_left = new int[meio - inicio + 1];
		int[] array_right = new int[fim - meio];
		
		for (int i = 0; i < array_left.length; i++) {
			array_left[i] = array[inicio + i];
		}
		for (int i = 0; i < array_right.length; i++) {
			array_right[i] = array[meio + 1 + i];
		}
		
		int i = 0;
		int j = 0;
		int k = inicio;
		
		while (i < array_left.length && j < array_right.length) {
			if (array_left[i] <= array_right[j]) {
				array[k] = array_left[i];
				i++;
			} else {
				array[k] = array_right[j];
				j++;
			}
			k++;
		}
		
		while (i < array_left.length) {
			array[k] = array_left[i];
			i++;
			k++;
		}
		while (j < array_right.length) {
			array[k] = array_right[j];
			j++;
			k++;
		}
	}
	
	public static void printArray(int[] array, int start, int end) {
		for (int i = start; i < end; i++) {
			System.out.print(array[i] + " // ");
		}
		System.out.println(array[array.length - 1]);
	}
	
	
	public static void main(String[] args) {
		int[] a = {64,34,25,12,22,11,90,5,42,8};
		int[] a_merge = a.clone();
		
		printArray(a_merge,0,a_merge.length - 1);
		
		long start = System.nanoTime();
		mergeSort(a_merge);
		long end = System.nanoTime();
		
		printArray(a_merge,0,a_merge.length - 1);
		System.out.println("Tempo decorrido: "+ (end - start));
		
		start = System.nanoTime();
		quickSort(a);
		end = System.nanoTime();
		
		printArray(a,0,a.length - 1);
		System.out.println("Tempo decorrido: "+ (end - start));
		
		
	}

}
