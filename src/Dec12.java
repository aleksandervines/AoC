import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Dec12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        long sum = 0;
        int line = 0;
        int multiplier = 5;
        while (sc.hasNextLine()) {
            String singleSprings = sc.next();
            StringBuilder springs = new StringBuilder(singleSprings);
            for (int j = 1; j < multiplier; j++) {
                springs.append("?").append(singleSprings);
            }
            System.out.println("Testing: " + springs);
            int[] singleGroups = Arrays.stream(sc.nextLine().trim().split(",")).mapToInt(Integer::parseInt).toArray();
            int[] groups = new int[singleGroups.length * multiplier];
            for (int i = 0; i < singleGroups.length; i++) {
                for (int j = 0; j < multiplier; j++) {
                    groups[i + singleGroups.length * j] = singleGroups[i];
                }
            }
            int[] requiredForGroups = new int[groups.length];
            int counter = -1;
            for (int i = groups.length - 1; i >= 0; i--) {
                counter += groups[i] + 1;
                requiredForGroups[i] = counter;
            }
            long sum1 = calculatePermutations(0, springs.toString(), 0, groups, requiredForGroups);
            sum += sum1;
            line++;
            System.out.printf("Sum for line %d: %d%n", line, sum1);
        }
        out.println(sum);
        out.close();
        sc.close();
    }

    static Map<String, Long> memory = new HashMap<>();

    private static String calculateMemoryKey(int start, String springs, int nextGroup, int[] groups) {
        if (start >= springs.length() || nextGroup >= groups.length)
            return null;
        StringBuilder key = new StringBuilder(springs.substring(start));
        for (int i = nextGroup; i < groups.length; i++) {
            key.append(groups[i]);
        }
        return key.toString();
    }

    private static long calculatePermutations(int start, String springs, int nextGroup, int[] groups, int[] requiredForGroups) {
        //System.out.println(start + " - " + nextGroup);
        String key = calculateMemoryKey(start, springs, nextGroup, groups);
        if (key != null && memory.containsKey(key)) {
            return memory.get(key);
        }
        long returnValue = -1;
        if (nextGroup >= groups.length) {
            returnValue = 1;
            for (int i = start - 1; i < springs.length() && start > 0; i++) {
                if (springs.charAt(i) == '#') {
                    returnValue = 0;
                    break;
                }
            }
        } else if (springs.length() - start < requiredForGroups[nextGroup]) {
            //  System.out.println("-2");
            returnValue = 0;
        } else if (start >= springs.length()) {
            //   System.out.println("-1");
            returnValue = 0;
        } else {
            // System.out.print(springs.substring(start) + " ");
            //for (int i = nextGroup; i < groups.length; i++){
            // System.out.print(groups[i] + ",");
            //}
            //System.out.println();
            char c = springs.charAt(start);
            switch (c) {
                case '?':
                    for (int j = 1; j < groups[nextGroup]; j++) {
                        if (springs.charAt(start + j) == '.') {
                            //  System.out.println("0 - NO SPACE - ? must be .");
                            returnValue = calculatePermutations(start + 1, springs, nextGroup, groups, requiredForGroups);
                            break;
                        }
                    }
                    if (returnValue != -1)
                        break;
                    if (springs.length() - start < groups[nextGroup] || nextGroup < groups.length - 1 && springs.charAt(start + groups[nextGroup]) == '#') {
                        //System.out.println("1 - NO SPACE - ? must be .");
                        returnValue = calculatePermutations(start + 1, springs, nextGroup, groups, requiredForGroups);
                        break;
                    }
                    //System.out.println("1 - setting ? as #");
                    long p1 = calculatePermutations(start + groups[nextGroup] + 1, springs, nextGroup + 1, groups, requiredForGroups);
                    // System.out.println("1 - setting ? as .");
                    long p2 = calculatePermutations(start + 1, springs, nextGroup, groups, requiredForGroups);
                    returnValue = p1 + p2;
                    break;
                case '.':
                    // System.out.println("2");
                    returnValue = calculatePermutations(start + 1, springs, nextGroup, groups, requiredForGroups);
                    break;
                case '#':
                    if (springs.length() - start < groups[nextGroup] || nextGroup < groups.length - 1 && springs.charAt(start + groups[nextGroup]) == '#') {
                        //  System.out.println("3");
                        returnValue = 0;
                        break;
                    }
                    for (int j = 1; j < groups[nextGroup]; j++) {
                        if (springs.charAt(start + j) == '.') {
                            //  System.out.println("4");
                            returnValue = 0;
                            break;
                        }
                    }
                    if (returnValue != -1)
                        break;
                    //System.out.println("5");
                    returnValue = calculatePermutations(start + groups[nextGroup] + 1, springs, nextGroup + 1, groups, requiredForGroups);
                    break;
            }
        }
        //System.out.println("NOOOPE");
        if (key != null)
            memory.put(key, returnValue);
        return returnValue;
    }
}
