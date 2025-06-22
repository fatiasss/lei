package aula03;
import java.util.Scanner;

public class ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String date;
        int weekday;
        do{
            System.out.print("Insert a date! mm/yyyy\n");
            date= sc.nextLine();

        }while (!date.matches("\\d{2}" + "/" + "\\d{4}"));
        do{
            System.out.print("Insert the day of the week the month starts!\n(1 = Monday, 2 = Tuesday, 3 = Wednesday, 4 = Thursday, 5 = Friday, 6 = Saturday, 7 = Sunday");
            weekday= sc.nextInt();
        }while (weekday>7 || weekday<1);

        sc.close();
    }

}
