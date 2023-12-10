import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.RuntimeException;
import java.util.Map;
import java.util.HashMap;

public class Dec2P2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		Long counter = 0L;
		while (sc.hasNextLine()) {
			Map<String, Integer> minMap = new HashMap<String, Integer>(Map.of("red", 0, "green", 0, "blue", 0));
			String line = sc.nextLine();
			String[] gamesplit = line.split(":");
			int game = Integer.valueOf(gamesplit[0].trim().split(" ")[1]);
			String[] draws = gamesplit[1].split(";");
			for (int i = 0; i < draws.length;i++) {
				out.println(draws[i]);
				String[] colors = draws[i].split(",");
				for (int j = 0; j < colors.length;j++) {
					String[] numberColor = colors[j].trim().split(" ");
					String color = numberColor[1];
					minMap.put(color, Math.max(Integer.parseInt(numberColor[0]), minMap.get(color)));
				}
			}
			counter += minMap.get("red")*minMap.get("green")*minMap.get("blue");
		}
		out.println(counter);
		out.close();
		sc.close();
	}
}
