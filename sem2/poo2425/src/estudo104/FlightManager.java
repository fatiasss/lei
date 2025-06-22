package estudo104;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightManager {
    private ArrayList<Voo> voos = new ArrayList<>();

    public void loadFile(String v_file){
        try (Scanner myReader = new Scanner(new File("src/assets/" + v_file))) {
            myReader.nextLine();
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\t");
            if(sdata.length==4){addVoo(new Voo(sdata[0], sdata[1], sdata[2], sdata[3]));}
            else{addVoo(new Voo(sdata[0], sdata[1], sdata[2]));}
        }
            
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }
    
    public void addVoo(Voo v){
        voos.add(v);
    }
    public void printAllFlights(){
        System.out.println("Hora\tVoo\tCompanhia\tOrigem\tAtrasoO\tbs\n");
        for(Voo f: voos){System.out.println(f.toString());}
    }

    public static void main(String[] args) {
        FlightManager f = new FlightManager();
        f.loadFile("voos.txt");
        f.printAllFlights();
    }

}
