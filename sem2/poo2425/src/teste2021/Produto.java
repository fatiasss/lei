package teste2021;

public abstract class Produto implements IPVP{
    private String code_init;
    static int total_amount = 0;
    private String code;
    private int amount;
    private double basePrice;
    private double iva;

    public String getCode(){
        StringBuilder retString = new StringBuilder();
        retString.append(code_init).append(total_amount+=2);
        return retString.toString();
    };

    public Produto(double basePrice){
        this.basePrice = basePrice;
        this.code = getCode();
    }

    public double getBasePrice(){
        return this.basePrice;
    }

    public void setStock(int amount){
        this.amount = amount;
    }
    public int getStock(){
        return this.amount;
    }

    public void addStock(int amount){
        this.amount+=amount;
    }

    

    public double precoVendaAoPublico(){
        return basePrice*iva;
    }

    public abstract String getDescricao();

    public boolean vender(int sell_amount){
        if(this.amount<sell_amount){return false;}
        this.amount-=sell_amount;
        return true;
    }



}
