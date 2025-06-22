package teste2122;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class EventManager {
    private String name;
    private HashMap<Client,ArrayList<Event>> events = new HashMap<>();

    public EventManager(String name){
        this.name = name;
    }

    public Client addClient(String name, String local){
        Client client = new Client(name, local);
        if(events.keySet().contains(client)){System.out.print("Client already registered!"); return client;}
        else{
            events.put(client, new ArrayList<>());
            System.out.print(client);
            return client;
        }
    }
    public Event addEvent(Client client, LocalDate date){
        Event event = new Event(client, date);
        for(Event e : events.get(client)){
            if(!e.getDate().equals(date)){continue;}

            System.out.println("Client already has an event with that date!");
            return event;
        }
        events.get(client).add(event);
        return event;
    }
    public String listClients(){
        StringBuilder retString = new StringBuilder();
        for(Client c : events.keySet()){
            retString.append(c.toString());
        }
        return retString.toString();
    }

    public String listEvents(){
        StringBuilder retString = new StringBuilder();
        retString.append("Events:\n");
        for(Client c : events.keySet()){
            retString.append(c.toString());
            for(Event e : events.get(c)){
                retString.append("*** ").append(e.toString());
            }
        }
        return retString.toString();

    }
    public ArrayList<String> getClientsWithEvents(){
        ArrayList<String> retlist = new ArrayList<>(
            events.keySet().stream()
                .filter(c -> !events.get(c).isEmpty())
                .map(Client::toString)
                .toList()
        );
        return retlist;
    }
    public ArrayList<String> getNextEventsByDate(){
        ArrayList<Event> retlist = new ArrayList<>();
        for(Client c : events.keySet()){
            for(Event e: events.get(c)){
                retlist.add(e);
            }
        }
        retlist.sort(Comparator.comparing(Event::getDate));
        ArrayList<String> stringRetList = new ArrayList<String>(
            retlist.stream().map(Event::toString).collect(Collectors.toCollection(ArrayList::new)));
        return stringRetList;
    }

    @Override

    public String toString(){
        return this.name;
    }
        
}
