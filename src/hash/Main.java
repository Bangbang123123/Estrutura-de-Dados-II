package hash;

public class Main {
	public static void main(String[] args) {
		HashTable1 tabela = new HashTable1(5);
	
		tabela.inserir("damn","2974");
		tabela.inserir("pog","2976");
		
		System.out.println(tabela.toString());
		System.out.println(tabela.recuperar("damn"));
		
		HashTable2 tabela1 = new HashTable2(4);
		
		tabela1.inserir("ANA","2974");
		tabela1.inserir("ALICE","2976");
		tabela1.inserir("BRUNO","2974");
		tabela1.inserir("CELIA","2976");
		
		System.out.println(tabela1.toString());
		System.out.println(tabela1.recuperar("damn"));
	}
	
	
	
}
