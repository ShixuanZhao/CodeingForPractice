package A_MyPractice;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class OA3 {
    //how many decreasing subsequence which size == 3
    public int maxInversions(List<Integer> arr) {
        if (arr == null || arr.size() < 3) {
            return 0;
        }
        int[] res = new int[1];
        List<Integer> sub = new LinkedList<>();
        Set<String> set = new HashSet<>();
        helper(arr, res, sub, 0, set);
        return res[0];
//        int res = 0;
//        List<Integer> cur = new LinkedList<>();
//        for (int i = 0; i <= arr.size() - 2; i++) {
//            if (i != 0 && arr.get(i).equals(arr.get(i - 1))) {
//                continue;
//            }
//            int cnt = 0;
//            for (int j = i + 1; j < arr.size(); j++) {
//                if (arr.get(j) < arr.get(i)) {
//                    cnt++;
//                }
//            }
//            cur.add(cnt);
//        }
//        for (int i = 0; i < cur.size() - 1; i++) {
//
//            for (int j = i + 1; j < cur.size(); j++) {
//                if (cur.get(j) < cur.get(i)) {
//                    res += cur.get(j);
//                }
//            }
//        }
//        return res;
    }

    private void helper(List<Integer> arr, int[] res, List<Integer> sub, int index, Set<String> set) {
        if (sub.size() == 3) {
            StringBuilder sb = new StringBuilder();
            for (int temp : sub) {
                sb.append(temp);
            }
            if (set.add(sb.toString())) {
                res[0]++;
            }

            return;
        }
        if (index == arr.size()) {
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            while (i != 0 && arr.get(i).equals(arr.get(i - 1))) {
                i++;
            }
            if (sub.isEmpty() || sub.get(sub.size() - 1) > arr.get(i)) {
                sub.add(arr.get(i));
                helper(arr, res, sub, i + 1, set);
                sub.remove(sub.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        OA3 test = new OA3();
        List<Integer> arr = new LinkedList<>();
        arr.add(5);
        arr.add(3);
        arr.add(3);
        arr.add(2);
        arr.add(3);
        arr.add(1);
        System.out.println(test.maxInversions(arr));
    }
}
