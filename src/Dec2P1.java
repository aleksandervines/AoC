import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.RuntimeException;
import java.util.Map;

public class Dec2P1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		Long counter = 0L;
		Map<String, Integer> maxMap = Map.of("red", 12, "green", 13, "blue", 14);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] gamesplit = line.split(":");
			int game = Integer.valueOf(gamesplit[0].trim().split(" ")[1]);
			String[] draws = gamesplit[1].split(";");
			boolean valid = true;
			for (int i = 0; i < draws.length;i++) {
				out.println(draws[i]);
				String[] colors = draws[i].split(",");
				for (int j = 0; j < colors.length;j++) {
					String[] numberColor = colors[j].trim().split(" ");
					if (maxMap.get(numberColor[1]) < Integer.valueOf(numberColor[0])){
						valid = false;
					}
				}
				out.println(valid);
			}
			if (valid){
				counter += game;
			}
		}
		out.println(counter);
		out.close();
		sc.close();
	}
}
