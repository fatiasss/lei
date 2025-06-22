package aula09;

public class MilitaryPlane extends Plane {
    private int ammo;

    public MilitaryPlane(String id, String manufacturer, String model, int year, int pmax, int vmax, int ammo){
        super(id, manufacturer, model, year, pmax, vmax);
        this.ammo = ammo;
    }
    public int getAmmo(){
        return ammo;
    }
    public void setAmmo(int ammo){
        this.ammo = ammo;
    }

    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Military Plane;" + this.getManufacturer() + "," + this.getModel() + ":");
        retString.append(this.getId() + ";");
        retString.append("Made in " + this.getYear());
        retString.append("; Max Passengers: " + this.getPmax() + "; Max Velocity: " + this.getVmax());

        return retString.toString();
    }
    public String getPlaneType(){
        return "Military";
    }

}
