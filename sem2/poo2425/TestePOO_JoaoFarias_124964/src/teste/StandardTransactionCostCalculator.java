package teste;

public class StandardTransactionCostCalculator implements ITransactionCostCalculator {

    public double calculateTransactionCost(Transaction t){
        int week_day = t.getWeekDay();
        double mult;

        switch(week_day){
            case 6, 7: mult = 2; //Saturday, Sunday
                break;

            case 3: mult = 0.9; //Wednesday
                break;

            default: mult = 1;
        }
        return t.getWorkHours()*50*mult;

    }
}
