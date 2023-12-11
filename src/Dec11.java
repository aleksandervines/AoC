import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Dec11 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        List<List<Boolean>> image = new ArrayList<>();
        int EMPTY_MULTIPLIER = 1000000;
        while (sc.hasNextLine()) {
            boolean emptyLine = true;
            String line = sc.nextLine();
            List<Boolean> nextLine = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                nextLine.add(line.charAt(i) == '#');
                if (line.charAt(i) == '#') {
                    emptyLine = false;
                }
            }
            if (emptyLine) {
                image.add(null);
            } else {
                image.add(nextLine);
            }
        }
        int emptyColumns = 0;
        long sum = 0;
        List<int[]> galaxies = new ArrayList<>();
        for (int x = 0; x < image.get(0).size(); x++) {
            boolean empty = true;
            int emptyRows = 0;
            for (int y = 0; y < image.size(); y++) {
                if (image.get(y) != null) {
                    if (image.get(y).get(x)) {
                        empty = false;
                        for (int[] galaxy : galaxies) {
                            long distance = (Math.abs(y - galaxy[0]+(emptyRows*(EMPTY_MULTIPLIER-1))) + (Math.abs(x - galaxy[1] + (emptyColumns*(EMPTY_MULTIPLIER-1)))));
                            //System.out.printf("[%d,%d] -> [%d,%d] = %d%n", y+(emptyRows*EMPTY_MULTIPLIER), x + (emptyColumns*EMPTY_MULTIPLIER), galaxy[0], galaxy[1], distance);
                            sum += distance;
                        }
                        galaxies.add(new int[]{y +(emptyRows*(EMPTY_MULTIPLIER-1)), x + (emptyColumns*(EMPTY_MULTIPLIER-1))});
                    }
                } else {
                    emptyRows++;
                }
            }
            if (empty)
                emptyColumns++;
        }
        for (int[] galaxy : galaxies) {
            //System.out.printf("Galaxy at [%d,%d]%n", galaxy[0], galaxy[1]);
        }
        out.println(sum);
        out.close();
        sc.close();
    }
}
