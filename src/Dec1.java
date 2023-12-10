import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.RuntimeException;

public class Dec1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		Long counter = 0L;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String digit = "one|two|three|four|five|six|seven|eight|nine|\\d";
		  Pattern patternFirst = Pattern.compile(String.format("[^%1$s]*(%1$s).*",digit));
		  Pattern patternLast = Pattern.compile(String.format(".*(%1$s)[^%1$s]*",digit));
			Matcher matcherFirst = patternFirst.matcher(line);
			Matcher matcherLast = patternLast.matcher(line);
			if (matcherFirst.find()) {
				String first = matcherFirst.group(1);
				counter += convert(first)*10;
				String last = first;
				if (matcherLast.find()) {
					last = matcherLast.group(1);
				}
				counter += convert(last);
				out.println(line);
				out.println(first);
				out.println(last);
			} else {
				throw new RuntimeException("No match found for " + line);
			}
		}
		out.println(counter);
		out.close();
		sc.close();
	}

	public static int convert(String input) {
		switch (input) {
			case "one":
			case "1":
				return 1;	
			case "two":
			case "2":
				return 2;	
			case "three":
			case "3":
				return 3;	
			case "four":
			case "4":
				return 4;	
			case "five":
			case "5":
				return 5;	
			case "six":
			case "6":
				return 6;	
			case "seven":
			case "7":
				return 7;	
			case "eight":
			case "8":
				return 8;	
			case "nine":
			case "9":
				return 9;		
			default:
				throw new RuntimeException("Invalid input: " + input);
		}
	}
}
