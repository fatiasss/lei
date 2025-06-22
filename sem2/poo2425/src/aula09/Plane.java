package aula09;

public class Plane {
    private String id, manufacturer, model;
    private int year, pmax, vmax;

    public Plane(String id, String manufacturer, String model, int year, int pmax, int vmax){
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.pmax = pmax;
        this.vmax = vmax;
    }

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    
    public String getManufacturer(){
        return manufacturer;
    }
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year = year;
    }

    public int getPmax(){
        return pmax;
    }
    public void setPmax(int pmax){
        this.pmax = pmax;
    }

    public int getVmax(){
        return vmax;
    }
    public void setVmax(int vmax){
        this.vmax = vmax;
    }

    public String getPlaneType(){
        return "Null";
    }

    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(manufacturer + "," + model + ":");
        retString.append(id + ";");
        retString.append("Made in " + year);
        retString.append("; Max Passengers: " + pmax + "; Max Velocity: " + vmax);

        return retString.toString();
    }
}
