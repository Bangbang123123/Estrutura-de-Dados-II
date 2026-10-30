package hash;

public class HashTable1 {
	private class Entrada {
		private String chave;
		private String valor;
		
		public Entrada(String chave, String valor) {
			this.chave = chave;
			this.valor = valor;
		}
	}
	
	private int tam_array;
	private Entrada[] array;
	
	public HashTable1(int tam_array) {
		this.tam_array = tam_array;
		this.array = new Entrada[tam_array];
	}
	
	private int hash(String chave) {
		int hashCode = 0;
		
		for (byte b : chave.getBytes()) {
			hashCode += b;
		}
		
		return hashCode % tam_array;
	}
	
	public void inserir(String chave, String valor) {
		int index = hash(chave);
		array[index] = new Entrada(chave,valor);
	}
	
	public String recuperar(String chave) {
		int index = hash(chave);
		
		if (array[index] == null) {
			return null;
		} else {
			return array[index].valor;
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
