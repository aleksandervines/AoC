import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Dec9P2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        long counter = 0;
        while(sc.hasNextLine()){
            List<Integer> line = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            Collections.reverse(line);
            System.out.println("New line:");
            line.stream().forEach(integer -> System.out.print(integer + " "));
            System.out.println();
            int length = line.size();
            Integer[][] matrix = new Integer[length][];
            matrix[0] = line.toArray(Integer[]::new);
            boolean finished = false;
            counter += matrix[0][length -1];
            for (int i = 0; i < length && !finished; i++){
                finished = true;
                matrix[i+1] = new Integer[length];
                for (int j = 0; j < length -i-1; j++){
                    matrix[i+1][j] = matrix[i][j] - matrix[i][j+1];
                    if (matrix[i+1][j] != 0){
                        finished = false;
                    }
                    if (j == length -i-2) {
                        if (i % 2 == 0)
                            counter -= matrix[i+1][j];
                        else
                            counter += matrix[i+1][j];
                    }
                }
                for (int j = 0; j < i+1; j++)
                    System.out.print(" ");
                Arrays.stream(matrix[i+1]).forEach(integer -> {
                    if (integer != null)
                        System.out.print(integer + " ");
                });
                System.out.println();
            }
            System.out.println("counter:"+counter);
        }
        out.print(counter);
        out.close();
        sc.close();
    }
}
