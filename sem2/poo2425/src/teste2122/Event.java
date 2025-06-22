package teste2122;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event implements IEvent {
    LocalDate date;
    ArrayList<Activity> activities = new ArrayList<>();
    boolean hasCatering = false;
    Client client;

    public Event(Client client, LocalDate date){
        this.client = client;
        this.date = date;
    }

    public LocalDate getDate(){
        return this.date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }

    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }

    public ArrayList<Activity> getActivities(){
        return this.activities;
    }

    public Event addActivity(Activity activity){
        if(activity.getClass().equals(Catering.class) && hasCatering){return this;}
        if(activity.getClass().equals(Catering.class)){
            activities.add(activity);
            this.hasCatering = true;
        }
        else{
            activities.add(activity);
            
        }
        return this;
    }
    public double totalPrice(){

        double retprice = activities.stream()
                                    .map(a -> a.getPrice())
                                    .reduce(Double::sum)
                                    .orElse(0.0);
        return retprice;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Evento em ").append(this.date).append(", total=").append(totalPrice()).append("\n");
        for(Activity a : activities){
            retString.append("\t").append(a.toString());
        }
        return retString.toString();
    }


    

}
