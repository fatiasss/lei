package estudo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GradeBook {
    private ArrayList<Student> students = new ArrayList<>();

    public void load(String filename){
          try (Scanner myReader = new Scanner(new File("src/assets/" + filename))) {
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\\|");
            ArrayList<Double> grades = new ArrayList<>();
            grades.add(Double.valueOf(sdata[1]));
            grades.add(Double.valueOf(sdata[2]));
            grades.add(Double.valueOf(sdata[3]));
            addStudent(new Student(sdata[0], grades));
            }
        } catch (FileNotFoundException e) {
        System.out.println("File not found.");}}

    public void addStudent(Student student){
        students.add(student);
    }
    public void removeStudent(String name){
        if(!students.remove(getStudent(name))){System.out.print("Student not found!");}
    }
    public Student getStudent(String name){
        Student retStudent = students.stream()
                                     .filter(s -> s.getName().equals(name))
                                     .findFirst()
                                     .orElse(null);
        return retStudent;

    }
    public double calculateAverageGrade(String name){
        SimpleGradeCalculator calc = new SimpleGradeCalculator();
        return calc.calculate(getStudent(name).getGrades());

    }
    public void printAllStudents(){
        for(Student s : students){System.out.print(s.toString());}
    }
    
    }
