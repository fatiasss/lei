package estudo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.StringBuilder;

public class Contact {
    static int main_id = 1;
    private int id;
    private String name;
    private int phone;
    private String email;
    private LocalDate bdate;
    private double mins;
    private int emails;

    public Contact(String name, int phone, String email, String bdate){
        if(!(validateEmail(email) && validatePhone(phone))){System.out.println("Invalid parameters"); return;}
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bdate = LocalDate.parse(bdate.stripTrailing(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.id = main_id++;
    }

    private boolean validateEmail(String email){
        return email.stripTrailing().matches("^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
    private boolean validatePhone(int phone){
    
        return Integer.toString(phone).length()==9;
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
    public int getPhone(){
        return phone;
    }
    public void setPhone(int phone){
        this.phone = phone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public LocalDate getBdate(){
        return bdate;
    }
    public void setBdate(LocalDate bdate){
        this.bdate = bdate;
    }

    public void call(double mins){
        this.mins+=mins;
    }
    public void email(){
        this.emails++;
    }
    public double getEmails(){
        return this.emails;
    }
    public double getCalls(){
        return this.mins;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Id: ").append(id);
        retString.append(";Name: ").append(name);
        retString.append(";Phone: ").append(phone);
        retString.append(";Email: ").append(email);
        retString.append(";Birthdate: ").append(bdate.toString());
        return retString.toString();
    }
    public boolean equals(Object object){
        if(object.getClass()!=this.getClass()){return false;}
        Contact comparable = (Contact) object;
        return comparable.getName().equals(this.getName()) && 
               comparable.getEmail().equals(this.getEmail()) && 
               comparable.getPhone()==this.getPhone(); 
    }




}
