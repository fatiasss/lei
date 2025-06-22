package aula08.ex1;

public class AutomovelLigeiro extends Automovel{
    private int numeroquadro;
    private int capacidade;

    public AutomovelLigeiro(String matricula, String marca, String modelo, int potencia, int numeroquadro, int capacidade){
        super(matricula, marca, modelo, potencia);
        this.numeroquadro = numeroquadro;
        this.capacidade = capacidade;
    }
    public int getQuadro() {
        return numeroquadro;
    }
    public void setQuadro(int quadro) {
        this.numeroquadro = quadro;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder(super.toString());
    
        retString.append("Número do Quadro: " + numeroquadro + "\n");
        retString.append("Capacidade: " + capacidade + "\n");
    
        return retString.toString();
    }
    public boolean equals(Object object){
        AutomovelLigeiro newobject = (AutomovelLigeiro) object;
        return super.equals(object) && newobject.getQuadro() == this.getQuadro() && newobject.getCapacidade()==this.getCapacidade();
    }
}
