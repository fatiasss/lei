package estudo;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact c){
        if(!contacts.add(c)){System.out.print("Contact already added!");}
    }
    
    public void removeContact(int id){
        if(contacts.contains(getContact(id))){contacts.remove(getContact(id));}
        else{System.out.println("Contact not found");}

        
    }
    public Contact getContact(int id){
        Contact found = contacts.stream()
                                .filter(c -> c.getId() == id)
                                .findFirst()
                                .orElse(null);
        return found;
                                        
    }
    public double calculateContactCost(int id){
        if(contacts.contains(getContact(id))){
            Contact found = getContact(id);
            return found.getCalls()*0.1 + found.getEmails()*0;
        }
        else{return 0.0;}
    }
    public void printAllContacts(){
        for(Contact c : contacts){System.out.print(c.toString());}
    }
    public ArrayList<Contact> searchOlderThan(String date){
        ArrayList<Contact> retlist = contacts.stream()
                                             .filter(c -> c.getBdate().isAfter(LocalDate.parse(date.stripTrailing(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                                             .collect(Collectors.toCollection(ArrayList::new));
        return retlist;

    }
    public void readFile(String filename){
          try (Scanner myReader = new Scanner(new File("src/assets/" + filename))) {
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\t");
            addContact(new Contact(sdata[0], Integer.parseInt(sdata[1]), sdata[2], sdata[3]));}
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }
    public void writeFile(String filename){
        try (PrintWriter writer = new PrintWriter("src/assets/" + filename)) {
            for (Contact c : contacts) {writer.println(c.toString());}

        } catch (java.io.IOException e) {
        System.out.println("Error writing file: ");}}

    }




