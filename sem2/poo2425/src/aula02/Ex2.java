package aula02;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args){
        int point1x, point2x, point1y, point2y, xd, yd;
        Scanner sc = new Scanner(System.in);
        System.out.print("1X");
        point1x = sc.nextInt();
        System.out.print("1Y");
        point1y = sc.nextInt();
        System.out.print("2X");
        point2x = sc.nextInt();
        System.out.print("2Y");
        point2y = sc.nextInt();

        yd = Math.abs(point2y-point1y);
        xd = Math.abs(point2x-point1x);
        double d = Math.hypot(xd, yd);
        System.out.printf("Distância entre os pontos %.2f", d);
        sc.close();
    }




    
}
