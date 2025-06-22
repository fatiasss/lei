package aula10.ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import aula10.Student;

public class EnergyUsageReport {

    private HashMap<Integer, List<Double>> customers = new HashMap<>();

    public void load(){
        public void load(String filename){

        try {
        File input = new File("src/assets/" + filename);
        Scanner myReader = new Scanner(input);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\\|");
            List<String> sdataList = new ArrayList<>(List.of(sdata));
            sdataList.remove(0);
            List<Double> meter = sdataList.stream()
                                           .map(Double::parseDouble)
                                           .toList();

            customers.put(Integer.parseInt(sdata[0]), meter);}
        myReader.close();
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");
    }
    }
    public void addCustomer(int id){

    }

    public void removeCustomer(int id){

    }
    public double calculateTotalUsage(){

    }

    public String generateReport(){

    }



}
