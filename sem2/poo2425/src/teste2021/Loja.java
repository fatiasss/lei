package teste2021;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import aula08.ex3.prod;

public class Loja {
    private String name;
    private String link;
    private TreeSet<Produto> products = new TreeSet<>(Comparator.comparing(Produto::getCode));


    public Loja(String name, String link){
        this.name = name;
        this.link = link;

    }
    public int totalItems(){
        int total = products.stream()
                            .map(Produto::getStock)
                            .reduce(0, Integer::sum);
        return total;
    }

    public void add(Produto p){
        products.add(p);
    }

    public Produto getProdutoPelaDescricao(String d){
        Produto p = products.stream()  
                            .filter(prod -> prod.getDescricao().equals(d))
                            .findFirst()
                            .orElse(null);
        return p;
    }

    public TreeSet<Produto> reorder(){
        TreeSet<Produto> ordered = products.stream()
                                            .sorted(Comparator.comparing(Produto::getDescricao))
                                            .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Produto::getDescricao))));
        this.products = ordered;
        return ordered;
    }

    public void setNome(String nome){
        this.name = nome;
    }

    public void setEnderecoWeb(String link){
        this.link = link;
    }

    @Override

    public String toString(){
        StringBuilder retString = new StringBuilder();
        for(Produto p : products){
            retString.append(p);
        }
        return retString.toString();

    }


}
