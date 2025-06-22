public class StandardConcertProfitCalculator implements IConcertProfitCalculator {

    public double calculateConcertProfit(Concert c){
        double price = c.getDuration()/60*1500;

        if(c.getCountry().equals("Portugal")){
            return price;
        }
        price = price*2;
        if(!(c.getCountry().equals("Espanha") || c.getCountry().equals("Andorra") || c.getCountry().equals("Gibraltar"))){
            price+=800;
        }
        return price;

    }
}
