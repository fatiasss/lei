package estudo;

import java.util.List;

public class Student {

    private String name;
    private List<Double> grades;

    public Student(String name, List<Double> grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrades(List<Double> grades) {
        this.grades = grades;
    }
    @Override
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Name: ").append(name);
        retString.append("; Grades: ").append(grades).append("\n");
        return retString.toString();

    }
}