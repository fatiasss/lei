package aula10;
import java.util.List;

public class SimpleGradeCalculator implements IGradeCalculator {

    public double calculate(List<Double> grades){
        int retval = 0;
        for(double grade: grades){retval+=grade;};
        return retval/grades.size();
    }
    

}
