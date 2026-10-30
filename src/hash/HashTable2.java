package hash;

public class HashTable2 {
	private class Entrada {
		private String chave;
		private String valor;
		
		public Entrada(String chave, String valor) {
			this.chave = chave;
			this.valor = valor;
		}
	}
	
	private final double CARGA_MAX = 0.7;
	
	private Entrada[] array;
	private int tamanhoAtual;
	
	public HashTable2(int capacidadeInicial) {
		this.tamanhoAtual = 0;
		this.array = new Entrada[capacidadeInicial];
	}
	
	private int hash(String chave) {
		int hashCode = 0;
		
		for (byte b : chave.getBytes()) {
			hashCode += b;
		}
		
		return hashCode % array.length;
	}
	
	public void inserir(String chave, String valor) {
		
		if ((double) tamanhoAtual/array.length >= CARGA_MAX) {
			redimensionar();
		}
		int index_search = hash(chave);
		int index = index_search;
		int colisoes = 0;
		
		while (array[index] != null && !array[index].chave.equals(chave)) {
			colisoes++;
			index = (index_search + colisoes) % array.length;
		}
		
		if (array[index] == null) {
			tamanhoAtual++;
		}
		
		array[index] = new Entrada(chave,valor);
	}
	
	public String recuperar(String chave) {
		int index_search = hash(chave);
		int index = index_search;
		int colisoes = 0;
		
		while (array[index] != null) {
			if (array[index].chave.equals(chave)) {
				return array[index].valor;
			}
			colisoes++;
			index = (index_search + colisoes) % array.length;
		}
		
		return null;
	}
	
	private void redimensionar() {
		Entrada[] antigoArray = array;
		
		array = new Entrada[antigoArray.length * 2];
		
		for (Entrada entrada: antigoArray) {
			if (entrada != null) {
				inserir(entrada.chave,entrada.valor);
			}
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("HashTable:\n");
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				builder.append("[");
				builder.append(i);
				builder.append("]: { null }\n");
			} else {
				builder.append("[");
				builder.append(array[i].chave);
				builder.append("]: { ");
				builder.append(array[i].valor);
				builder.append(" }\n");
			}
		}
		
		return builder.toString();
	}
}
