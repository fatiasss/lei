package aula08.ex1;

public class PesadoMercadorias extends Pesado {
    private double cargamax;

    public PesadoMercadorias(String matricula, String marca, String modelo, int potencia, int numeroquadro, int peso, double cargamax){
        super(matricula, marca, modelo, potencia, numeroquadro, peso);
        this.cargamax = cargamax;
    }
    public double getCarga() {
        return cargamax;
    }
    public void setCarga(double cargamax) {
        this.cargamax = cargamax;
    }
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder(super.toString());
    
        retString.append("Carga Máxima: " + cargamax + "\n");
            
        return retString.toString();
    }
    public boolean equals(Object object){
        PesadoMercadorias newobject = (PesadoMercadorias) object;
        return super.equals(object) && newobject.getCarga()==this.getCarga();
    }
}
