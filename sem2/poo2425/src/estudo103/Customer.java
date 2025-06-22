package estudo103;
import java.util.List;

public class Customer {
    private int customerId;
    private List<Double> meterReadings;

    public Customer(int customerId, List<Double> meterReadings) {
        this.customerId = customerId;
        this.meterReadings = meterReadings;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Double> getMeterReadings() {
        return meterReadings;
    }

    public void setMeterReadings(List<Double> meterReadings) {
        this.meterReadings = meterReadings;
    }

    @Override

    public String toString(){
         StringBuilder retString = new StringBuilder();
        retString.append("Id: ").append(customerId);
        retString.append("; Meter Readings: ").append(meterReadings);
        retString.append("\n");
        return retString.toString();

    }
}
