package aula08.ex2;

public class Cereal extends Alimento {
    private String name;
    private double proteins;
    private boolean vegetarian = true;

    public Cereal(double peso, double calorias, String name, double proteins){
        super(peso, calorias);
        this.name = name;
        this.proteins = proteins;
    }

    public String getName(){
        return name;
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
        retString.append("\nCereal; ");
        retString.append("Peso: " + this.getPeso() + "; ");
        retString.append("Calorias: " + this.getCalorias() + "; ");
        retString.append("Proteinas: " + this.getProteins() + "; ");
        return retString.toString();

    }
    public boolean equals(Object object){
        Cereal newobject = (Cereal) object;
        return super.equals(object) && newobject.getName() == this.getName() && newobject.getProteins()==this.getProteins();
    }
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) this.getPeso();
        hash = 31 * hash + (int) this.getCalorias();
        hash = 31 * hash + (int) this.getProteins();
        hash = 31 * hash + (this.getName() == null ? 0 : this.getName().hashCode());
        return hash;
    }

    
    
    
}
