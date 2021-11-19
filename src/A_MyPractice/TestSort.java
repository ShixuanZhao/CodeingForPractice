package A_MyPractice;

import java.util.Arrays;

public class TestSort {
    public String[] sort(String[] input) {
//        Arrays.sort(input, (a, b) -> (a.compareTo(b)));
        Arrays.sort(input);
        return input;
    }

    public static void main(String[] args) {
        TestSort test = new TestSort();
        String[] s = new String[] {"January","February","March","April","May","June",
                "July","August","September","October","December"};
        System.out.println(Arrays.toString(test.sort(s)));
    }
}
