import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Dec5P2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        List<Long> currentNumbers = Arrays.stream(sc.nextLine().split(":")[1].trim().split(" ")).map(Long::parseLong).collect(Collectors.toList());
        sc.nextLine();
        sc.nextLine();
        List<Long> nextNumbers = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isBlank()) {
                sc.nextLine();
                currentNumbers.addAll(nextNumbers);
                nextNumbers = new ArrayList<>();
                line = sc.nextLine();
            }
            String[] mapping = line.split(" ");
            Long sourceStart = Long.parseLong(mapping[1]);
            Long sourceRange = Long.parseLong(mapping[2]);
            long sourceEnd = sourceStart + sourceRange - 1;
            Long difference = Long.parseLong(mapping[0]) - sourceStart;
            List<Long> remainingNumbers = new ArrayList<>();
            for (int i = 0; i < currentNumbers.size(); i += 2) {
                Long start = currentNumbers.get(i);
                Long range = currentNumbers.get(i + 1);
                long end = start + range - 1;
                if (start < sourceStart && end > sourceEnd) {
                    // Den går utenfor source både før og etter
                    remainingNumbers.add(start);
                    long rangeRemaningBefore = sourceStart - start;
                    remainingNumbers.add(rangeRemaningBefore);
                    nextNumbers.add(sourceStart + difference);
                    nextNumbers.add(sourceRange);
                    remainingNumbers.add(sourceEnd + 1);
                    remainingNumbers.add(range - sourceRange - rangeRemaningBefore);
                } else if (end >= sourceStart && start <= sourceEnd) {
                    if (start >= sourceStart) {
                        if (end > sourceEnd) {
                            // Den går utenfor source etter
                            nextNumbers.add(start + difference);
                            long rangeNext = sourceEnd - start + 1;
                            nextNumbers.add(rangeNext);
                            remainingNumbers.add(sourceEnd + 1);
                            remainingNumbers.add(range - rangeNext);
                        } else {
                            // Hele er innenfor
                            nextNumbers.add(start + difference);
                            nextNumbers.add(range);
                        }
                    } else {
                        // Den går utenfor source før
                        remainingNumbers.add(start);
                        long rangeRemaning = sourceStart - start;
                        remainingNumbers.add(rangeRemaning);
                        nextNumbers.add(sourceStart + difference);
                        nextNumbers.add(range - rangeRemaning);
                    }
                } else {
                    remainingNumbers.add(start);
                    remainingNumbers.add(range);
                }
            }
            currentNumbers = remainingNumbers;
        }
        nextNumbers.addAll(currentNumbers);
        Long min = Long.MAX_VALUE;
        long antall = 0;
        for (int i = 0; i < nextNumbers.size(); i += 2) {
            out.println("Option: " + nextNumbers.get(i));
            if (nextNumbers.get(i) < min) {
                min = nextNumbers.get(i);
            }
            antall += nextNumbers.get(i + 1);
        }
        out.println(nextNumbers.size());
        out.println(antall);
        out.println(min);
        out.close();
        sc.close();
    }
}
