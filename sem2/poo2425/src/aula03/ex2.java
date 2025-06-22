package aula03;
import java.util.Scanner;
import java.lang.Math;


public class ex2 {

    public static int CompGuess(int secret){
        int number, guesses=0, low=0, high=100;
        do{
            number = Math.round(low+high)/2;
            guesses++;
            if(number<secret){low=number;}else{high=number;}
        }while(number!=secret); 

        return guesses;

    }
    public static void HighLow(){
        Scanner sc = new Scanner(System.in);
        int number, playerguesses = 0;
        int secret = (int)((Math.random() * (99)+ 1));
        do{
            number = sc.nextInt();
            playerguesses++;
            if(number<secret){System.out.print("Low\n");}
            else{System.out.print("High\n");}
        }while(number!=secret);

        System.out.print("The computer got it in " + CompGuess(secret) + " guesses, you got it in " + playerguesses + "\n");
        System.out.print("Right! Play again?(Y/N)");

        sc.nextLine();
        String answer = sc.nextLine();

        if(answer.equals("Y")){HighLow();}
        else{sc.close();};
        
    }
        public static void main(String[] args) {
        HighLow();
    }


}
