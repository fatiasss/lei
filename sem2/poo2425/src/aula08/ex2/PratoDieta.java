package aula08.ex2;

import java.util.ArrayList;

public class PratoDieta extends Prato{
    private ArrayList<Alimento> comp = new ArrayList<>();
    

    public PratoDieta(String name){
        super(name);
    }
    @Override
    public void addAlimento(Alimento a){
        if(a.getVegetarian()){comp.add(a);}
        else{
            System.out.print("Not vegetarian");
        }
    }
    
    
}
