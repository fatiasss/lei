package estudo;

import java.util.List;

public class SimpleGradeCalculator implements IGradeCalculator {
    

    public double calculate(List<Double> grades){
        double sum = 0;
        for(Double c : grades){sum+=c;}
        return sum/3;
    }
}
