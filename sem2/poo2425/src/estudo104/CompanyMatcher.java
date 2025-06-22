package estudo104;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CompanyMatcher {
    private HashMap<String, String> company_map;

    public CompanyMatcher(){
        this.company_map = new HashMap<>();
        loadFile("companhias.txt");
    }

    private void loadFile(String filename){
        try (Scanner myReader = new Scanner(new File("src/assets/" + filename))) {
            myReader.nextLine();
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\t");
            company_map.put(sdata[0], sdata[1]);
        }}catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }
    public String matchCompany(String id){
        System.out.println(id);
        return company_map.get(id);
    }

}

