package teste2021;

import teste2021.ClasseEnergetica;

public class Electrodomestico extends Produto {
    private String code_init = "E";
    static int total_amount = 0;
    private String type;
    private String marca;
    private String model;
    private ClasseEnergetica eClass;
    private double potencia;

    public Electrodomestico(String type, String marca, String model, double basePrice){
        super(basePrice);
        this.type = type;
        this.marca = marca;
        this.model = model;
        this.potencia = 0;
    }

    public Electrodomestico(String type, String marca, String model, int potencia, double basePrice){
        super(basePrice);
        this.type = type;
        this.marca = marca;
        this.model = model;
        this.potencia = potencia;
    }

    public void setClasse(ClasseEnergetica c){
        this.eClass = c;

    }

     public String getDescricao(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.type).append(":").append(this.marca).append("/").append(this.model);
        return retString.toString();
    }

    @Override

    
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Eletrodoméstico: ").append(this.type).append(":").append(this.marca).append("/").append(this.model);
        return retString.toString();
    }

    

}
