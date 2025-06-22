package aula02;
import java.util.Scanner;
import java.lang.StringBuilder;

public class Ex6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        System.out.printf("The sentence has %01d digits",CountDigits(sentence));
        System.out.printf("The sentence has %01d spaces",CountSpaces(sentence));
        System.out.print("Is it lowercase?" + String.valueOf(IsLowerCase(sentence)));
        System.out.print("Is it a palindrome?" + String.valueOf(isPalindrome(sentence)));
        sc.close();
    }
    public static int CountDigits(String words){
        int c=0;
        for (int x = 0; x<words.length(); x++){
            if (String.valueOf(words.charAt(x)).matches("[0-9]")){
                c++;
            }
        }
        return c;

    }
    public static int CountSpaces(String words){
        int c=0;
        for (int x = 0; x<words.length(); x++){
            if (String.valueOf(words.charAt(x)).matches(" ")){
                c++;
            }
        }
        return c;
    }
    public static boolean IsLowerCase(String words){
        if (words== words.toLowerCase()){return true;}
        else{return false;}

    }
    public static boolean isPalindrome(String words){
        StringBuilder reversed = new StringBuilder(words).reverse();
        return words.equals(reversed.toString());
    }
}
