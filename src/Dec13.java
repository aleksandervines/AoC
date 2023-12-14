import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Dec13 {

    public static void main(String[] args) {
        // Brute force - ikke spesielt effektiv løsning :/
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        long sum = 0;
        int requiredSmudges = 1;
        while(sc.hasNextLine()){
            List<char[]> map = new ArrayList<>();
            List<int[]> alternatives = null;
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if (line.isBlank())
                    break;
                map.add(line.toCharArray());
                if (alternatives == null){
                    alternatives = IntStream.rangeClosed(0, line.length()-2).boxed().map(i -> new int[]{i,0}).toList();
                }
                List<int[]> updatedAlternatives = new ArrayList<>();
                for (int[] i: alternatives){
                    boolean match = true;
                    for (int j = 0; match && i[0]-j >= 0 && i[0]+j+1 < line.length(); j++){
                        if (line.charAt(i[0]-j)!=line.charAt(i[0]+j+1)){
                            if (++i[1] > requiredSmudges)
                                match = false;
                        }
                    }
                    if (match){
                        updatedAlternatives.add(i);
                    }
                }
                alternatives = updatedAlternatives;
            }
            int thisSum = -1;
            if (!alternatives.isEmpty()) {
                out.print("Vertical option:");
                String delimiter = "";
                for (int[] i: alternatives){
                    out.printf("%s%d-%d",delimiter,i[0],i[1]);
                    delimiter = ", ";
                    if (i[1] == requiredSmudges){
                        out.print("**");
                        if (thisSum != -1)
                            throw new RuntimeException("NEINEINEI");
                        thisSum = i[0]+1;
                    }
                }
                out.println();;
            }
            if (thisSum == -1){
                alternatives = IntStream.rangeClosed(0, map.size()-2).boxed().map(i -> new int[]{i,0}).toList();
                for (int k = 0; k < map.get(0).length; k++){
                    List<int[]> updatedAlternatives = new ArrayList<>();
                    for (int[] i: alternatives){
                        boolean match = true;
                        for (int j = 0; match && i[0]-j >= 0 && i[0]+j+1 < map.size(); j++){
                            if (map.get((i[0]-j))[k]!=map.get(i[0]+j+1)[k]){
                                if (++i[1] > requiredSmudges)
                                    match = false;
                            }
                        }
                        if (match){
                            updatedAlternatives.add(i);
                        }
                    }
                    alternatives = updatedAlternatives;
                }
                if (!alternatives.isEmpty()) {
                    out.print("Horisontal option:");
                    String delimiter = "";
                    for (int[] i: alternatives){
                        out.printf("%s%d-%d",delimiter,i[0],i[1]);
                        delimiter = ", ";
                        if (i[1] == requiredSmudges){
                            out.print("**");
                            if (thisSum != -1)
                                throw new RuntimeException("NEINEINEI2");
                            thisSum = 100*(i[0]+1);
                        }
                    }
                    out.println();;
                }
            }
            if (thisSum == -1)
                throw new RuntimeException("NEINEINEI2");
            sum+=thisSum;
        }
        out.println(sum);
        out.close();
        sc.close();
    }
}
