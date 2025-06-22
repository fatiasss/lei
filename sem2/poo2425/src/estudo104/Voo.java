package estudo104;

import java.time.Duration;
import java.time.LocalTime;
import java.lang.StringBuilder;

public class Voo {
    private LocalTime expected_arrival;
    private Duration delay = parseD("00:00");
    private LocalTime real_arrival;
    private String name;
    private String origin;
    private String company;

    public Voo(String expected_arrival, String name, String origin){
        this.expected_arrival = LocalTime.parse(expected_arrival);
        this.name = name;
        this.origin = origin;
        CompanyMatcher match = new CompanyMatcher();
        String company_id = name.replaceAll("\"[^a-zA-Z]+\"", "");
        this.company = match.matchCompany(company_id);
    }

    public Voo(String expected_arrival, String name, String origin, String delay){
        this.expected_arrival = LocalTime.parse(expected_arrival);
        
        this.delay = parseD(delay);
        this.real_arrival = LocalTime.parse(expected_arrival).plus(parseD(delay));
        this.name = name;
        this.origin = origin;
        CompanyMatcher match = new CompanyMatcher();
        String company_id = name.replaceAll("[^A-Za-z]", "");
        this.company = match.matchCompany(company_id);
    }
    private Duration parseD(String d){
        String[] parts = d.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return Duration.ofHours(hours).plusMinutes(minutes);
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append(expected_arrival).append("\t");
        retString.append(name).append("\t");
        retString.append(company).append("\t");
        retString.append(origin).append("\t");
        if(this.delay.isPositive()){
             retString.append(delay).append("\t");
            retString.append("Previsto:").append(real_arrival).append("\n");
        }
       
        return retString.toString();
    }

}
