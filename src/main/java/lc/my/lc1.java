package lc.my;

import java.util.HashMap;
import java.util.Map;

public class lc1 {
    public static void main(String[] args) {
        int[] nums = new int[] {3,3};
        int target = 6;
        int[] ret = twoSum(nums, target);
        assert ret != null;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (hashtable.containsKey(x)) {
                return new int[]{hashtable.get(x), i};
            }
            hashtable.put(nums[i], i);
        }
        return null;
    }
}
