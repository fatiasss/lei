package aula08.ex2;
import java.lang.Comparable;

public class Alimento implements Comparable{
    private double peso;
    private double calorias;
    private boolean vegetarian;

    public Alimento(double peso, double calorias){
        this.peso = peso;
        this.calorias = calorias;
    }

    public double getPeso(){
        return peso;
    }
    public double getCalorias(){
        return calorias;
    }
        public boolean getVegetarian(){
        return vegetarian;
    }
    public boolean equals(Object object){
        if(object.getClass()!=this.getClass()){return false;}
        Alimento newobject = (Alimento) object;
        if(newobject.getPeso()!=this.getPeso() || newobject.getCalorias()!= this.getCalorias()){
            return false;
        }
        else{return true;}
    }
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) peso;
        hash = 31 * hash + (int) calorias;
        return hash;
    }
    public int compareTo(Object object){
        Alimento newobject = (Alimento) object;
        return Double.compare(this.getCalorias(), newobject.getCalorias());
    }
    
}
