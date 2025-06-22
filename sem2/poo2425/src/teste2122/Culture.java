package teste2122;

public class Culture extends Activity {
    static int personPrice =  22;
    public enum Option {
        ARCHITECTURAL_TOUR, DRINKS_AND_SNACKS, ART_MUSEUM, WINE_TASTING, RIVER_TOUR
    }
    private int participants;
    private Option chosen;

    public Culture(Option chosen, int participants){
        this.participants = participants;
        this.chosen = chosen;
    }

    public double getPrice(){
        return this.participants*personPrice;
    }
    public Option getOption(){
        return this.chosen;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.chosen).append(" with ").append(this.participants).append(" participants.").append("\n");
        return retString.toString();
    }

}
