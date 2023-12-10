import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Dec10 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        List<char[]> mapArray = new ArrayList<>();
        int i = 0;
        int x = -1, y = -1;
        while (sc.hasNextLine()) {
            String lineString = sc.nextLine();
            int indexOfS = lineString.indexOf('S');
            if (indexOfS != -1) {
                y = i;
                x = indexOfS;
            }
            char[] line = lineString.toCharArray();
            mapArray.add(line);
            i++;
        }
        char[][] map = mapArray.toArray(char[][]::new);
        boolean[][] connected = new boolean[map.length][map[0].length];
        int[] c = findNext(new int[]{y, x}, map, connected);
        int count = 1;
        connected[y][x] = true;
        System.out.println("Start at " + x + "," + y);
        System.out.println("Next at " + c[1] + "," + c[0]);
        while ((c[0] != y || c[1] != x)) {
            connected[c[0]][c[1]] = true;
            c = findNext(c, map, connected);
            connected[y][x] = false;
            count++;
            System.out.println("Next at " + c[1] + "," + c[0]);
        }
        connected[y][x] = true;
        map[y][x] = figureOutS(x,y,connected,map);
        long countInside = 0;
        for (i = 0; i < map.length; i++) {
            boolean inside = false;
            char first = '.';
            for (int j = 0; j < map[0].length; j++) {
                if (connected[i][j]) {
                    if (map[i][j] != '-') {
                        if (!(map[i][j]=='J' && first == 'F' || map[i][j]=='7' && first == 'L'))
                            inside = !inside;
                    }
                    if (map[i][j] != '|' && first == '.') {
                        first = map[i][j];
                    }
                    if (map[i][j] == '|' || map[i][j] == '7' || map[i][j]=='J') {
                        first = '.';
                    }
                } else {
                    first = '.';
                    if (inside) {
                        countInside++;
                        map[i][j] = 'I';
                    } else {
                        map[i][j] = 'O';
                    }
                }
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        out.println(count);
        out.println(count / 2);
        out.println(countInside);
        out.close();
        sc.close();
    }

    private static char figureOutS(int x, int y, boolean[][] connected, char[][] map) {
        if (y > 0){
            if (connected[y-1][x]){
                // | L J
                if (y < map.length-1){
                    if (connected[y+1][x]){
                        return '|';
                    }
                }
                if (x > 0) {
                    if (connected[y][x-1]){
                        return 'J';
                    }
                }
                return 'L';
            }
        }
        if (y < map.length-1){
            if (connected[y+1][x]){
                // F 7
                if (x > 0) {
                    if (connected[y][x-1]){
                        return '7';
                    }
                }
                return 'F';
            }
        }
        return '-';
    }

    static int[] findNext(int[] c, char[][] map, boolean[][] connected) {
        int y = c[0];
        int x = c[1];
        char at = map[y][x];
        if (y > 0 && (at == '|' || at == 'L' || at == 'J' || at == 'S') && !connected[y - 1][x]) {
            char t = map[y - 1][x];
            System.out.println("t0:" + t);
            if (t == '|' || t == '7' || t == 'F' || t == 'S') {
                return new int[]{y - 1, x};
            }
        }
        if (y < map.length - 1 && (at == '|' || at == 'F' || at == '7' || at == 'S') && !connected[y + 1][x]) {
            char t = map[y + 1][x];
            System.out.println("t1:" + t);
            if (t == '|' || t == 'L' || t == 'J' || t == 'S') {
                return new int[]{y + 1, x};
            }
        }
        if (x > 0 && (at == '-' || at == '7' || at == 'J' || at == 'S') && !connected[y][x - 1]) {
            char t = map[y][x - 1];
            System.out.println("t2:" + t);
            if (t == '-' || t == 'F' || t == 'L' || t == 'S') {
                return new int[]{y, x - 1};
            }
        }
        return new int[]{y, x + 1};
    }
}
