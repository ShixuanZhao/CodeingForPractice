package A_MyPractice;

import java.util.Arrays;

public class Jump {
    public int jumps(int flagHeight, int bigJump) {
        int[] M = new int[flagHeight + 1];
        Arrays.fill(M, Integer.MAX_VALUE);
        M[0] = 0;
        for (int i = 1; i <= flagHeight; i++) {
            if (i >= bigJump) {
                M[i] = Math.min(M[i - 1] + 1, M[i - bigJump] + 1);
            } else {
                M[i] = M[i - 1] + 1;
            }
        }
        return M[flagHeight];
    }

    public static void main(String[] args) {
       Jump test = new Jump();
        System.out.println(test.jumps(8, 3));
    }
}
