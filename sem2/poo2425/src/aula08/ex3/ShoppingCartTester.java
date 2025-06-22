package aula08.ex3;

public class ShoppingCartTester {
    public static void main(String[] args) {
        prod p1 = new prod("Camisolas", 10, 3);
        prod p2 = new prod("Calças", 30, 1);
        prod p3 = new prod("Sapatilhas", 50, 2, 50);
        prod p4 = new prod("Casacos", 100, 1, 10);
        
        Carrinho carrinho = new Carrinho();
        carrinho.adicionarProduto(p1, 1);
        carrinho.adicionarProduto(p2, 5);
        carrinho.adicionarProduto(p3, 2);
        carrinho.adicionarProduto(p4, 1);

        carrinho.listarProdutos();
        System.out.printf("Preço total da compra %.2f\n", carrinho.calcularTotal());
    }
}

