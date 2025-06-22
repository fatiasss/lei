package aula02;
import java.util.Scanner;
import java.util.ArrayList;

public class Ex4 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> list = new ArrayList<Double>();
        double n1, n, max, min, sum, avg;
        
        int c = 1;
        System.out.print("Insira o primeiro número");
        n1= sc.nextDouble();
        list.add(n1);
        min = max = n1;
        sum = 0;
        while (true) {
            System.out.print("Insira um número");
            n = sc.nextDouble();
            list.add(n);
            if (n==n1) break;
        }
        for (double x : list) {
            min = Math.min(min, x);
            max = Math.max(max, x);
            sum+=x;
        }
        c= list.size();
        avg = sum/c;
        System.out.printf("Min:%.2f; Max:%.2f; Sum:%.2f; Avg:%.2f;\n", min, max, sum, avg);
        for (double x : list){
            if (x > avg){
                System.out.println(x);
            }
        }
        sc.close();
    }
    
}
