package aula09;

public class CommercialPlane extends Plane {
    private int pilots;

    public CommercialPlane(String id, String manufacturer, String model, int year, int pmax, int vmax, int pilots){
        super(id, manufacturer, model, year, pmax, vmax);
        this.pilots = pilots;
    }
    
    public int getPilots(){
        return pilots;
    }
    public void setPilots(int pilots){
        this.pilots = pilots;
    }
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Commercial Plane;" + this.getManufacturer() + "," + this.getModel() + ":");
        retString.append(this.getId() + ";");
        retString.append("Made in " + this.getYear());
        retString.append("; Max Passengers: " + this.getPmax() + "; Max Velocity: " + this.getVmax());

        return retString.toString();
    }
    public String getPlaneType(){
        return "Commercial";
    }


}
