package aula08.ex1;
import java.util.ArrayList;

public class EmpresaViaturas {
    private String name;
    private String postal;
    private String email;
    private ArrayList<Automovel> veiculos = new ArrayList<>();

    public void addVeiculo(Automovel automovel){
        veiculos.add(automovel);
    }

    public void listAutomoveis(){
        for(Automovel x : veiculos){
            System.out.print(x.toString());
        }
    }
    public Automovel maxKm(){
        Automovel maxkmauto = null;
        int maxkm = 0;
        for(Automovel x : veiculos){
            if (x.distanciaTotal()>maxkm) {
                maxkm= x.distanciaTotal();
                maxkmauto = x;
                

            }
        }
        return maxkmauto;
    }
    public Automovel minKm(){
        Automovel minkmauto = null;
        int minkm = Integer.MAX_VALUE;
        for(Automovel x : veiculos){
            if (x.distanciaTotal()<minkm) {
                minkm= x.distanciaTotal();
                minkmauto = x;
                

            }
        }
        return minkmauto;
    }
    

}
