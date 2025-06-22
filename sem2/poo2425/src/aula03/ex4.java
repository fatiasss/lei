package aula03;
import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ex4 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Double>> grades = new ArrayList<>();
        File gradeFile = new File("gradefile.txt");
        Scanner sc;
        try {
            sc = new Scanner(gradeFile);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                return;
            }
        while(sc.hasNextLine()){
            ArrayList<Double> student = new ArrayList<>();
            String[] studentstring = sc.nextLine().split("\t");
            double notaP = Double.valueOf(studentstring[0]);
            double notaT = Double.valueOf(studentstring[1]);
            student.add(notaP);
            student.add(notaT);
            student.add(CalculateGrade(notaP, notaT));
            grades.add(student);
        }
        sc.close();
        System.out.printf("%-10s %-10s %-10s%n","NotaP", "NotaT", "Pauta");
        for(ArrayList<Double> student : grades){
            if (student.get(2)!=66 && student.get(2)>=9.5){
                System.out.printf("%10.2d %10.2d %10.2d%n", student.get(0), student.get(1), student.get(2));
            }
        }



        
    }
    public static double CalculateGrade(double notaP, double notaT){
        double grade;
        if(notaT<7 || notaP<7){grade=66;}
        else{grade = Math.round(0.4 * notaT + 0.6 * notaP);};
        return grade;
    }

}
