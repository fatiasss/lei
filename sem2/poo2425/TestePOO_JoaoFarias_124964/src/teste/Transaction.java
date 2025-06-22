package teste;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Transaction {
    private static int main_id = 1;
    private int id;
    private double work_hours;
    private String string_id;
    private LocalDateTime service_time;

    public Transaction( String string_id, String service_time, double work_hours){
        this.id = main_id++;
        this.string_id = string_id;
        this.work_hours = work_hours;
        try {this.service_time = LocalDateTime.parse(service_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));}
        catch (DateTimeParseException e) {this.service_time = LocalDateTime.parse(service_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));}
   
    }
     public Transaction(int id_insert, String string_id, String service_time, double work_hours){
        this.id = id_insert;
        if (id_insert >= main_id) { main_id = id_insert + 1; }
        this.string_id = string_id;
        this.work_hours = work_hours;
        try {this.service_time = LocalDateTime.parse(service_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));}
        catch (DateTimeParseException e) {this.service_time = LocalDateTime.parse(service_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));}
   
    }

    public int getId(){
        return id;
    }
    public double getWorkHours(){
        return work_hours;
    }
    public void setWorkHours(double hours){
        this.work_hours = hours;
    }
    public LocalDateTime getServiceTime(){
        return service_time;
    }
    public void setServiceTime(String service_time){
        try {this.service_time = LocalDateTime.parse(service_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));}
        catch (DateTimeParseException e) {this.service_time = LocalDateTime.parse(service_time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));}
    }
    public String getStringId(){
        return string_id;
    }
    public void setStringId(String string_id){
        this.string_id = string_id;
    }
    public int getWeekDay(){
        return this.service_time.getDayOfWeek().getValue();
    }
    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Transaction Name: ").append(string_id);
        retString.append("; Transaction ID: ").append(id);
        retString.append("; Service Time: ").append(service_time); //A formatação utilizada é YYYY-MM-DDTHH:MM , vinda do LocalDateTime.toString()
        retString.append("; Service Duration: ").append(work_hours).append(" Hours");
        retString.append("\n");
        return retString.toString();
    }



}
