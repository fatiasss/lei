package aula02;
import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        double a, b, h;

        do{
            System.out.print("Cateto 1");
            a = sc.nextDouble();
        } while (a<=0);
        do{
            System.out.print("Cateto 2");
            b = sc.nextDouble();
        } while (b<=0);
        
        h = Math.hypot(a, b);
        double angle = Math.atan2(a, b);
        System.out.printf("A hipotnusa é: %.02f", h);
        System.out.printf("O ângulo é: %.02f", angle);
        sc.close();
    }
}
