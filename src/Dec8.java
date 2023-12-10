import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Dec8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        String instructionLine = sc.nextLine();
        int[] instructions = new int[instructionLine.length()];
        int i = 0;
        for (char c: instructionLine.toCharArray()){
            if (c == 'L')
                instructions[i++] = 0;
            else
                instructions[i++] = 1;
        }
        sc.nextLine();
        Map<String, Node> nodes = new HashMap<>();
        List<Node> startNodes = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String name = line.substring(0,3);
            Node node = new Node(name);
            if (nodes.containsKey(name)){
                node = nodes.get(name);
            } else {
                nodes.put(name, node);
            }
            node.links[0] = line.substring(7,10);
            node.links[1] = line.substring(12,15);
            if (node.startNode)
                startNodes.add(node);
        }
        int ii = 0;
        int counter = 1;
        Node[] currentNodes = startNodes.toArray(new Node[0]);
        out.println("Startnodes:"+startNodes.size());
        for (int j = 0; j < startNodes.size(); j++){
            out.println(j+":" + startNodes.get(j).name);
        }
        out.println();
        int nDone = 0;
        boolean[] found = new boolean[currentNodes.length];
        while(nDone < currentNodes.length){
            // out.println(String.format("ii:%d currentNode:%s:%s|%s", ii, currentNode.name, currentNode.links[0], currentNode.links[1]));
            for (int j = 0; j < currentNodes.length; j++) {
                if (!found[j]) {
                    currentNodes[j] = nodes.get(currentNodes[j].links[instructions[ii]]);
                    if (currentNodes[j].endNode) {
                        startNodes.get(j).foundAt = counter;
                        nDone++;
                    }
                }
            }

            ii = (ii+1)%i;
            counter++;
        }
        long commonLcm = 1L;
        for (int j = 0; j < startNodes.size(); j++){
            out.println(j+":" + startNodes.get(j).foundAt);
            commonLcm = lcm(commonLcm, startNodes.get(j).foundAt);
        }
        out.println(counter);
        out.println(commonLcm);
        out.close();
        sc.close();
    }

    static long gcd(long a, long b){
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static long lcm(long a, long b){
        return a*b/gcd(a,b);
    }

    static class Node {
        String name;
        String[] links;
        boolean startNode = false;
        boolean endNode = false;
        int foundAt = -1;
        Node(String name) {
            this.name=name;
            switch (name.charAt(2)) {
                case 'A':
                    startNode = true;
                    break;
                case 'Z':
                    endNode = true;
            }
            links = new String[2];
        }
    }
}
