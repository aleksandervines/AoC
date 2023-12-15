import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Dec15P2 {

    public static void main(String[] args) {
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        Scanner sc = new Scanner(System.in);
        String inputLine = sc.nextLine();
        String[] inputArray = inputLine.split(",");
        Map<Integer, Map<String, Lens>> boxes = new HashMap<>();
        int count = 0;
        for (String input : inputArray) {
            if (input.charAt(input.length() - 1) == '-') {
                String id = input.substring(0, input.length() - 1);
                int hash = calculateHash(id);
                out.printf("%s:%d -%n", id, hash);
                Map<String, Lens> box = boxes.get(hash);
                if (box != null)
                    box.remove(id);
            } else {
                int indexOfEquals = input.indexOf('=');
                String id = input.substring(0, indexOfEquals);
                int hash = calculateHash(id);
                int fl = Integer.parseInt(input.substring(indexOfEquals + 1));
                out.printf("%s:%d=%d%n", id, hash, fl);
                Map<String, Lens> box;
                int index;
                if (boxes.containsKey(hash)) {
                    box = boxes.get(hash);
                    if (box.containsKey(id)) {
                        index = box.get(id).index;
                    } else {
                        index = count++;
                    }
                } else {
                    box = new HashMap<>();
                    boxes.put(hash, box);
                    index = count++;
                }
                box.put(id, new Lens(id, fl, index));
            }
            for (Integer i : boxes.keySet()) {
                out.printf("Box %d: ", i);
                List<Lens> box = new ArrayList<>(boxes.get(i).values());
                Collections.sort(box);
                for (Lens lens : box) {
                    out.printf("[%s %d]", lens.id, lens.fl);
                }
                out.println();
            }
            out.println();
        }
        long sum = 0;
        for (Integer i : boxes.keySet()) {
            List<Lens> box = new ArrayList<>(boxes.get(i).values());
            Collections.sort(box);
            int boxNumber = 1;
            for (Lens lens : box) {
                sum += (i + 1) * boxNumber++ * lens.fl;
            }
            out.println();
        }
        out.println(sum);

        sc.close();
        out.close();
    }

    static class Lens implements Comparable<Lens> {

        String id;
        int fl;
        int index;
        public Lens(String id, int fl, int index) {
            this.id = id;
            this.fl = fl;
            this.index = index;
        }

        @Override
        public int compareTo(Lens o) {
            return this.index - o.index;
        }
    }

    private static int calculateHash(String input) {
        int value = 0;
        for (int i = 0; i < input.length(); i++) {
            value += input.charAt(i);
            value = (value * 17) % 256;
        }
        return value;
    }
}
