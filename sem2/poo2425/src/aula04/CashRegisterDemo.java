package aula04;
import java.util.ArrayList;
import java.lang.StringBuilder;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int  getQuantity() {
        return quantity;
    }
}


class CashRegister {
    private ArrayList<Product> productlist = new ArrayList<>();
    private StringBuilder stringval = new StringBuilder();
    private double totalprice;

    public void addProduct(Product product){
        productlist.add(product);
        totalprice+=product.getTotalValue();

    }
    public String toString(){
        stringval.append(String.format("%-15s %-10s %-10s %-10s\n", "Product", "Price", "Quantity", "Total"));
        for(Product product : productlist){
            stringval.append(String.format("%-15s %-10s %-10s %-10s\n", product.getName(), product.getPrice(), product.getQuantity(),product.getTotalValue()));
        }
        stringval.append("Total Price: " + totalprice);
        return stringval.toString();
    }



}

public class CashRegisterDemo {

    public static void main(String[] args) {

        // Cria e adiciona 5 produtos
        CashRegister cr = new CashRegister();
        cr.addProduct(new Product("Book", 9.99, 3));
        cr.addProduct(new Product("Pen", 1.99, 10));
        cr.addProduct(new Product("Headphones", 29.99, 2));
        cr.addProduct(new Product("Notebook", 19.99, 5));
        cr.addProduct(new Product("Phone case", 5.99, 1));
        
        // TODO: Listar o conteúdo e valor total
        System.out.println(cr.toString());

    }
}