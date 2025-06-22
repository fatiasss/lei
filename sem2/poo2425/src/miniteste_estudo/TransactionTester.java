package miniteste_estudo;

public class TransactionTester {
    public static void main(String[] args) {
        TravelManager tm = new TravelManager();

        // ----------------------------------------------------------
        Travel t1 = new Travel("Lisboa, Portugal", "Londres, Inglaterra", 5);
        Travel t2 = new Travel("Porto, Portugal", "Tokyo, Japão", 15);
        tm.addTravel(t1);
        tm.addTravel(t2);
        // ----------------------------------------------------------

        tm.printAllTravels();

        // ----------------------------------------------------------
        System.out.println(tm.getTravel(1));
        System.out.println(tm.calculateTravelCost(1));
        System.out.println(tm.getTravel(2));
        System.out.println(tm.calculateTravelCost(2));
        System.out.println(tm.getTravel(30));              // não existe!
        System.out.println(tm.calculateTravelCost(30));    // não existe!
        // ----------------------------------------------------------

        System.out.println("---------------");
        tm.sortTravelsByCost(false);
        System.out.println("---------------");

        // ----------------------------------------------------------
        //tm.readFile("services.txt");
        tm.printAllTravels();

        // ----------------------------------------------------------
        System.out.println(tm.getTravel(1));
        System.out.println(tm.calculateTravelCost(1));
        System.out.println(tm.getTravel(2));
        System.out.println(tm.calculateTravelCost(2));
        System.out.println(tm.getTravel(30));
        System.out.println(tm.calculateTravelCost(30));
        // ----------------------------------------------------------

        tm.writeFile("src\\assets\\result.txt");

        // ----------------------------------------------------------

        System.out.println("---------------");
        tm.sortTravelsByCost(true);
        System.out.println("---------------");

        // ----------------------------------------------------------

    }
}

