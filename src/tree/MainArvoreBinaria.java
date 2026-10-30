package tree;

public class MainArvoreBinaria {

	public static void main(String[] args) {
		ArvoreBinaria arvore = new ArvoreBinaria();
		
		ArvoreBinaria.No noA = arvore.criarRaiz('8');
		
		ArvoreBinaria.No noB = arvore.adicionarEsquerda(noA, '3');
		ArvoreBinaria.No noC = arvore.adicionarDireita(noA, '4');
		
		
		ArvoreBinaria.No noD = arvore.adicionarEsquerda(noB, '9');
		ArvoreBinaria.No noE = arvore.adicionarEsquerda(noC, '2');
		ArvoreBinaria.No noF = arvore.adicionarDireita(noC, '7');
		
		System.out.println("Pré-ordem: ");
		arvore.preOrdem();
		
		System.out.println("Em ordem: ");
		arvore.emOrdem();
		
		System.out.println("Pós-ordem: ");
		arvore.posOrdem();
	}

}
