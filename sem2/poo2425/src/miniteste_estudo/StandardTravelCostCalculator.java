package miniteste_estudo;

public class StandardTravelCostCalculator implements ITravelCostCalculator{

    public double calculateTravelCost(Travel t){
        Boolean national = t.isNational();
        double price = 0;
        double mult;
        if(national){mult = 50;}
        else{mult = 150;}

        price+=t.getDuration()*mult;

        if(t.getDuration()<7){price+=75;}

        if(t.getId()%10==0){price=price*0.9;}

        return price;
    }
}
