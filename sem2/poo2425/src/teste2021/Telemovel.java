package teste2021;

import java.util.HashSet;

public class Telemovel extends Produto{
    private String code_init = "T";
    static int total_amount = 0;
    private String marca;
    private String model;
    private double iva = 1.23;
    private HashSet<String> notes = new HashSet<>();

    public Telemovel(String marca, String model, double basePrice){
        super(basePrice);
        this.marca = marca;
        this.model = model;
    }

    public void addNota(String nota){
        this.notes.add(nota);
    }

    public String getDescricao(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.marca.toUpperCase()).append("/").append(this.model);
        return retString.toString();
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Telemóvel ").append(this.marca.toUpperCase()).append("/").append(this.model);
        return retString.toString();
    }


}
