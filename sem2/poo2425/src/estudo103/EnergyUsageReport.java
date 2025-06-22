package estudo103;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnergyUsageReport {
    private ArrayList<Customer> customers = new ArrayList<>();
    public void load(String filename){
        try (Scanner myReader = new Scanner(new File("src/assets/" + filename))) {
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\\|");
            ArrayList<Double> readings = new ArrayList<>();
            for(int i=1; i<5; i++){readings.add(Double.parseDouble(sdata[i]));}
            addCustomer(new Customer(Integer.parseInt(sdata[0]), readings));}
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }

    public void addCustomer(Customer customer){
        customers.add(customer);

    }

    public void removeCustomer(int id){
        Customer found = getCustomer(id);
        if(found!=null){customers.remove(found);}
        else{System.out.print("Customer not in List");}

    }
    public Customer getCustomer(int id){
        Customer retCustomer = customers.stream()
                                        .filter(s -> s.getCustomerId()==id)
                                        .findFirst()
                                        .orElse(null);
        return retCustomer;
    }
    public double calculateTotalUsage(int id){
        Customer customer = getCustomer(id);
        Double total = customer.getMeterReadings().stream()
                                                  .collect(Collectors.summingDouble(Double::doubleValue));
        return total;
        

    }

    public void generateReport(String filename){
        try (PrintWriter writer = new PrintWriter("src/assets/" + filename)) {
            for (Customer c : customers) {writer.println(c.toString());}

        } catch (java.io.IOException e) {
        System.out.println("Error writing file: ");}}


    }


