package galeria;

public class ObraDigital extends Obra {
    private String blockchain;
    private String hex;
    public ObraDigital(String name, String author, double baseprice, String blockchain, String hex){
        super();
        if(chainValidate(blockchain) && hexValidate(hex)){
            this.name=name;
            this.author = author;
            this.baseprice= baseprice;
            this.id = currentid;
            currentid+=1;
            this.blockchain = blockchain;
            this.hex= hex;
        }
        else{
            System.out.print("Invalid ObraDigital");
        }

       
    
}
protected boolean chainValidate(String material){
    if(material.equals("Ethereum" )|| material.equals("Solana" ) || material.equals("Polygon" )){
        return true;
    }
    else{
        throw new IllegalArgumentException("Illegal blockchain type!");
    }

}
protected boolean quantityValidate(int quantity){
    if(quantity>0){return true;}
    else{throw new IllegalArgumentException("There must be at least 1 specimen");}
}
protected boolean hexValidate(String hex){
    return hex.matches("0x[0-9A-Fa-f]+");
}
@Override
public String toString(){
    return ("Nome: " + name + "\nAutor: " + author + "\nPreço: " + Double.toString(baseprice) + "\n Id: " + Integer.toString(id)+ "\nBlockchain: " + blockchain + "\nHex: " + hex + "\n\n");
}
}
