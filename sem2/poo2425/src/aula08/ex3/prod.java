package aula08.ex3;
import java.lang.StringBuilder;

public class prod {
    private String nome;
    private double preco;
    private int stock;
    private double desconto;

    public prod(String nome, double preco, int stock){
        this.nome=nome;
        this.preco=preco;
        this.stock=stock;
        this.desconto=0;
    }
    public prod(String nome, double preco, int stock, double desconto){
        this.nome=nome;
        this.preco=preco;
        this.stock=stock;
        this.desconto=desconto;
    }
    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco - preco*(desconto/100);
    }
    public int getQuantidade(){
        return stock;
    }
    public void adicionarQuantidade(int quantidade){
        stock+=quantidade;
    }
    public void removerQuantidade(int quantidade){
        stock-=quantidade;
    }
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.getNome() + "\n");
        retString.append("Preço: " + this.getPreco() + "\n");
        retString.append("Quantidade: " + this.getQuantidade() + "\n");
        return retString.toString();

    }
    
}
