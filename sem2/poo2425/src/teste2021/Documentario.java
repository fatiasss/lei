package teste2021;

public class Documentario extends Produto {
    private String code_init = "D";
    static int total_amount = 0;
    private String code;
    private int amount;
    private double basePrice;
    private String title;
    private int year;
    private int duration;
    private double iva = 1.23;


    public Documentario(String title, double basePrice, int year, int duration){
        super(basePrice);
        this.title = title;
        this.year = year;
        this.duration = duration;
    }

}
