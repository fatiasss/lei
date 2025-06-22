package aula10.ex1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.io.FileNotFoundException;

public class PairCounter {
    private static TreeMap<String,TreeMap<String, Integer>> pairsMap = new TreeMap<>(); 

    public void readList(String filename){

        try (Scanner myReader = new Scanner(new File("src/assets/" + filename))) {
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] stringData = data.split("[,\\?\\t\\n.:'‘’;!\\-*{}=+&/()\\[\\]”“\"'  ]");
            List<String> stringDataList = new ArrayList<>(List.of(stringData));
            List<String> filteredList = stringDataList.stream()
                                           .filter(word -> word.length() >= 3)
                                           .toList();

            for(int i = 0; i<filteredList.size()-1; i++){
                String word = filteredList.get(i);
                String nextword = filteredList.get(i+1);

                pairsMap.computeIfAbsent(word, k -> new TreeMap<>()).merge(nextword, 1, Integer::sum);


/*                 if(pairsMap.containsKey(word)){
                    TreeMap<String, Integer> accessedword = pairsMap.get(word);
                    if(accessedword.containsKey(nextword)){
                        int currentvalue = accessedword.get(nextword);
                        accessedword.put(nextword, currentvalue + 1);
                    }
                    else{
                        accessedword.put(nextword, 1);
                    }

                }
                else{
                    TreeMap<String, Integer> innerMap = new TreeMap<>();
                    innerMap.put(nextword, 1);
                    pairsMap.put(word, innerMap);
                }
 */
            }


            
      }
        }catch (FileNotFoundException e) {
        System.out.println("File not found.");
    }
    }

    public void printList(){
        pairsMap.forEach((word, innerMap) -> {
           /*  StringBuilder printStr = new StringBuilder();
            printStr.append(word).append("={");
            for(String x: innerMap.keySet()){
                printStr.append(x).append("=").append(innerMap.get(x)).append(",");
            }
            printStr.deleteCharAt(printStr.length() - 1).append("}\n"); */

            StringJoiner joiner = new StringJoiner(",", word + "={", "}\n");
            innerMap.forEach((nextword, count) -> joiner.add(nextword + "=" + count)); ;
            System.out.print(joiner);
           
        });
    }
    public static void main(String[] args) {
        PairCounter obj = new PairCounter();
        obj.readList("major.txt");
        obj.printList();
        
        
    }

}
