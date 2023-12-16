import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Dec16 {

    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        Scanner sc = new Scanner(System.in);
        List<String> layout = new ArrayList<>();
        while (sc.hasNextLine()) {
            layout.add(sc.nextLine());
        }
        int sum = 0;
        int nColumns = layout.get(0).length() - 1;
        int nRows = layout.size() - 1;
        for (int y = 0; y <= nRows; y++) {
            int sum1 = getSum(y, 0, 3, layout);
            if (sum1 > sum) {
                out.printf("NEW MAX FROM:%d,%d,%d=%d%n", y, 0, 3, sum1);
                sum = sum1;
            }
            sum1 = getSum(y, nColumns, 1, layout);
            if (sum1 > sum) {
                out.printf("NEW MAX FROM:%d,%d,%d=%d%n", y, nColumns, 1, sum1);
                sum = sum1;
            }
        }
        for (int x = 0; x <= nColumns; x++) {
            int sum1 = getSum(0, x, 2, layout);
            if (sum1 > sum) {
                out.printf("NEW MAX FROM:%d,%d,%d=%d%n", 0, x, 2, sum1);
                sum = sum1;
            }
            sum1 = getSum(nRows, x, 0, layout);
            if (sum1 > sum) {
                out.printf("NEW MAX FROM:%d,%d,%d=%d%n", nRows, x, 0, sum1);
                sum = sum1;
            }
        }
        out.println(sum);
        sc.close();
        out.close();
    }

    private static int getSum(int y, int x, int di, List<String> layout) {
        // N, W, S, E, Any
        boolean[][][] visited = new boolean[layout.size()][layout.get(0).length()][5];
        traverse(y, x, di, layout, visited);
        int sum = 0;
        for (boolean[][] row : visited) {
            for (boolean[] visitLog : row) {
                if (visitLog[4])
                    sum++;
            }
        }
        return sum;
    }

    private static void traverse(int y, int x, int di, List<String> layout, boolean[][][] visited) {
        //System.out.printf("Processing: y=%d, x=%d, di=%d",y,x,di);
        if (visited[y][x][di])
            return;
        visited[y][x][di] = true;
        visited[y][x][4] = true;
        int[] directionsNext;
        switch (layout.get(y).charAt(x)) {
            case '-':
                directionsNext = switch (di) {
                    case 0 -> new int[]{1, 3};
                    case 1 -> new int[]{1};
                    case 2 -> new int[]{1, 3};
                    case 3 -> new int[]{3};
                    default -> throw new RuntimeException("NOOPE");
                };
                break;
            case '|':
                directionsNext = switch (di) {
                    case 0 -> new int[]{0};
                    case 1 -> new int[]{0, 2};
                    case 2 -> new int[]{2};
                    case 3 -> new int[]{0, 2};
                    default -> throw new RuntimeException("NOOPE1");
                };
                break;
            case '/':
                directionsNext = switch (di) {
                    case 0 -> new int[]{3};
                    case 1 -> new int[]{2};
                    case 2 -> new int[]{1};
                    case 3 -> new int[]{0};
                    default -> throw new RuntimeException("NOOPE2");
                };
                break;
            case '\\':
                directionsNext = switch (di) {
                    case 0 -> new int[]{1};
                    case 1 -> new int[]{0};
                    case 2 -> new int[]{3};
                    case 3 -> new int[]{2};
                    default -> throw new RuntimeException("NOOPE3");
                };
                break;
            case '.':
                if (di >= 0 && di <= 3)
                    directionsNext = new int[]{di};
                else
                    throw new RuntimeException("NOOPE4");
                break;
            default:
                throw new RuntimeException("NOOPE4");

        }
        int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int direction : directionsNext) {
            int y1 = y + directions[direction][0];
            if (y1 < 0 || y1 >= layout.size())
                continue;
            int x1 = x + directions[direction][1];
            if (x1 < 0 || x1 >= layout.get(0).length())
                continue;
            traverse(y1, x1, direction, layout, visited);
        }
    }
}
