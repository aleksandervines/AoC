import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.RuntimeException;
import java.util.List;
import java.util.ArrayList;

public class Dec3P2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OutputStream outputStream = System.out;
		PrintWriter out = new PrintWriter(outputStream);
		Long counter = 0L;
		List<char[]> lines = new ArrayList(); 
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine().toCharArray());
		}
		for (int i=0; i < lines.size(); i++) {
			char[] line = lines.get(i);
			for (int j=0; j < line.length; j++) {
				char c = line[j];
				if (c == '*'){
					out.println(String.format("The character %s is at %d,%d", c, i, j));
					int[] gears = new int[3];
					if (i > 0) {
						if (j > 0) {
							addGear(gears, processNumber(i-1, j-1, lines));
						}
						addGear(gears, processNumber(i-1, j, lines));
						if (j < line.length-1) {
							addGear(gears, processNumber(i-1, j+1, lines));
						}
					}
					if (j > 0) {
						addGear(gears, processNumber(i, j-1, lines));
					}
					if (j < line.length-1) {
						addGear(gears, processNumber(i, j+1, lines));
					}
					if (i < lines.size()-1) {
						if (j > 0) {
							addGear(gears, processNumber(i+1, j-1, lines));
						}
						addGear(gears, processNumber(i+1, j, lines));
						if (j < line.length-1) {
							addGear(gears, processNumber(i+1, j+1, lines));
						}
					}
					if (gears[1] != 0 && gears[2] == 0){
						counter += gears[0]*gears[1];
					}
				}
			}
		}
		out.println("Counter:"+counter);
		out.close();
		sc.close();
	}

	public static void addGear(int[] gears, int gear) {
		if (gear == 0) {
			return;
		}
		System.out.println("Adding gear:"+gear);
		if (gears[0] == 0) {
			gears[0] = gear;
		} else if (gears[1] == 0) {
			gears[1] = gear;
		} else {
			gears[2] = gear;
		}
	}

	public static int processNumber(int i, int j, List<char[]> lines) {
		char[] line = lines.get(i);
		if (Character.isDigit(line[j])) {
			int next = findFirstDigitInNumber(line, j);
			String number = "";
			while(next < line.length && Character.isDigit(line[next])){
				number+=line[next];
				line[next] = '.';
				next++;
			}
			return Integer.parseInt(number);
		}
		return 0;
	}

	public static int findFirstDigitInNumber(char[] line, int j) {
		if (j == 0) {
			return 0;
		}
		if (Character.isDigit(line[j-1])) {
			return findFirstDigitInNumber(line, j-1);
		}
		return j;
	}
}
