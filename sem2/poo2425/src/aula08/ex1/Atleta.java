package aula08.ex1;

public class Atleta implements IKmPercorridos{
    private String nome;
    private String country;
    private String desporto;
    private int ultimoTrajeto;
    private int distanciaTotal;
    private Automovel alugado;

    public Atleta(String nome, String country, String desporto){
        this.nome = nome;
        this.country = country;
        this.desporto = desporto;

    }
    public void alugar(Automovel alugado){
        this.alugado = alugado;
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
        StringBuilder retString = new  StringBuilder();
        retString.append("Nome: " + nome + "\n");
        retString.append("País: " + country + "\n");
        retString.append("Desporto: " + desporto + "\n");
        return retString.toString();

    }
    
}
