package aula05;
import java.util.ArrayList;

import myutils.DateYMD;

import java.lang.StringBuilder;
public class Ex3 {
    public static void main(String[] args) {
        RealEstate imobiliaria = new RealEstate();
        imobiliaria.newProperty("Glória", 2, 30000);
        imobiliaria.newProperty("Vera Cruz", 1, 25000);
        imobiliaria.newProperty("Santa Joana", 3, 32000);
        imobiliaria.newProperty("Aradas", 2, 24000);
        imobiliaria.newProperty("São Bernardo", 2, 20000);

        imobiliaria.sell(1001);
        imobiliaria.sell(1001);
        imobiliaria.sell(1010);
        imobiliaria.setAuction(1002, new DateYMD(21, 3, 2023), 4);
        imobiliaria.setAuction(1003, new DateYMD(1, 4, 2023), 3);
        imobiliaria.setAuction(1001, new DateYMD(1, 4, 2023), 4);
        imobiliaria.setAuction(1010, new DateYMD(1, 4, 2023), 4);

        System.out.println(imobiliaria);

    }
    public static class Property{
        private int id;
        private int rooms;
        private int price;
        private String location;
        private boolean availability = true;
        private DateYMD beginning = null;
        private DateYMD end = null;
        static int lastid = 1000;

        public Property(String location, int rooms, int price){
            this.location = location;
            this.rooms = rooms;
            this.price= price;
            this.id = lastid+1;
            lastid++;
        }
        public String toString(){
            if(this.beginning==null || this.end==null){
                return String.format("Imóvel: %4d; quartos: %2d; localidade: %10s; preço: %5d; disponível: %3s\n", id, rooms, location, price, (availability? "sim" : "não"));
            }
            else{
                return String.format("Imóvel: %4d; quartos: %2d; localidade: %10s; preço: %5d; disponível: %3s leilão %10s : %10s\n", id, rooms, location, price, (availability? "sim" : "não"), beginning, end);
            }
        }
        public int getId(){
            return this.id;
        }
        public void setAuction(DateYMD startdate, DateYMD enddate){
            this.beginning = startdate;
            this.end= enddate;
        }
        public boolean getAvailability(){
            return this.availability;
        }
        public void setAvailability(boolean availability){
            this.availability = availability;
        }

    }
    public static class RealEstate{
        private ArrayList<Property> propertylist = new ArrayList<>();

        public void newProperty(String location, int rooms, int price){
            propertylist.add(new Property(location, rooms, price));
        }
        public void sell(int id){
            try {
                for (int i = 0; i < propertylist.size(); i++) {
                    if (propertylist.get(i).getId() == id) {
                        if(propertylist.get(i).getAvailability()){
                            propertylist.get(i).setAvailability(false);
                            System.out.printf("Imóvel %4d vendido.\n", id);             
                            return;
                        }
                        else{
                            System.out.printf("Imóvel %4d não está disponível\n", id);             
                            return;
                        }
                       
                    }
                }
                throw new Exception("Imóvel não existe");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
        }
        public void setAuction(int id, DateYMD date, int days){
            DateYMD enddate = date;
            for(int i=1; i<=days; i++){
                enddate.increment();
            }
            for(Property property: propertylist){
                if(property.getId()==id){
                    property.setAuction(date, enddate);
                }

            }
        }
        public String toString(){
            StringBuilder retString = new StringBuilder();
            for(Property property: propertylist){
                retString.append(property.toString());
            }
            return retString.toString();
        }
            }
           
}
    


    