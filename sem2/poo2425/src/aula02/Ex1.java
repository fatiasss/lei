package aula02;
import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args){
        int hours, minutes, seconds, resto;
        Scanner sc =  new Scanner(System.in);
        seconds = sc.nextInt();
        hours = seconds/3600;
        resto = seconds%3600;
        minutes = resto/60;
        resto = resto%60;
        
        System.out.printf("%02d:%02d:%02d\n\n", hours, minutes, resto);
        sc.close();

    }
}
