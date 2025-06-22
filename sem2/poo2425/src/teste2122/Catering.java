package teste2122;

public class Catering extends Activity {
    static int personPrice =  25;
    public enum Option {
        FULL_MENU, RIVER_TOUR, LIGHT_BITES, WINE_TASTING, DRINKS_AND_SNACKS
    }
    private int participants;
    private Option chosen;

    public Catering(Option chosen, int participants){
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
        retString.append("'").append(this.chosen).append("'").append(" for ").append(this.participants).append(" participants.").append("\n");
        return retString.toString();
    }

}
