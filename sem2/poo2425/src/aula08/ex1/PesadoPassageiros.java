package aula08.ex1;

public class PesadoPassageiros extends Pesado {
    private int passmax;

    public PesadoPassageiros(String matricula, String marca, String modelo, int potencia, int numeroquadro, int peso, int passmax){
        super(matricula, marca, modelo, potencia, numeroquadro, peso);
        this.passmax = passmax;
    }
    public int getPass() {
        return passmax;
    }
    public void setPass(int passmax) {
        this.passmax = passmax;
    }
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder(super.toString());
    
        retString.append("Passageiros Máximos: " + passmax + "\n");
            
        return retString.toString();
    }
    public boolean equals(Object object){
        PesadoPassageiros newobject = (PesadoPassageiros) object;
        return super.equals(object) && newobject.getPass()==this.getPass();
    }
}