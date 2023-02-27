package lc.my;

/*
给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
* */
public class lc209 {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4,5};
        System.out.println(minSubArrayLen(11, nums));
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int minLen = 0;
        int i = 0,j = 0;
        int sum = nums[0];
        while (i<nums.length && j<nums.length) {
            if(i == j) {
                if(sum >= target) {
                    minLen = 1;
                    break;
                }
                else if(sum < target && ++j < nums.length) {
                    sum += nums[++j];
                }
                continue;
            }
            if(sum >= target) {
                int len = j - i + 1;
                minLen = (minLen > len | minLen == 0) ? len : minLen;
                if(sum > target) {
                    sum -= nums[i++];
                }
                else if(sum == target) {
                    if(j == nums.length - 1) {
                        break;
                    }
                    sum -= nums[i++];
                    sum += nums[++j];
                }
            }
            else if(sum < target && ++j < nums.length) {
                sum += nums[j];
            }

        }

        return minLen;
    }
}
