package aula02;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Ex7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        String[] words = sentence.split("[ +]");
        StringBuilder sb = new StringBuilder();
        for(String x : words){
            if (x.length()>3){sb.append(x.charAt(0));}
        }
        System.out.print(sb);
        sc.close();



    }
}
