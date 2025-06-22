package teste2122;

public class Client {
    private String name;
    private String place;

    public Client(String name, String place){
        this.name = name;
        this.place = place;
    }

    public String getName(){
        return this.name;
    }
    
    public String getPlace(){
        return this.place;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(this.name).append(" [").append(this.place).append("]\n");
        return retString.toString();
    }

    public boolean equals(Object object){
        if(object.getClass()!=this.getClass()){return false;}

        Client obj = (Client) object;

        return this.name.equals(obj.getName()) && this.place.equals(obj.getPlace());
    }



}
