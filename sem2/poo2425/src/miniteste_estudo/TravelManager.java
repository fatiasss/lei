package miniteste_estudo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.*;

public class TravelManager {

    private ArrayList<Travel> travels = new ArrayList<>();


    public void addTravel(Travel t){
        travels.add(t);
    }
    public void removeTravel(int id){
        this.travels = travels.stream()
                              .filter(t -> t.getId()!=id)
                              .collect(Collectors.toCollection(ArrayList::new));
    }
    public Travel getTravel(int id){
        Travel retTravel = travels.stream() 
                                  .filter(t -> t.getId()==id)
                                  .findFirst()
                                  .orElse(null);
        return retTravel;
    }

    public double calculateTravelCost(int id){
        StandardTravelCostCalculator calc = new StandardTravelCostCalculator();
        if(getTravel(id)==null){return -1;}
        double cost = calc.calculateTravelCost(getTravel(id));
        return cost;
    }

    public void printAllTravels(){
        travels.forEach(System.out::println);
    }

    public void sortTravelsByCost(boolean national){
        ArrayList<Travel> sortedTravels = travels.stream()
                                                 .filter(t -> t.isNational()==national)
                                                 .sorted((t1,t2) -> Double.compare(calculateTravelCost(t2.getId()), calculateTravelCost(t1.getId())))
                                                 .collect(Collectors.toCollection(ArrayList::new));
        sortedTravels.forEach(System.out::println);
    }

/*     public void readFile(String fich){
        try(Scanner sc = new Scanner(new File(fich))){
            sc.nextLine();
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("; ");
            if(getTransaction(Integer.parseInt(sdata[0]))!=null){
                transactions.remove(getTransaction(Integer.parseInt(sdata[0])));
                addTransaction(new Transaction(Integer.parseInt(sdata[0]), sdata[2], sdata[3], Double.parseDouble(sdata[1])));
            }
            else{
                addTransaction(new Transaction(sdata[2], sdata[3], Double.parseDouble(sdata[1])));
            }

        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
    } */

    public void writeFile(String fich){
        try(PrintWriter p = new PrintWriter(new File(fich))){
            for(Travel t : travels){
                StringBuilder retString = new StringBuilder();
                retString.append(t.getId()).append(";");
                retString.append(t.getStartLocation()).append(";");
                retString.append(t.getDestination()).append(";");
                retString.append(t.getDuration()).append(";");
                retString.append(calculateTravelCost(t.getId())).append(";");
                p.println(retString.toString());
            }
        }catch(FileNotFoundException e){
            System.out.println("File not found!");
        }
    }

}
