package aula12;

public class StandardCostCalculator implements IContactCostCalculator{

    public double calculateCost(double units, ContactType type){
        switch(type){
            case EMAIL: return 0;
            case CELLNUMBER: return units*0.1;
            default: return 0;
        }
    };


}
