package aula12;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.StringBuilder;

public class Contact {
    private static int idcounter = 1;
    private int id;
    private String name;
    private int phone_number;
    private String email;
    private LocalDate bdate;
    private int emailcounter = 0;
    private double phonecounter = 0;

    public Contact(String name, int phone_number, String email, String date){
        if(email.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$") && Integer.toString(phone_number).length()==9){
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.id = idcounter++;
        this.bdate = LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        else{
            System.out.println("Invalid Phone Number or Email");
        }
        

    }


    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public int getPhoneNumber(){
        return phone_number;
    }
    public void setPhoneNumber(int phone_number){
        this.phone_number = phone_number;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public LocalDate getDate(){
        return bdate;
    }
    public void setDate(LocalDate bdate){
        this.bdate = bdate;
    }
    public int getEmailCounter(){
        return emailcounter;
    }
    public double getPhoneCounter(){
        return phonecounter;
    }

    public void email(){
        emailcounter++;
    }
    public void email(int number){
        this.emailcounter+=number;
    }

    public void call(double minutes){
        this.phonecounter+=minutes;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Id: ").append(this.id);
        retString.append("; Name: ").append(this.name);
        retString.append("; Phone Number: ").append(this.phone_number);
        retString.append("; Email: ").append(this.email);
        retString.append("; BirthDate: ").append(this.bdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        retString.append(";");

        return retString.toString();
    }

    public boolean equals(Object other){
        if(!other.getClass().equals(this.getClass())){return false;}
        Contact newContact = (Contact) other;
        if(newContact.getName()==null || newContact.getEmail()==null){return false;}
        return(newContact.getName().equals(this.name) && 
        newContact.getPhoneNumber()==(this.phone_number) && 
        newContact.getEmail().equals(this.email));
    }


}
