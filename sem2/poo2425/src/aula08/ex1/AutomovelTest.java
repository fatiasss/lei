package aula08.ex1;

public class AutomovelTest {
    public static void main(String[] args) {
        Automovel automovel = new Automovel("123-ABC", "Toyota", "Corolla", 120);
        System.out.println("Testing Constructor and Getters:");
        System.out.println("Matricula: " + automovel.getMatricula().equals("123-ABC"));
        System.out.println("Marca: " + automovel.getMarca().equals("Toyota"));
        System.out.println("Modelo: " + automovel.getModelo().equals("Corolla"));
        System.out.println("Potencia: " + (automovel.getPotencia() == 120));

        automovel.setMatricula("456-DEF");
        automovel.setMarca("Honda");
        automovel.setModelo("Civic");
        automovel.setPotencia(150);
        System.out.println("\nTesting Setters:");
        System.out.println("Matricula: " + automovel.getMatricula().equals("456-DEF"));
        System.out.println("Marca: " + automovel.getMarca().equals("Honda"));
        System.out.println("Modelo: " + automovel.getModelo().equals("Civic"));
        System.out.println("Potencia: " + (automovel.getPotencia() == 150));

        automovel.trajeto(50);
        automovel.trajeto(100);
        System.out.println("\nTesting trajeto, ultimoTrajeto, and distanciaTotal:");
        System.out.println("Ultimo Trajeto: " + (automovel.ultimoTrajeto() == 100));
        System.out.println("Distancia Total: " + (automovel.distanciaTotal() == 150));

        System.out.println("\nTesting toString:");
        System.out.println(automovel.toString());

        Automovel automovel2 = new Automovel("456-DEF", "Honda", "Civic", 150);
        Automovel automovel3 = new Automovel("789-GHI", "Ford", "Focus", 130);
        System.out.println("\nTesting equals:");
        System.out.println("Equals (same values): " + automovel.equals(automovel2));
        System.out.println("Equals (different values): " + !automovel.equals(automovel3));
        Automovel automovel1 = new Automovel("123-ABC", "Toyota", "Corolla", 120);
        Automovel automovel4 = new Automovel("456-DEF", "Honda", "Civic", 150);
        Automovel automovel5 = new Automovel("789-GHI", "Ford", "Focus", 130);
        
        automovel1.trajeto(100);
        automovel1.trajeto(50);
        
        automovel4.trajeto(200);
        
        automovel5.trajeto(150);
        automovel5.trajeto(50);
        
        EmpresaViaturas empresa = new EmpresaViaturas();
        
        empresa.addVeiculo(automovel1);
        empresa.addVeiculo(automovel4);
        empresa.addVeiculo(automovel5);
        
        System.out.println("\nListing all Automoveis in the Empresa:");
        empresa.listAutomoveis();
        
        Automovel maxKmAutomovel = empresa.maxKm();
        System.out.println("\nAutomovel with the maximum kilometers:");
        System.out.println(maxKmAutomovel);
        
        Automovel minKmAutomovel = empresa.minKm();
        System.out.println("\nAutomovel with the minimum kilometers:");
        System.out.println(minKmAutomovel);
        Atleta newAtleta = new Atleta("Pessoa", "Portugal", "Atletismo");
        newAtleta.alugar(minKmAutomovel);
            }
    }