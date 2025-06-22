package aula10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Gradebook {
    private ArrayList<Student> students = new ArrayList<>();

    public void load(String filename){

        try {
        File input = new File("src/assets/" + filename);
        Scanner myReader = new Scanner(input);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\\|");
            List<String> sdataList = new ArrayList<>(List.of(sdata));
            sdataList.remove(0);
            List<Double> grades = sdataList.stream()
                                           .map(Double::parseDouble)
                                           .toList();

            students.add(new Student(sdata[0], grades));
      }
        myReader.close();
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");
    }
        



    }

    public void removeStudent(String name){
        students.remove(getStudent(name));

    }

    
    public void addStudent(Student student){
        students.add(student);
    }

    public Student getStudent(String name){

        Student retstudent= students.stream()
                            .filter(s -> s.getName().equals(name))
                            .findFirst()
                            .orElse(null);
        return retstudent;
    }

    public double calculateAverageGrade(String name){
        SimpleGradeCalculator calc = new SimpleGradeCalculator();
        return calc.calculate(getStudent(name).getGrades());
    }

    public void printAllStudents(){
        for(Student student: students){
            System.out.println("Nome: " + student.getName());
            System.out.println("Notas: " + student.getGrades());
            System.out.println("Média: " + calculateAverageGrade(student.getName()));
        }
    }



}
