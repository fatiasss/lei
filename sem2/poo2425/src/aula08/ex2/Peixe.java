package aula08.ex2;

public class Peixe extends Alimento {
    private String tipo;
    private double proteins;
    private boolean vegetarian = false;

    public Peixe(double peso, double calorias, String tipo, double proteins){
        super(peso, calorias);
        this.tipo = tipo;
        this.proteins = proteins;
    }

    public String getType(){
        return tipo;
    }
    public double getProteins(){
        return proteins;
    }
    public boolean getVegetarian(){
        return vegetarian;
    }
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("\nPeixe : " + this.getType() + "; ");
        retString.append("Peso: " + this.getPeso() + "; ");
        retString.append("Calorias: " + this.getCalorias() + "; ");
        retString.append("Proteinas: " + this.getProteins() + "; ");
        return retString.toString();

    }
    public boolean equals(Object object){
        Peixe newobject = (Peixe) object;
        return super.equals(object) && newobject.getType() == this.getType() && newobject.getProteins()==this.getProteins();
    }
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) this.getPeso();
        hash = 31 * hash + (int) this.getCalorias();
        hash = 31 * hash + (int) this.getProteins();
        hash = 31 * hash + (this.getType() == null ? 0 : this.getType().hashCode());
        return hash;
    }

    
}
