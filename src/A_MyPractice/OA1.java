package A_MyPractice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OA1 {
    public String reconstructCipher(List<Character> startChars, int piecesLeft) {
        List<Character> res= new ArrayList<>();
        for (int i = 1; i <= 5; i += 2) {
            res.add(startChars.get(i));
        }
        int sum = 0;
        for (int i = 0; i <= 2; i++) {
            sum += res.get(i) - 'a' + 1;
        }
        sum = sum % 27;
//        System.out.println(sum);
        char c = ' ';
        if (sum == 0) {
            c = ' ';
        } else {
            c = (char) (sum + 'a' - 1);
        }
//        System.out.println(c);
        res.add(c);
        for (int i = 3; i <= piecesLeft - 1; i++) {
            sum = sum + res.get(i) - 'a' + 1 - (res.get(i - 3) - 'a' + 1);
            sum = sum % 27;
            char c1 = ' ';
            if (sum == 0) {
                c1 = ' ';
            } else {
                c1 = (char) (sum + 'a' - 1);
            }
            res.add(c1);
//            System.out.println(c1);
        }
        StringBuilder sb = new StringBuilder();
        for (char ch : res) {
            sb.append((ch));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        OA1 test = new OA1();
        List<Character> startChars = new LinkedList<>();
        startChars.add('[');
        startChars.add('a');
        startChars.add(',');
        startChars.add('b');
        startChars.add(',');
        startChars.add('c');
        startChars.add(']');
        System.out.println(test.reconstructCipher(startChars, 10));
    }
}
