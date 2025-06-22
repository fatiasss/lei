package aula08.ex1;
import java.lang.StringBuilder;

public class Automovel implements IKmPercorridos{
    private String matricula;
    private String marca;
    private String modelo;
    private int potencia;

    private int ultimoTrajeto;
    private int distanciaTotal;

    public Automovel(String matricula, String marca, String modelo, int potencia) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    @Override

    public void trajeto(int quilometros){
        if(quilometros>0){
            distanciaTotal+=quilometros;
            ultimoTrajeto=quilometros;
        }

    }
    public int ultimoTrajeto(){
        return ultimoTrajeto;
    }
    public int distanciaTotal(){
        return distanciaTotal;
    }

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Marca: " + marca + "\n");
        retString.append("Modelo: " + modelo + "\n");
        retString.append("matricula: " + matricula + "\n");
        retString.append("Potência: " + Integer.toString(potencia) + "\n");
        retString.append("Ultimo Trajeto: " + Integer.toString(ultimoTrajeto()) + "\n");
        retString.append("Distância Total: " + Integer.toString(distanciaTotal()) + "\n");

        return retString.toString();

    }
    public boolean equals(Object object){
        if(object.getClass()!=this.getClass()){return false;}
        Automovel newobject = (Automovel) object;
        if(newobject.getMatricula()!=this.getMatricula() || newobject.getMarca()!= this.getMarca() || newobject.getModelo()!=this.getModelo() || newobject.getPotencia()!=this.getPotencia()){
            return false;
        }
        else{return true;}
    }
    

    
}
