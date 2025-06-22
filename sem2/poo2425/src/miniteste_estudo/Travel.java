package miniteste_estudo;

public class Travel {
    private static int global_id = 1;
    private int id;
    private int duration;
    private String startLocation;
    private String destination;

    public Travel(String start, String dest, int duration){
        this.startLocation = start;
        this.destination = dest;
        this.duration = duration;
        this.id = global_id++;
    }

    public int getId(){
        return this.id;
    }
    public String getStartLocation(){
        return this.startLocation;
    }
    public void setStartLocation(String loc){
        this.startLocation = loc;
    }
    public void setDestination(String loc){
        this.destination = loc;
    }
    public String getDestination(){
        return this.destination;
    }

    public String getStartCountry(){
        return this.startLocation.split(",")[1].trim();
    }
    public String getDestinationCountry(){
        return this.destination.split(",")[1].trim();
    }
    public int getDuration(){
        return duration;
    }
    public void setDuration(int days){
        this.duration = days;
    }

    public boolean isNational(){
        return getStartCountry().equals(getDestinationCountry());
    }


    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Id: ").append(id);
        retString.append(";Start Location: ").append(startLocation);
        retString.append(";Destination: ").append(destination).append("\n");
       
        return retString.toString();
    }




}
