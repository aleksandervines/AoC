import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


public class Dec7P1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OutputStream outputStream = System.out;
        PrintWriter out = new PrintWriter(outputStream);
        List<Hand> hands = new ArrayList<>();
        while(sc.hasNext()) {
            hands.add(new Hand(sc.next(), sc.nextInt()));
        }
        Collections.sort(hands);
        long result = 0;
        for (int i = 0; i < hands.size(); i++) {
            result += (i+1) * hands.get(i).bid;
        }
        out.println(result);
        out.close();
        sc.close();
    }

    static class Hand implements Comparable<Hand> {
        String cards;
        int typeRank;
        int bid;
        Hand(String cards, int bid) {
            this.cards = cards;
            this.bid = bid;
            calculateTypeRank();
        }

        private void calculateTypeRank() {
            Map<Character, Integer> map = new HashMap<>();
            for (Character c: cards.toCharArray()) {
                if (map.containsKey(c)) {
                    map.put(c, map.get(c)+1);
                } else {
                    map.put(c, 1);
                }
            }
            if (map.size() == 1){
                // Five of a kind
                typeRank = 6;
            } else if (map.size() == 2) {
                if (map.containsValue(1)){
                    // Four of a kind
                    typeRank = 5;
                } else {
                    // Full house
                    typeRank = 4;
                }
            } else if (map.size() == 3) {
                if (map.containsValue(3)){
                    // Three of a kind
                    typeRank = 3;
                } else {
                    // Two pair
                    typeRank = 2;
                }
            } else if (map.size() == 4) {
                // One pair
                typeRank = 1;
            } else {
                // High card
                typeRank = 0;
            }
        }

        @Override
        public int compareTo(Hand o) {
            if (this.typeRank != o.typeRank)
                return Integer.compare(this.typeRank, o.typeRank);
            for (int i = 0; i < cards.length(); i++){
                if (getValueOfCard(this.cards.charAt(i)) != getValueOfCard(o.cards.charAt(i))){
                    return Integer.compare(getValueOfCard(this.cards.charAt(i)), getValueOfCard(o.cards.charAt(i)));
                }
            }
            return 0;
        }

        public static int getValueOfCard(char c) {
            switch (c) {
                case 'A':
                    return 14;
                case 'K':
                    return 13;
                case 'Q':
                    return 12;
                case 'J':
                    return 11;
                case 'T':
                    return 10;
                default:
                    return c-'0';
            }
        }
    }
}
