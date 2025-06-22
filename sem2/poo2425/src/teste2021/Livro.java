package teste2021;

import java.util.ArrayList;
import java.util.List;

public class Livro extends Produto {
    private String code_init = "L";
    static int total_amount = 0;
    private String title;
    private ArrayList<Autor> autores = new ArrayList<>();
    private double iva = 1.06;

    public Livro(String title, double basePrice){
        super(basePrice);
        this.title = title;
    }

    public Livro(String title, double basePrice, List<Autor> autores){
        super(basePrice);
        this.title = title;
        this.autores = new ArrayList<>(autores);
    }

    public void add(Autor author){
        autores.add(author);
    }

    public String getTitulo(){
        return this.title;
    }

    public ArrayList<Autor> getLista(){
        return autores;
    }
    public int numeroAutores(){
        return autores.size();
    }

    public String autores(){
        StringBuilder retString = new StringBuilder();
        retString.append("[");
        for(Autor autor : autores){
            retString.append(autor.toString());
        }
        retString.setLength(retString.length() - 1);
        retString.append("]");
        return retString.toString();

    }
    public String getDescricao(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.title);
        return retString.toString();
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Livro ").append(this.title);
        return retString.toString();
    }



}
