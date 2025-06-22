package galeria;
import java.util.ArrayList;
import java.util.Scanner;

public class Galeria {
    private ArrayList<Obra> obras;
    private double lucro;
    private int size;
    public Galeria(int size){
        obras = new ArrayList<>(size);
        this.size = size;

    }
    public void addObra(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Tipo de obra:\n1. Quadro\n2. Escultura\n3. Obra Digital (NFT)");
        int typeint=-1;
        String name;
        String author;
        double base;

        while(typeint>3 || typeint<1){
            typeint = sc.nextInt();
            sc.nextLine();
        }
        switch(typeint){
            case(1):
                String paint;
                String framed;
                String size;
                System.out.print("Nome do Quadro: ");
                name = sc.nextLine();
                System.out.print("Autor do Quadro: ");
                author = sc.nextLine();
                System.out.print("Preço do Quadro: ");
                base = sc.nextDouble();
                sc.nextLine();
                System.out.print("Tipo de Tinta: ");
                paint = sc.nextLine();
                System.out.print("Obra emoldurada? (s/n): ");
                framed = sc.nextLine();
                System.out.print("Tamanho da Tela: ");
                size = sc.nextLine();
                obras.add(new Quadro(name, author, base, paint, size, framed));
                break;
            case(2):
                String material;
                int quantity;
                System.out.print("Nome da Escultura: ");
                name = sc.nextLine();
                System.out.print("Autor da Escultura: ");
                author = sc.nextLine();
                System.out.print("Preço da Escultura: ");
                base = sc.nextDouble();
                sc.nextLine();
                System.out.print("Material: ");
                material = sc.nextLine();
                System.out.print("Quantos exemplares?: ");
                quantity = sc.nextInt();
                sc.nextLine();
                obras.add(new Escultura(name, author, base, material, quantity));
                break;
            
            case(3):
                String block;
                String hex;
                System.out.print("Nome do NFT: ");
                name = sc.nextLine();
                System.out.print("Autor do NFT: ");
                author = sc.nextLine();
                System.out.print("Preço do NFT: ");
                base = sc.nextDouble();
                sc.nextLine();
                System.out.print("Blockchain: ");
                block = sc.nextLine();
                System.out.print("Hex:");
                hex = sc.nextLine();
                obras.add(new ObraDigital(name, author, base, block, hex));
                break;

        }

        
    }
    public void listObra(){
        for(Obra x: obras){
            System.out.print(x.toString());
        }
    }
    public void sell(int id, double saleprice){
        for(int i=0; i<=(size-1); i++){
            if(obras.get(i).getId()==id){
                lucro+=saleprice-obras.get(i).getBasePrice();
                obras.remove(i);
                break;
            }
        }
        System.out.print("Obra not found");
    }
    public void sellFront(){
        Scanner sc = new Scanner(System.in);
        int id;
        double saleprice;
        System.out.print("Que obra? (id): ");
        id = sc.nextInt();
        System.out.print("Preço de venda: ");
        saleprice = sc.nextDouble();
        sell(id, saleprice);
    }
    public double getLucro(){
        return lucro;
    }

    
}
