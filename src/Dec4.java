import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.RuntimeException;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Dec4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		int counter = 0;
		List<char[]> lines = new ArrayList();
		int[] multiplicator = {1,1,1,1,1,1,1,1,1,1};
		int index = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			int copies = multiplicator[index];
			multiplicator[index] = 1;
			counter += copies;
			index = (index+1)%multiplicator.length;
			String numbers = line.split(":")[1].trim();
			out.println("numbers:"+numbers);
			String[] numbersSplit = numbers.split("\\|");
			out.println("numbersSplit-0:"+numbersSplit[0]);
			out.println("numbersSplit-1:"+numbersSplit[1]);
			Set<String> winningNumbers = new HashSet(Arrays.asList(numbersSplit[0].trim().split(" +")));
			int winners = 0;
			for (String s: numbersSplit[1].trim().split(" +")){
				if (winningNumbers.contains(s)) {
					winners++;
				}
			}
			for (int i = 0; i < winners; i++){
				multiplicator[(i+index)%multiplicator.length] += copies;
			}
		}
		out.println("Counter:"+counter);
		out.close();
		sc.close();
	}
}
