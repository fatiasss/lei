import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parte1 {
    // -----------------------------------------------------
    //          !!! Não alterar este código !!!
    // -----------------------------------------------------
    private static int v1;
    private static List<Integer> v2 = new ArrayList<>();
    private static String v3;
    private static List<String> v4 = new ArrayList<>();
    // -----------------------------------------------------

    // -----------------------------------------------------------
    // >> Pode acrescentar novos métodos aqui (não é obrigatório)
    // -----------------------------------------------------------

    // pex., public static void ler(SOURCE) {}

    // -----------------------------------------------------------

    public static void lerTeclado() {
        Scanner sc = new Scanner(System.in);
        v1 = sc.nextInt();
        sc.nextLine();
        String tempString = sc.nextLine();
        String[] stringArray = tempString.split(" ");
        for(String x: stringArray){
            v2.add(Integer.parseInt(x));
        }
        v3 = sc.nextLine();
        String tempString2 = sc.nextLine();
        String[] stringArray2 = tempString2.split(" ");
        for(String x: stringArray2){
            v4.add(x);
        }
        sc.close();
    }

    public static void lerFicheiro() {
        Path fich = Paths.get("_124964\\inputs.txt");
        try (Scanner sc = new Scanner(fich)) {
                v1 = sc.nextInt();
                sc.nextLine();
                String[] stringArray = sc.nextLine().split(" ");
                for(String x: stringArray){
                    v2.add(Integer.parseInt(x));
                }
                v3 = sc.nextLine();
                String[] stringArray2 = sc.nextLine().split(" ");
                for(String x: stringArray2){
                        v4.add(x);
                }
                sc.close();

                
        } catch (IOException e) {
            System.out.println("Certifique-se que o ficheiro \"inputs.txt\" está na raiz da pasta do projeto");
        }


    }

    public static void imprimir() {
        System.out.print(v1 + "\n");
        for(int x: v2){
            System.out.print(x + " ");
        }
        System.out.print("\n" + v3 + "\n");
        for(String x: v4){
            System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {
        // -----------------------------------------------------------------
        // !!! Não imprimir texto a pedir determinado input, ler direto !!!
        // -----------------------------------------------------------------

        // pode editar livremente este código
        //lerTeclado();
        lerFicheiro();
        imprimir();
    }
}
