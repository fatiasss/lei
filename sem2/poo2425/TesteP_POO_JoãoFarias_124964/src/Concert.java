import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Month;

public class Concert {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static int global_id = 1;
    private int id;
    private double duration;  //minutos
    private String location;
    private LocalDateTime date;

    public Concert(String location, String date, double duration){
        this.id = global_id++;
        this.location = location;
        this.duration = duration;
        try {this.date = LocalDateTime.parse(date, formatter);}
        catch (DateTimeParseException e) {this.date = LocalDateTime.parse(date,formatter);}

    }
    public Concert(int insert_id, String location, String date, double duration){
        this.id = insert_id;
        if (insert_id >= global_id) { global_id = insert_id + 1; }
        this.location = location;
        this.duration = duration;
        try {this.date = LocalDateTime.parse(date, formatter);}
        catch (DateTimeParseException e) {this.date = LocalDateTime.parse(date,formatter);}

    }
    public int getId(){
        return id;
    }
    public double getDuration(){
        return this.duration;
    }
    public String getLocation(){
        return this.location;
    }
    public LocalDateTime getDate(){
        return this.date;
    }

    public String getCountry(){
        return this.location.split(",")[1].trim();
    }
    public Month getMonth(){
        return this.date.getMonth();
    }

    public int getMonthValue(){
        return this.date.getMonthValue();
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Id: ").append(this.id);
        retString.append("; Localização: ").append(this.location);
        retString.append("; Duração: ").append(this.duration).append(" minutos");
        retString.append("; Data: ").append(this.date.format(formatter));
        return retString.toString();
    }
}
