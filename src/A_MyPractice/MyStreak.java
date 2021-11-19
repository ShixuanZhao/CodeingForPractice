package A_MyPractice;

import java.util.List;

public class MyStreak {
    public int findMyStreak(List<Integer> steps) {
        int left = 0;
        //the number of element that larger than 10000
        int cnt = 0;
        int res = 0;
        for (int i = 0; i < steps.size(); i++) {
            if (steps.get(i) > 10000) {
                cnt++;
            }
            while (i - left + 1 - cnt >= cnt) {
                if (steps.get(left) > 10000) {
                    cnt--;
                }
                left++;
            }
            res = Math.max(res, i - left + 1);
        }
        return res;
    }
}
