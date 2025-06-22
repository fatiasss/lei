package aula09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import javax.sound.midi.SysexMessage;

import aula06.ex1.Pessoa;
import myutils.DateYMD;

public class ALDemo {
public static void main(String[] args) {
ArrayList<Integer> c1 = new ArrayList<>();
for (int i = 10; i <= 100; i+=10) 
    c1.add(i);
System.out.println("Size: " + c1.size());
for (int i = 0; i < c1.size(); i++) 
    System.out.println("Elemento: " + c1.get(i));
ArrayList<String> c2 = new ArrayList<>();
c2.add("Vento");
c2.add("Calor");
c2.add("Frio");
c2.add("Chuva");
System.out.println(c2);
Collections.sort(c2);
System.out.println(c2);
System.out.println(c2.contains("Frio"));
System.out.println(c2.lastIndexOf("Frio"));
c2.remove("Frio"); 
System.out.println(c2.contains("Frio"));
c2.remove(0);
System.out.println(c2);


HashSet<Pessoa> c3 = new HashSet<>();
c3.add(new Pessoa("Inês", 0, new DateYMD(21, 4, 2006)));
c3.add(new Pessoa("Ibês", 1, new DateYMD(22, 4, 2006)));
c3.add(new Pessoa("Icês", 2, new DateYMD(23, 4, 2006)));
c3.add(new Pessoa("Idês", 3, new DateYMD(21, 4, 2006)));
c3.add(new Pessoa("Iês", 4, new DateYMD(29, 4, 2006)));
c3.add(new Pessoa("Inês", 0, new DateYMD(21, 4, 2006)));
System.out.println(c3);

TreeSet<DateYMD> c4 = new TreeSet<>();
c4.add(new DateYMD(21, 4, 2006));
c4.add(new DateYMD(20, 4, 2006));
c4.add(new DateYMD(21, 3, 2006));
c4.add(new DateYMD(21, 12, 2005));
System.out.println(c4);
}
}
