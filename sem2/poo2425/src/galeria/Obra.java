package galeria;

public abstract class Obra {
    protected String name;
    protected String author;
    protected double baseprice;
    protected int id;
    protected static int currentid = 33;

    public String getName(){
        return name;
    }
    public String getAuthor(){
        return author;
    }
    public double getBasePrice(){ 
        return baseprice;
    }
    public int getId(){
        return id;
    }
    public String toString(){
        return ("Nome: " + name + "\nAutor: " + author + "\nPreço: " + Double.toString(baseprice) + "\n Id: " + Integer.toString(id) + "\n\n");
    }
       
    


    
}
