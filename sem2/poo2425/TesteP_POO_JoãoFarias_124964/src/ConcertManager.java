import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConcertManager {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private ArrayList<Concert> concerts = new ArrayList<>();

    
    
    public void addConcert(Concert c){
        concerts.add(c);
    }

    public void removeConcert(int id){
        concerts.remove(getConcert(id));
    }

    public Concert getConcert(int id){
        Concert retTravel = concerts.stream() 
                                  .filter(t -> t.getId()==id)
                                  .findFirst()
                                  .orElse(null);
        return retTravel;
    }

    public double calculateConcertProfit(int id){
        Concert c = getConcert(id);
        if(c==null){return -1;}

        StandardConcertProfitCalculator calc = new StandardConcertProfitCalculator();
        
        return calc.calculateConcertProfit(c);
    }
    public void printAllConcerts(){
        concerts.forEach(System.out::println);
    }

    public void sortConcertsByProfit(){
        StringBuilder retString = new StringBuilder(); 
        ArrayList<Concert> sortedList = concerts.stream()
                                                        .sorted(Comparator.comparing(Concert::getMonthValue)
                                                        .thenComparing((c1, c2) -> Double.compare(calculateConcertProfit(c2.getId()), calculateConcertProfit(c1.getId()))))
                                                        .collect(Collectors.toCollection(ArrayList::new));  
                                          
        for(Concert c: sortedList){
            retString.append(c.toString()).append("; ");
            retString.append("Mês ").append(c.getMonth());
            retString.append("; Lucro: ").append(calculateConcertProfit(c.getId())).append(" Euros;\n");
        }
        System.out.println(retString);
    }
    public void readFile(String fich){
        try (Scanner myReader = new Scanner(new File("src/assets/" + fich))) {
            myReader.nextLine();
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("; ");
            if(getConcert(Integer.parseInt(sdata[0]))!=null){
                concerts.remove(getConcert(Integer.parseInt(sdata[0])));
                addConcert(new Concert(Integer.parseInt(sdata[0]), sdata[2], sdata[3], Double.parseDouble(sdata[1])));
            }
            else{
                addConcert(new Concert(sdata[2], sdata[3], Double.parseDouble(sdata[1])));
            }
            
        }
            
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }
    public void writeFile(String filename){
        try (PrintWriter writer = new PrintWriter("src/assets/" + filename)) {
            StringBuilder retString = new StringBuilder();
            retString.append("ID; Duração; Local do Concerto; Data e Hora de Início; Lucro do Concerto\n");
            for (Concert c : concerts) {
                retString.append(c.getId()).append(";");
                retString.append(c.getDuration()).append(";");
                retString.append(c.getLocation()).append(";");
                retString.append(c.getDate().format(formatter)).append(";");
                retString.append(calculateConcertProfit(c.getId())).append(";");
                retString.append("\n");
            }
            writer.println(retString);

        } catch (java.io.IOException e) {
        System.out.println("Error writing file: ");}}



    
       
}
