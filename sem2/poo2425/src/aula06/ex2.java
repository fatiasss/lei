package aula06;

import aula06.ex1.Pessoa;
import myutils.DateYMD;

import java.util.Scanner;
import java.util.ArrayList;

public class ex2 {
    public class Contacto{
        private int id;
        static int lastid = 0;
        private Pessoa pessoa;
        private String phone;
        private String email;
        public boolean isValidPhone(String phone){
            return phone.matches("9||d{8}");
    
        }
        public boolean isValidEmail(String email){
            return email.matches("^.@{1}(gmail|hotmail|sapo)\\.(pt|com|org)$");
        }
        public Contacto(Pessoa pessoa, String phone, String email){
            this.pessoa = pessoa;
            if(isValidPhone(phone) && phone!=null){
                this.phone = phone;
            }else{
                throw new IllegalArgumentException("Invalid Phone Number");
            }
            if(isValidEmail(email) && email!=null){
                this.email = email;
            }else{
                throw new IllegalArgumentException("Invalid Email");
            }
            lastid++;
            this.id = lastid;
        }
        public Contacto(Pessoa pessoa, String PhoneEmail){
            this.pessoa = pessoa;
            if((PhoneEmail!=null && isValidPhone(PhoneEmail))){
                this.phone = PhoneEmail;
            }
            else{
                if(PhoneEmail!=null && isValidEmail(PhoneEmail)){
                    this.email = PhoneEmail;
                }
                else{
                    throw new IllegalArgumentException("Invalid Contact Info");
                }
            }
            lastid++;
            this.id = lastid;
        }
    }
    public class ContactManager{
        ArrayList<Contacto> contactlist = new ArrayList<>();
        public void run(){
            Scanner sc = new Scanner(System.in);
            while(true){
            System.out.println("1. Inserir contacto");
            System.out.println("2. Alterar contacto");
            System.out.println("3. Apagar contacto");
            System.out.println("4. Procurar contacto");
            System.out.println("5. Listar contactos");
            System.out.println("0. Sair");
            int option = sc.nextInt();
            switch(option){
                case(1): createContact(); 
                case(2): changeContact(); 
                case(3): deleteContact(); 
                case(4): searchContact(); 
                case(5): listContacts(); 
                case(0): break;
            }
            sc.close();
            }
            


        }
        public void createContact(){
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Insert person name");
            String name = sc2.nextLine();
            System.out.print("Insert CC");
            int cc = sc2.nextInt();
            System.out.print("Insert birthday");
            int birthday = sc2.nextInt();
            System.out.print("Insert birth month");
            int month = sc2.nextInt();
            System.out.print("Insert birth year");
            int year = sc2.nextInt();
            System.out.print("0 - Only Phone; 1 - Only Email; 2- Both");
            int option = sc2.nextInt();
            String phone;
            String email;
            switch(option){

                case(0): 
                System.out.print("Insert phone number");
                phone = sc2.nextLine();
                contactlist.add(new Contacto(new Pessoa(name, cc, new DateYMD(birthday, month, year)), phone));
                System.out.print("Contact created!");
                break;
                case(1):
                System.out.print("Insert email");
                email = sc2.nextLine();
                contactlist.add(new Contacto(new Pessoa(name, cc, new DateYMD(birthday, month, year)), email));
                System.out.print("Contact created!");
                break; 
                case(2):
                System.out.print("Insert phone number");
                phone = sc2.nextLine();
                System.out.print("Insert email");
                email = sc2.nextLine();
                contactlist.add(new Contacto(new Pessoa(name, cc, new DateYMD(birthday, month, year)), phone));
                System.out.print("Contact created!");
                break;
                default:
                System.out.print("Invalid option selected");

            }
            

            sc2.close();

        }
        public void changeContact(){

        }
        public void deleteContact(){
            
        }
        public void searchContact(){

        }
        public void listContacts(){

        }
    }

    
}
