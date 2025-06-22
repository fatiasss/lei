package aula08.ex2;

public class Carne extends Alimento {
    private String variety;
    private double proteins;
    private boolean vegetarian = false; 

    public Carne(double peso, double calorias, String variety, double proteins){
        super(peso, calorias);
        this.variety = variety;
        this.proteins = proteins;
    }

    public String getVariety(){
        return variety;
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
        retString.append("\nCarne : " + this.getVariety() + "; ");
        retString.append("Peso: " + this.getPeso() + "; ");
        retString.append("Calorias: " + this.getCalorias() + "; ");
        retString.append("Proteinas: " + this.getProteins() + "; ");
        return retString.toString();

    }
    public boolean equals(Object object){
        Carne newobject = (Carne) object;
        return super.equals(object) && newobject.getVariety() == this.getVariety() && newobject.getProteins()==this.getProteins();
    }
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) this.getPeso();
        hash = 31 * hash + (int) this.getCalorias();
        hash = 31 * hash + (int) this.getProteins();
        hash = 31 * hash + (this.getVariety() == null ? 0 : this.getVariety().hashCode());
        return hash;
    }

    
    
}
