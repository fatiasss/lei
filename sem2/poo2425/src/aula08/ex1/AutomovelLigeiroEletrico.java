package aula08.ex1;

public class AutomovelLigeiroEletrico extends AutomovelLigeiro implements IGestaoBateria {
    double carga;
    double cargamaxima;

    public AutomovelLigeiroEletrico(String matricula, String marca, String modelo, int potencia, int numeroquadro, int capacidade){
        super(matricula, marca, modelo, potencia, numeroquadro, capacidade);
    }
    @Override
    public double cargaDisponivel() {
        return carga;
    }
    public void limitarCargaMaxima(double percentagem){
        this.cargamaxima = percentagem;
    }
    public void carregar(double percentagem){
        if(carga+percentagem<cargamaxima){
            carga+=percentagem;
        }
        else{carga=cargamaxima;}
    }
    
}
