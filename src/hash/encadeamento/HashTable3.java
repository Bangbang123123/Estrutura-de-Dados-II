package hash.encadeamento;


public class HashTable3 {
	private int tamArray;
	private Lista[] array;
	
	public HashTable3(int tamArray) {
		this.tamArray = tamArray;
		this.array = new Lista[tamArray];
	}
	
	private int hash(String chave) {
		int hashCode = 0;
		for (byte b:chave.getBytes()) {
			hashCode += b;
		}
		return hashCode % tamArray;
	}
	
	public void inserir(String chave, String valor) {
		int index = hash(chave);
		
		if (array[index] == null) {
			array[index] = new Lista(chave,valor);
		} else {
			array[index].atualizarOuAdicionar(chave, valor);
		}
	}
	
	public String recuperar(String chave) {
		int index = hash(chave);
		
		if (array[index] == null) {
			return null;
		}
		
		return array[index].buscaPorChave(chave);
	}
	
	public void remover(String chave) {
		int index = hash(chave);
		
		if (array[index] != null) {
			array[index].removerChave(chave);
		}
	}
	
	public String toString() {
		String content = "Hash Table:\n";
		
		for (int i = 0; i < tamArray; i++) {
			if (array[i] != null) {
				content += "["+ i +"]{\n"+ array[i].toString() +"\n}\n";
			} else {
				content += "["+ i +"]{}\n";
			}
			
		}
		
		return content;
	}
}
