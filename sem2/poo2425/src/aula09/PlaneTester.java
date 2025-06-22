package aula09;

import java.util.Scanner;

public class PlaneTester {
    public static void main(String[] args) {
        PlaneManager manager = new PlaneManager();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar avião comercial");
            System.out.println("2. Adicionar avião militar");
            System.out.println("3. Remover avião");
            System.out.println("4. Procurar avião");
            System.out.println("5. Imprimir todos os aviões");
            System.out.println("6. Imprimir aviões comerciais");
            System.out.println("7. Imprimir aviões militares");
            System.out.println("8. Imprimir avião mais rápido");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            option = sc.nextInt();
            sc.nextLine(); // Consume leftover newline

            switch (option) {
                case 1: // Add Commercial Plane
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Fabricante: ");
                    String manufacturer = sc.nextLine();
                    System.out.print("Modelo: ");
                    String model = sc.nextLine();
                    System.out.print("Ano: ");
                    int year = sc.nextInt();
                    System.out.print("Máx. Passageiros: ");
                    int pmax = sc.nextInt();
                    System.out.print("Velocidade Máx.: ");
                    int vmax = sc.nextInt();
                    System.out.print("Número de Pilotos: ");
                    int pilots = sc.nextInt();
                    manager.addPlane(new CommercialPlane(id, manufacturer, model, year, pmax, vmax, pilots));
                    System.out.println("Avião comercial adicionado com sucesso!");
                    break;

                case 2: // Add Military Plane
                    System.out.print("ID: ");
                    id = sc.nextLine();
                    System.out.print("Fabricante: ");
                    manufacturer = sc.nextLine();
                    System.out.print("Modelo: ");
                    model = sc.nextLine();
                    System.out.print("Ano: ");
                    year = sc.nextInt();
                    System.out.print("Máx. Passageiros: ");
                    pmax = sc.nextInt();
                    System.out.print("Velocidade Máx.: ");
                    vmax = sc.nextInt();
                    System.out.print("Munição: ");
                    sc.nextLine(); // Consume leftover newline
                    int ammo = sc.nextInt();
                    manager.addPlane(new MilitaryPlane(id, manufacturer, model, year, pmax, vmax, ammo));
                    System.out.println("Avião militar adicionado com sucesso!");
                    break;

                case 3: // Remove Plane
                    System.out.print("ID do avião a remover: ");
                    id = sc.nextLine();
                    manager.removePlane(id);
                    System.out.println("Avião removido com sucesso!");
                    break;

                case 4: // Search Plane
                    System.out.print("ID do avião a procurar: ");
                    id = sc.nextLine();
                    Plane plane = manager.searchPlane(id);
                    if (plane != null) {
                        System.out.println("Avião encontrado:");
                        System.out.println(plane);
                    } else {
                        System.out.println("Avião não encontrado.");
                    }
                    break;

                case 5: // Print All Planes
                    System.out.println("Todos os aviões:");
                    manager.printAllPlanes();
                    break;

                case 6: // Print Commercial Planes
                    System.out.println("Aviões comerciais:");
                    for (CommercialPlane cp : manager.getCommercialPlanes()) {
                        System.out.println(cp);
                    }
                    break;

                case 7: // Print Military Planes
                    System.out.println("Aviões militares:");
                    for (MilitaryPlane mp : manager.getMilitaryPlanes()) {
                        System.out.println(mp);
                    }
                    break;

                case 8: // Print Fastest Plane
                    Plane fastestPlane = manager.getFastestPlane();
                    if (fastestPlane != null) {
                        System.out.println("Avião mais rápido:");
                        System.out.println(fastestPlane);
                    } else {
                        System.out.println("Não há aviões na frota.");
                    }
                    break;

                case 9: // Exit
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 9);

        sc.close();
    }
}
