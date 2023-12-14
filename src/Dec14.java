import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Dec14 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        int cycles = 1000000000;
        long sum = 0;
        List<String> mapList = new ArrayList<>();
        while (sc.hasNextLine()) {
            mapList.add(sc.nextLine());
        }
        char[][] map = mapList.stream().map(String::toCharArray).toArray(char[][]::new);
        //int[][] directions = {{-1,-0}};
        int[][] directions = {{-1,-0, -1},{0,-1, -1},{1,0, 1},{0,1, 1}};
        Map<String, long[]> memory = new HashMap<>();
        List<String> memoryList = new ArrayList<>();
        StringBuilder key = null;
        int x = 0;
        for (; x < cycles; x++) {
            out.println("x:"+x);
            key = new StringBuilder();
            for (char[] chars : map) {
                key.append(chars);
            }
            if (memory.containsKey(key.toString())){
                break;
            }
            int value = 0;
            for (int[] direction : directions) {
                boolean[][] occupied = new boolean[map.length][map[0].length];
                char[][] newMap = new char[map.length][map[0].length];
                int countRocks = 0;
                int iStart = 0;
                int jStart = 0;
                if (direction[2] > 0){
                    iStart = map.length-1;
                    jStart = map[0].length-1;
                }
                for (int i = iStart; i < map.length && i >= 0; i-= direction[2]) {
                    for (int j = jStart; j < map[i].length && j >= 0; j-= direction[2]) {
                        newMap[i][j] = '.';
                        if (map[i][j] == '#') {
                            occupied[i][j] = true;
                            newMap[i][j] = '#';
                        } else if (map[i][j] == 'O') {
                            countRocks++;
                            int k = i;
                            int l = j;
                            int k1;
                            int l1;
                            while (true) {
                                k1 = k + direction[0];
                                l1 = l + direction[1];
                                //out.printf("Testing:%d,%d->%d,%d%n", k, l, k1, l1);
                                if (k1 < 0 || l1 < 0 || k1 >= map.length || l1 >= map[0].length) {
                                    break;
                                }
                                if (occupied[k1][l1]) {
                                    break;
                                }
                                k = k1;
                                l = l1;
                            }
                            occupied[k][l] = true;
                            newMap[k][l] = 'O';
                            //out.printf("%d,%d->%d,%d - %d%n", i, j, k, j, map.length - k);
                            sum += map.length - k; // TODO: flytt summeringen
                        }
                    }
                }
                map = newMap;
                value = 0;
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        out.print(map[i][j]);
                        if (map[i][j] == 'O')
                            value+=map.length-i;
                    }
                    out.println();
                }
                out.println(countRocks);
            }
            memory.put(key.toString(), new long[]{x,value});
            memoryList.add(key.toString());
        }
        long[] memoryvalue = memory.get(key.toString());
        out.println(memoryvalue[0]);
        out.println(memoryvalue[1]);
        out.printf("final x:%d%n",x);
        long loopLength = x - memoryvalue[0];
        long lengthAfterLoopsStarted = cycles - memoryvalue[0];
        long modulo = lengthAfterLoopsStarted % loopLength;
        out.println(modulo);
        int index = (int)(memoryvalue[0] + modulo)-1;
        out.println(index);
        out.println(memory.get(memoryList.get(index))[1]);
        out.close();
        sc.close();
    }
}
