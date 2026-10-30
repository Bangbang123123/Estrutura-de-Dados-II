package tree;

public class MainArvoreBusca {

	public static void main(String[] args) {
		ArvoreBinariaBusca arvore = new ArvoreBinariaBusca();
		
		arvore.inserir(50);
		arvore.inserir(30);
		arvore.inserir(70);
		arvore.inserir(20);
		arvore.inserir(40);
		arvore.inserir(60);
		arvore.inserir(80);
		
		arvore.emOrdem();
		
		for (int i = 0; i <= 100; i += 10) {
			if (arvore.buscar(i)){
				System.out.println("Existe "+ i);
			} else {
				System.out.println("Não existe "+ i);
			}
		}
		
		arvore.remover(40);
		System.out.println("Percurso por nível:");
		arvore.mostrarPorNivel();
		System.out.println("Quantidade: "+ arvore.quantidadeNo());
		arvore.maior_e_menor();
	}

}
