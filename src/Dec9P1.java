import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Dec9P1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        long counter = 0;
        while(sc.hasNextLine()){
            Integer[] line = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
            Integer[][] matrix = new Integer[line.length][];
            matrix[0] = line;
            boolean finished = false;
            counter += line[line.length-1];
            for (int i = 0; i < line.length && !finished; i++){
                finished = true;
                matrix[i+1] = new Integer[line.length];
                for (int j = 0; j < line.length-i-1; j++){
                    matrix[i+1][j] = matrix[i][j+1] - matrix[i][j];
                    if (matrix[i+1][j] != 0){
                        finished = false;
                    }
                    if (j == line.length-i-2) {
                        counter += matrix[i+1][j];
                    }
                }
            }
        }
        out.print(counter);
        out.close();
        sc.close();
    }
}
