package aula08.ex2;
import java.util.ArrayList;

public class Prato {
    private String name;
    private ArrayList<Alimento> comp = new ArrayList<>();

    public Prato(String name){
        this.name= name;
    }

    public String getName(){
        return name;
    }
    public void addAlimento(Alimento a){
        comp.add(a);
    }
    public ArrayList<Alimento> getComp(){
        return comp;
    }

    @Override
    public boolean equals(Object object){
        Prato newobject = (Prato) object;
        return super.equals(object) && newobject.getName() == this.getName() && newobject.getComp()==this.getComp();
    }
    public int hashCode() {
        int hash = 7;
        for(Alimento a: comp){
            hash = 31 * hash + (int) a.hashCode();
        }
        return hash;
    }
    public String toString(){
            StringBuilder retString = new StringBuilder();
            for(Alimento a: comp){
                retString.append("\nPrato : " + a.getClass() + "; ");
            }
            return retString.toString();
    }
    
}
