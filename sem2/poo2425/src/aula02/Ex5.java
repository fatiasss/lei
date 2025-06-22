package aula02;
import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();

        System.out.print("In undercase " + sentence.toLowerCase() + "\n");
        System.out.print("Last Character " + sentence.charAt(sentence.length()-1) + "\n");
        System.out.print("Start chars " + sentence.substring(0, 3) + "\n");


        sc.close();

    }
    
}
