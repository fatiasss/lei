package teste2122;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Scanner;

// Notas:
// Não altere o código apresentado
// Deve completar o código da alinea 2
// Deve comentar o código para garantir que vai executando parcialmente

public class P002122 {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream fl = new PrintStream(new File("POO_2122EP.txt"));
		test(System.out); // executa e escreve na consola
		test(fl); // executa e escreve no ficheiro
		fl.close();
	}

	private static void test(PrintStream out) {
		alinea1(out);
		alinea2(out);
	}

	private static void alinea1(PrintStream out) {
		out.println("\nAlínea 1) ----------------------------------\n");

		EventManager grandesEventos = new EventManager("Great outdoors");

		Client c1 = grandesEventos.addClient("The best", "Aveiro");
		Client c2 = grandesEventos.addClient("Team mates", "Coimbra");
		Client c3 = grandesEventos.addClient("Cool bunch", "Viseu");

		Event e1 = grandesEventos.addEvent(c1, LocalDate.parse("2022-06-24"));
		e1.addActivity(new Sport(Sport.Modality.KAYAK, 10));	
			e1.addActivity(new Catering(Catering.Option.FULL_MENU, 12));
			e1.addActivity(new Catering(Catering.Option.DRINKS_AND_SNACKS, 20));
		out.println(e1);
		Event e2 = grandesEventos.addEvent(c2, LocalDate.parse("2022-06-30"));
		e2.addActivity(new Catering(Catering.Option.LIGHT_BITES, 20));
			e2.addActivity(new Culture(Culture.Option.ARCHITECTURAL_TOUR, 10));
			e2.addActivity(new Culture(Culture.Option.RIVER_TOUR, 10));

		Event e3 = grandesEventos.addEvent(c3, LocalDate.parse("2022-06-25"));
		e3.addActivity(new Catering(Catering.Option.DRINKS_AND_SNACKS, 20));
			e3.addActivity(new Culture(Culture.Option.ART_MUSEUM, 20));

		Event e4 = grandesEventos.addEvent(c1, LocalDate.parse("2022-07-10"));
		e4.addActivity(new Sport(Sport.Modality.BIKE, 15));
			e4.addActivity(new Culture(Culture.Option.WINE_TASTING, 20));

		
		out.println(grandesEventos.listClients());
		out.println();
		out.println(grandesEventos.listEvents());
		
	}

	private static void alinea2(PrintStream out) {
		out.println("\nAlínea 2) ----------------------------------\n");
		EventManager em = new EventManager("A2"); // modificar
		
		// Adicione a seguir o código necessário para a leitura do ficheiro 
		try (Scanner sc = new Scanner(new File("src\\assets\\events.txt"))) {
    Event e = null;
    while (sc.hasNextLine()) {
        String edata = sc.nextLine().trim();
        if (edata.isEmpty()) continue;
        if (edata.charAt(0) == '#') {
            String[] cdata = edata.substring(1).split(",");
            Client client = em.addClient(cdata[0].trim(), cdata[1].trim());
            e = em.addEvent(client, LocalDate.parse(cdata[2].trim()));
        } else if (edata.charAt(0) == '*') {
            String[] activity_data = edata.substring(1).split(",");
            if (e != null) {
                switch (activity_data[0].trim()) {
                    case "Culture":
                        e.addActivity(new Culture(Culture.Option.valueOf(activity_data[1].trim()), Integer.parseInt(activity_data[2].trim())));
                        break;
                    case "Catering":
                        e.addActivity(new Catering(Catering.Option.valueOf(activity_data[1].trim()), Integer.parseInt(activity_data[2].trim())));
                        break;
                    case "Sport":
                        e.addActivity(new Sport(Sport.Modality.valueOf(activity_data[1].trim()), Integer.parseInt(activity_data[2].trim())));
                        break;
                }
            }
        }
    }
}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}




        // não modifique o metodo a partir daqui. Pode comentar para executar o programa

		if (em != null) {
			out.println(em + ": CLIENTES COM EVENTOS");
			for (String s: em.getClientsWithEvents())  // devolve todos os clientes com eventos
				out.println(s); 
			out.println("\n" + em + ": LISTA DE PRÓXIMOS EVENTOS ORDENADOS POR DATA");	
			for (String s: em.getNextEventsByDate())  // devolve todos os eventos, ordenados por data
				out.println(s); 
			out.println();
		}
	}

}

