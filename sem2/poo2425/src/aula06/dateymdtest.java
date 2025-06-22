package aula06;

import java.util.Scanner;

import myutils.DateYMD;

public class dateymdtest {
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int input = -1;
        DateYMD date = null;
        while(input!=0){
            System.out.print("Date operations:\r\n1 - create new date\r\n2 - show current date\r\n3 - increment date\r\n4 - decrement date\r\n0 - exit");                  
            input = sc.nextInt();
            switch(input){
                case 1: 
                System.out.print("Insert day");
                int inputday = sc.nextInt();
                System.out.print("Insert month");
                int inputmonth = sc.nextInt();
                System.out.print("Insert year");
                int inputyear = sc.nextInt();
                date = new DateYMD(inputday, inputmonth, inputyear);
                break;

                case 2: if(date!=null){System.out.print(date.toString() + "\n");};
                break;
                case 3: if(date!=null){date.increment();} 
                break;
                case 4: if(date!=null){date.decrement();} 
                break;
            }
            
    
        }
        sc.close();
    
    
    
    }
    
}
