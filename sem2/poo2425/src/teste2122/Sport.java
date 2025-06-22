package teste2122;

public class Sport extends Activity {
    static int personPrice =  30;
    public enum Modality {
        KAYAK, BIKE, BOWLING, HIKING
    }
    private int participants;
    private Modality chosen;

    public Sport(Modality chosen, int participants){
        this.participants = participants;
        this.chosen = chosen;
    }

    public double getPrice(){
        return this.participants*personPrice;
    }
    public Modality getOption(){
        return this.chosen;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.chosen).append(" sporting activity with ").append(this.participants).append(" participants.").append("\n");
        return retString.toString();
    }

}
