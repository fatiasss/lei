package aula12;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aula12.IContactCostCalculator.ContactType;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContactManager{
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact){
        if(contacts.contains(contact)){System.out.println("Contact already in list!"); return;}
        contacts.add(contact);
    }
    
    public void removeContact(Contact contact){
        if(!contacts.remove(contact)){System.out.println("Contact not in list!");}
    }

    public Contact getContact(int id){
        List<Contact> searched = contacts.stream()
                                .filter(c -> c.getId()==id)
                                .toList();
        if(searched.size()==0){return null;}
        else return searched.get(0);
    }

    public double calculateContactCost(int id){
        Contact contact = this.getContact(id);
        if (contact == null) {
        System.out.println("Contact with id " + id + " not found.");
        return 0;}
        StandardCostCalculator calc = new StandardCostCalculator();
        return calc.calculateCost(contact.getPhoneCounter(), ContactType.CELLNUMBER) + calc.calculateCost(contact.getEmailCounter(), ContactType.EMAIL);
    };

    public void printAllContacts(){
        for(Contact c : contacts){System.out.println(c.toString() + "\n");}
    };

    public void readFile(String filename){
        try (Scanner myReader = new Scanner(new File("src/assets/" + filename))) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] sdata = data.split("\t");
            contacts.add(new Contact(sdata[0], Integer.parseInt(sdata[1]), sdata[2], sdata[3]));}
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");}
    }
    
    public void writeFile(String filename){
        try (PrintWriter writer = new PrintWriter("src/assets/" + filename)) {
        for (Contact c : contacts) {writer.println(c.toString());}
        } catch (java.io.IOException e) {
        System.out.println("Error writing file: ");}}
        
    public List<Contact> searchOlderThan(String dateString){
        LocalDate date = LocalDate.parse(dateString.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<Contact> searched = contacts.stream()
                                              .filter(c -> c.getDate().isAfter(date))
                                              .toList();
        return searched;
    }
}
