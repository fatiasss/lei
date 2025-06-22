package aula08.ex3;

import java.util.ArrayList;

public class Carrinho {
    private ArrayList<prod> produtos = new ArrayList<>();
    public void adicionarProduto (prod produto, int quantidade){
        produtos.add(produto);
    }
    public void listarProdutos(){
        for(prod x: produtos){
            System.out.print(x.toString() + "\n");
        }
    }
    public double calcularTotal(){
        double retdouble = 0;
        for(prod x: produtos){
            retdouble+=x.getPreco()*x.getQuantidade();
        }
        return retdouble;
    }
}
