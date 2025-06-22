package teste;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.lang.StringBuilder;

public class TransactionManager {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    
    public void addTransaction(Transaction t){
        if(!transactions.add(t)){System.out.println("Transaction already in List");};
    }
    public void removeTransaction(int id){
        Transaction found = getTransaction(id);
        if(found!=null){transactions.remove(found);}
        else{System.out.print("Transaction not in List");}

    }
    public Transaction getTransaction(int id){
        Transaction retCustomer = transactions.stream()
                                        .filter(t -> t.getId()==id)
                                        .findFirst()
                                        .orElse(null);
        return retCustomer;
    }
    public double calculateTransactionCost(int id){
        StandardTransactionCostCalculator calc = new StandardTransactionCostCalculator();
        Transaction found = getTransaction(id);

        if(found!=null){return calc.calculateTransactionCost(found);}
        else{return -1;}
        
    }
    public void printAllTransactions(){
        for(Transaction t: transactions){System.out.println(t.toString());}
    }
    public void sortTransactionsByCost(){
        StringBuilder retString = new StringBuilder(); 
        ArrayList<Transaction> sortedList = transactions.stream()
                                                        .sorted(Comparator.comparing(Transaction::getWeekDay)
                                                        .thenComparing((t1, t2) -> Double.compare(calculateTransactionCost(t2.getId()), calculateTransactionCost(t1.getId()))))
                                                        .collect(Collectors.toCollection(ArrayList::new));  
                                          
        for(Transaction t: sortedList){
            retString.append(t.toString()).append("; ");
            retString.append(t.getServiceTime().getDayOfWeek().toString()).append("; ");
            retString.append(calculateTransactionCost(t.getId())).append(" Euros;\n");
        }
        System.out.println(retString);

    }


    public void readFile(String fich){
        try (Scanner myReader = new Scanner(new File("src/assets/" + fich))) {
            myReader.nextLine();
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
            
        }
            
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }
    public void writeFile(String filename){
        try (PrintWriter writer = new PrintWriter("src/assets/" + filename)) {
            StringBuilder retString = new StringBuilder();
            retString.append("ID;Horas de Trabalho; Descrição do Serviço; Data e Hora do Serviço; Preço do Serviço\n");
            for (Transaction t : transactions) {
                retString.append(t.getId()).append(";");
                retString.append(t.getStringId()).append(";");
                retString.append(t.getServiceTime()).append(";");
                retString.append(calculateTransactionCost(t.getId())).append(";");
                retString.append("\n");
            }
            writer.println(retString);

        } catch (java.io.IOException e) {
        System.out.println("Error writing file: ");}}


    }




