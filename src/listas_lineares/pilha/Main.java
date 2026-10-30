package listas_lineares.pilha;

public class Main {

	public static void main(String[] args) {
		IPilha<Integer> pilha1 = new PilhaDinamica<Integer>();
		pilha1.push(10);
		pilha1.push(20);
		pilha1.push(30);
		
		System.out.println(pilha1.ToString());
		
		System.out.println(PilhaDinamica.inverterTexto("abc"));
		System.out.println(PilhaDinamica.intToBinary(100));
		System.out.println(PilhaDinamica.testOpenEnd("{[{{}}]}"));

	}
	

}
