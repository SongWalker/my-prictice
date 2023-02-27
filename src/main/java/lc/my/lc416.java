package lc.my;

/*
给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

示例 1：

输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：

输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
* */
public class lc416 {
    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        boolean res = canPartition(nums);
    }

    /**
     * 一个整型数组是否能划分为两个子数组，使得两个数组和相等
     * 思路：转换为背包问题
     * @param nums 输入一个整型数组
     * @return 输出true/false
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int max = sum / 2;
        int[][] dp = new int[nums.length + 1][max + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= max; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= num) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - num] + num);
                }
            }
            //提前结束
            if(dp[i][max] == max) {
                return true;
            }
        }

        return false;
    }
}
