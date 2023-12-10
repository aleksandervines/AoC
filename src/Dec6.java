import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class Dec6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        Integer[] times = Arrays.stream(sc.nextLine().split(":")[1].trim().split(" +")).map(Integer::parseInt).toArray(Integer[]::new);
        Long[] distances = Arrays.stream(sc.nextLine().split(":")[1].trim().split(" +")).map(Long::parseLong).toArray(Long[]::new);
        long multi = 1;
        for (int i = 0; i < times.length; i++) {
            long counter = 0;
            for (int t = 0; t < times[i]; t++) {
                long traveled = t*(times[i]-t);
                if (traveled > distances[i]) {
                    counter++;
                }
            }
            multi *= counter;
        }
        out.println(multi);

        // PART 2:

        long time = 52947594L;
        long distance = 426137412791216L;
        long counter = 0;
        for (int t = 0; t < time; t++) {
            long traveled = t * (time - t);
            if (traveled > distance) {
                counter++;
            }
        }
        out.println(counter);
        out.close();
        sc.close();
    }


}
