package ex2;
import java.util.ArrayList;

public class Encaminhador {
    private String name;
    private ArrayList<Pacote> pacotes = new ArrayList<>();
    private Pacote lastsent = null;

    public Encaminhador(String name){
        this.name = name;
    }
    public void addPacote(Pacote pacote){
        pacotes.add(pacote);
    }
    public void removePacote(Pacote pacote) {
        if (pacotes.contains(pacote)) {
            pacotes.remove(pacote);
        } else {
            System.out.print("Pacote not in List\n");
        }
    }
    public void enviaPacote() {
        if (pacotes.isEmpty()) {
            return;
        }
    
        Pacote sending = pacotes.get(0);
        if (lastsent == null || !sending.equals(lastsent)) {
            System.out.print(sending.toString());
            lastsent = sending;
        } else {
            System.out.print("(pacote duplicado)\n");
        }
    
        pacotes.remove(0);
    }
    public boolean hasPacotes(){
        return pacotes.size()!=0;
    }
    public int getTamanhoFila(){
        int retInt=0;
        for(Pacote x: pacotes){
            retInt+=x.getMessageLength();
        }
        return retInt;
        
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Encaminhador" + this.getName() + ", ");
        retString.append("TamanhoFila = " + this.getTamanhoFila() + "\n");

        return retString.toString();

    }


}
