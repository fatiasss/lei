package aula08.ex1;

public class Motociclo extends Automovel {
    private String tipo;

    public Motociclo(String matricula, String marca, String modelo, int potencia, String tipo){
        super(matricula, marca, modelo, potencia);
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Override
    public String toString() {
        StringBuilder retString = new StringBuilder(super.toString());
    
        retString.append("Tipo: " + tipo + "\n");
            
        return retString.toString();
    }
    public boolean equals(Object object){
        Motociclo newobject = (Motociclo) object;
        return super.equals(object) && newobject.getTipo() == this.getTipo();
    }

    
}
