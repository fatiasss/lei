package galeria;

public class Quadro extends Obra{
    private String tinta;
    private boolean framed;
    private String size;
    public Quadro(String name, String author, double baseprice, String tinta, String size, String emoldurado){
        super();
        if(sizeValidate(size) && paintValidate(tinta)){
            this.name=name;
            this.author = author;
            this.baseprice= baseprice;
            this.id = currentid;
            currentid+=1;
            this.tinta = tinta;
            this.framed= framedConvert(emoldurado);
            this.size = size;
        }
        else{
            System.out.print("Invalid Quadro");
        }



    }
    protected boolean sizeValidate(String size) {
        if (size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL")) {
            return true;
        } else {
            throw new IllegalArgumentException("Illegal size: " + size);
        }
    }
    
    protected boolean paintValidate(String paint) {
        if (paint.equals("óleo") || paint.equals("guache") || paint.equals("aguarela")) {
            return true;
        } else {
            throw new IllegalArgumentException("Illegal paint type: " + paint);
        }
    }
    protected boolean framedConvert(String framed){
        switch (framed) {
            case "s":return true;
            case "n":return false;
            default:throw new IllegalArgumentException("s/n");
        }

    }

    @Override
    public String toString(){
        return ("Nome: " + name + "\nAutor: " + author + "\nPreço: " + Double.toString(baseprice) + "\n Id: " + Integer.toString(id)+ "\nTipo de tinta: " + tinta + "\nTamanho: " + size + "\nEmoldurado: " + Boolean.toString(framed) + "\n\n");
    }
}