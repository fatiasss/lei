package aula08.ex1;

public class Taxi extends AutomovelLigeiro {
    private int licenca;

    public Taxi(String matricula, String marca, String modelo, int potencia, int numeroquadro, int capacidade, int licenca){
        super(matricula, marca, modelo, potencia, numeroquadro, capacidade);
        this.licenca = licenca;
    }
    public int getLicensa() {
        return licenca;
    }
    public void setLicensa(int licenca) {
        this.licenca = licenca;
    }
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder(super.toString());
    
        retString.append("Licensa: " + licenca + "\n");
            
        return retString.toString();
    }
    public boolean equals(Object object){
        Taxi newobject = (Taxi) object;
        return super.equals(object) && newobject.getLicensa()==this.getLicensa();
    }
    
}
