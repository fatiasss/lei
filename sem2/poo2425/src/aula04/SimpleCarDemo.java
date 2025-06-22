package aula04;
import java.util.Scanner;
import java.util.ArrayList;

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
        this.kms+=distance;
    }
}

public class SimpleCarDemo {

    static Scanner sc = new Scanner(System.in);

    static void listCars(ArrayList<Car> cars) {
        System.out.println("\nCarros registados: ");
        for(Car car : cars){
            System.out.printf(car.make + " " + car.model + ", " + car.year + "kms: " + car.kms + "\n");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Supra", 1998, 20000));
        cars.add(new Car("Toyota", "Simpra", 2000, 20000));
        cars.add(new Car("Toyota", "Sompra", 2020, 20000));

        listCars(cars);

        // Adicionar 10 viagens geradas aleatoriament
        for (int i=0; i<10; i++) {
            int j = (int)Math.round(Math.random()*2); // escolhe um dos 3 carros
            int kms = (int)Math.round(Math.random()*1000); // viagem até 1000 kms
            System.out.printf("Carro %d viajou %d quilómetros.\n", j, kms);
            
            cars.get(j).kms+= kms;
        }

        listCars(cars);

        sc.close();

    }
}