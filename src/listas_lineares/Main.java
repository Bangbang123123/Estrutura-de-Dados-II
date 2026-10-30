package listas_lineares;

public class Main {

	public static void main(String[] args) {
		
		
		ListaDupla lista = new ListaDupla();
		
		lista.inserirInicio(4);
		lista.inserirInicio(4);
		lista.inserirInicio(3);
		lista.inserirInicio(4);
		lista.inserirInicio(4);
		
		System.out.print(lista.ToString());
		System.out.print(lista.ToStringReverse());
		if (lista.palindromo()) {
			System.out.println("É palíndromo");
		} else {
			System.out.println("Não é palíndromo");
		}
		
	}
}
