package aula03;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTester {
    public static double[] Tester(int DIM) {
    Collection<Integer> col = new ArrayList<>();
    return checkPerformance(col, DIM);
    }
    private static double[] checkPerformance(Collection<Integer> col, int DIM) {
    double start, stop, delta1, delta2, delta3;
    // Add
    start = System.nanoTime(); // clock snapshot before
    for(int i=0; i<DIM; i++ )
    col.add( i );
    stop = System.nanoTime(); // clock snapshot after
    delta1 = (stop-start)/1e6; // convert to milliseconds
    System.out.println(col.size()+ ": Add to " +
    col.getClass().getSimpleName() +" took " + delta1 + "ms");
    // Search
    start = System.nanoTime(); // clock snapshot before
    for(int i=0; i<DIM; i++ ) {
    int n = (int) (Math.random()*DIM);
    if (!col.contains(n)) 
    System.out.println("Not found???"+n);
    }
    stop = System.nanoTime(); // clock snapshot after
    delta2 = (stop-start)/1e6; // convert nanoseconds to milliseconds
    System.out.println(col.size()+ ": Search to " + 
    col.getClass().getSimpleName() +" took " + delta2 + "ms");
    // Remove
    start = System.nanoTime(); // clock snapshot before
    Iterator<Integer> iterator = col.iterator();
    while (iterator.hasNext()) {
     iterator.next();
     iterator.remove();
    }
    stop = System.nanoTime(); // clock snapshot after
    delta3 = (stop-start)/1e6; // convert nanoseconds to milliseconds
    System.out.println(col.size() + ": Remove from "+ 
    col.getClass().getSimpleName() +" took " + delta3 + "ms");
    double[] delta = {delta1, delta2, delta3};
    return delta;
    }
    public static void main(String[] args) {
        double[]  var1, var2, var3, var4, var5, var6;
        var1=Tester(1000);
        var2=Tester(5000);
        var3=Tester(10000);
        var4=Tester(20000);
        var5=Tester(40000);
        var6=Tester(100000);
        System.out.printf("%-10s %10d %10d %10d %10d %10d %10d%n","Collection", 1000, 5000, 10000, 20000, 40000, 100000);
        System.out.printf("%-10s %10.2f %10.2f %10.2f %10.2f %10.2f %10.2f%n","add", var1[0],var2[0],var3[0],var4[0],var5[0],var6[0]);
        System.out.printf("%-10s %10.2f %10.2f %10.2f %10.2f %10.2f %10.2f%n","search",var1[1],var2[1],var3[1],var4[1],var5[1],var6[1]);
        System.out.printf("%-10s %10.2f %10.2f %10.2f %10.2f %10.2f %10.2f%n", "remove",var1[2],var2[2],var3[2],var4[2],var5[2],var6[2]);



    }
    }