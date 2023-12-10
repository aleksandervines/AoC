import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Dec5P1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        List<Long> currentNumbers = Arrays.stream(sc.nextLine().split(":")[1].trim().split(" ")).map(s -> Long.parseLong(s)).collect(Collectors.toList());
        sc.nextLine();
        sc.nextLine();
        List<Long> nextNumbers = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            out.println(line);
            if (line.isBlank()) {
                sc.nextLine();
				nextNumbers.addAll(currentNumbers);
                currentNumbers = nextNumbers;
                nextNumbers = new ArrayList<>();
                continue;
            }
            String[] mapping = line.split(" ");
			Long sourceStart = Long.parseLong(mapping[1]);
			Long sourceRange = Long.parseLong(mapping[2]);
			Long difference = Long.parseLong(mapping[0]) - sourceStart;
            List<Long> remainingNumbers = new ArrayList<>();
            for (Long number : currentNumbers) {
                if (number >= sourceStart && number <= sourceStart + sourceRange) {
					out.println(number + "->" + (number+difference));
                    nextNumbers.add(number + difference);
                } else {
                    remainingNumbers.add(number);
                }
            }
            currentNumbers = remainingNumbers;
        }
		nextNumbers.addAll(currentNumbers);
        Collections.sort(nextNumbers);
        if (nextNumbers.size() > 0)
            out.println(nextNumbers.get(0));
        out.close();
        sc.close();
    }
}
