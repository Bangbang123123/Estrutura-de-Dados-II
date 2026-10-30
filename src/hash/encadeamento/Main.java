package hash.encadeamento;

public class Main {

	public static void main(String[] args) {
		HashTable3 tabela = new HashTable3(10000);
		
		long antes = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			tabela.inserir("chave_"+i, "abab");
		}
		long depois = System.currentTimeMillis();
		System.out.println(depois - antes);
	}

}
