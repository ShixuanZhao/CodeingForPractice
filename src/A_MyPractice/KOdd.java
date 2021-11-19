package A_MyPractice;

import java.util.HashSet;

public class KOdd {
    public int kOdd(int[] nums, int k) {
        int n = nums.length;
        //the number of odd element
        int cnt = 0;
        int res = 0;
        HashSet<String> unique = new HashSet<>();
        int start = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                cnt++;
            }
            while (cnt > k) {
                if ((nums[start] & 1) == 1) {
                    cnt--;
                }
                start++;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = start; j <= i; j++) {
                sb.append(nums[j]);
                //each subarray is valid
                unique.add(sb.toString());
            }
            System.out.println(sb.toString());
        }
        return unique.size();
    }

    public static void main(String[] args) {
        KOdd test = new KOdd();
        int[] nums = new int[] {3, 2, 3, 2};
        System.out.println(test.kOdd(nums, 1));
    }
}
