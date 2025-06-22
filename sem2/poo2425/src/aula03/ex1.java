package aula03;
import java.util.Scanner;
import java.util.ArrayList;

public class ex1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int number = 0;

        do{
            System.out.print("Insert a positive number");
            try{number = sc.nextInt();}
            catch(Exception e){
                System.out.print("Not a word\n");
                sc.nextLine();
                number=0;
            }

        }while (number<=0);
        System.out.print(allPrimes(number));
        sc.close();
    }
    public static boolean isPrime(int number){
        for(int i=2; i<number; i++){
            if(number%i==0){return false;}
        }
        return true;
    }
    public static ArrayList<Integer> allPrimes(int number){
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        for(int i=1; i<number; i++){
            if(isPrime(i)){retlist.add(i);}
        }
        return retlist;

    }

}
