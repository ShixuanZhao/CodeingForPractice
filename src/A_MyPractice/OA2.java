package A_MyPractice;

import java.util.HashMap;
import java.util.Map;

public class OA2 {
//    Count of subarrays having at least K unique elements
    //���Ե�ʱ��û�����������ǽ��ţ�һ��Ҫ����������õ��㷨��һ�����ӣ������������ϸ�ں���Ҫ�ı��������ݽṹ
    public int Kunique(int[] nums, int k) {
        int left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        //the number of unique elements
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) == 1) {
                cnt++;
            } else if (map.get(nums[i]) == 2) {
                cnt--;
            }
            while (cnt < k && i - left + 1 > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 1) {
                    cnt++;
                } else if (map.get(nums[left]) == 0) {
                    cnt--;
                }
                left++;
            }
            if (cnt >= k) {
                res++;
                System.out.println(left);
                System.out.println(i);
                System.out.println("*****");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        OA2 test = new OA2();
        int[] nums = new int[] {1, 2, 3, 2, 1};
        System.out.println(test.Kunique(nums, 3));
    }
}
