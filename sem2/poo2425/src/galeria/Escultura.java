package galeria;

public class Escultura extends Obra {
    private String material;
    private int quantity;
    public Escultura(String name, String author, double baseprice, String material, int quantity){
        super();
        if(materialValidate(material) && quantityValidate(quantity)){
            this.name=name;
            this.author = author;
            this.baseprice= baseprice;
            this.id = currentid;
            currentid+=1;
            this.material = material;
            this.quantity= quantity;
        }
        else{
            System.out.print("Invalid Escultura");
        }      
}
protected boolean materialValidate(String material){
    if (material.equals("madeira") || material.equals("cerâmica") || material.equals("metal")) {
        return true;
    } else {
        throw new IllegalArgumentException("Illegal material type! " + material);
    }

}
protected boolean quantityValidate(int quantity){
    if(quantity>0){return true;}
    else{throw new IllegalArgumentException("There must be at least 1 specimen");}
}
@Override
public String toString(){
return ("Nome: " + name + "\nAutor: " + author + "\nPreço: " + Double.toString(baseprice) + "\n Id: " + Integer.toString(id)+ "\nMaterial: " + material + "\nQuantidade: " + Integer.toString(quantity) + "\n\n");
}
}
