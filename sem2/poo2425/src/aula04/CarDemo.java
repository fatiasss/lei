package aula04;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

class Car {
    public String make;
    public String model;
    public int year;
    public int kms;

    public Car(String make, String model, int year, int kms) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.kms = kms;
    }

    public void drive(int distance) {
        kms+=distance;
    }

}

public class CarDemo {

    static Scanner sc = new Scanner(System.in);
    static String inputmake, inputmodel, inputtext;
    static int inputyears, inputkms, counter, carindex;
    static Boolean boolval;

    static int registerCars(ArrayList<Car> carlist) {
        counter=0;
        while(true){
            System.out.print("Insira dados do carro (marca modelo ano quilómetros): ");
            inputtext = sc.nextLine();
            if(inputtext.equals("")){break;}
            while (!ValidateCar(inputtext)){
                System.out.print("Mal formatado, insira outra vez");
                inputtext = sc.nextLine();
            }
            carlist.add(new Car(inputmake, inputmodel, inputyears, inputkms));
            counter+=1;

        }
        return counter;
        }
    
  

    static void registerTrips(ArrayList<Car> carlist, int numCars) {
        System.out.print("Registe uma viagem no formato \"carro:distância\": ");
        String[] inputtext = sc.nextLine().split(":");
        carlist.get(Integer.parseInt(inputtext[0].trim())).kms+=Integer.parseInt(inputtext[1].trim());
    }

    static void listCars(ArrayList<Car> carlist) {
        System.out.println("\nCarros registados: ");
        for(Car car : carlist){
            System.out.printf(car.make + " " + car.model + ", " + car.year + "kms: " + car.kms + "\n");
        }
        System.out.println("\n");
    }
    static boolean ValidateCar(String input){
        Pattern regex = Pattern.compile("(\\S+)\\s+(.+?)\\s+(\\d{4})\\s+(\\d+)");
        Matcher matcher = regex.matcher(input);
        if(matcher.matches()){
            inputmake = matcher.group(1);
            inputmodel = matcher.group(2);
            inputyears = Integer.parseInt(matcher.group(3));
            inputkms = Integer.parseInt(matcher.group(4));
            return true;
        }
        else{return false;}


    }

    public static void main(String[] args) {

        ArrayList<Car> carlist = new ArrayList<>();

        int numCars = registerCars(carlist);

        if (numCars>0) {
            listCars(carlist);
            registerTrips(carlist, numCars);
            listCars(carlist);
        }

        sc.close();

    }
}