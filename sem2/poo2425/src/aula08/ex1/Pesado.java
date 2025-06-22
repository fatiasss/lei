package aula08.ex1;

public class Pesado extends Automovel {
    private int numeroquadro;
    private int peso;

    public Pesado(String matricula, String marca, String modelo, int potencia, int numeroquadro, int peso){
        super(matricula, marca, modelo, potencia);
        this.numeroquadro = numeroquadro;
        this.peso = peso;
    }
    public int getQuadro() {
        return numeroquadro;
    }
    public void setQuadro(int quadro) {
        this.numeroquadro = quadro;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder(super.toString());
    
        retString.append("Numero Quadro: " + numeroquadro + "\n");
        retString.append("Peso: " + peso + "\n");
            
        return retString.toString();
    }
    public boolean equals(Object object){
        Pesado newobject = (Pesado) object;
        return super.equals(object) && newobject.getQuadro() == this.getQuadro() && newobject.getPeso()==this.getPeso();
    }
}

