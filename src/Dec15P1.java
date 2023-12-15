import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class Dec15P1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        String input = sc.nextLine();
        long sum = 0;
        int value = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i)==','){
                out.println(value);
                sum +=value;
                value = 0;
            } else {
                value += input.charAt(i);
                value = (value*17)%256;
            }
        }
        out.println(value);
        sum +=value;
        out.println(sum);
        out.close();
        sc.close();
    }
}
