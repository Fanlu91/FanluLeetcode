package dynamicprogramming;

// Source : https://leetcode.com/problems/house-robber-ii/
// Id     : 213
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 100.00%
public class HouseRobberII {

    /**
     * 参考 https://leetcode-cn.com/problems/house-robber-ii/solution/tong-yong-si-lu-tuan-mie-da-jia-jie-she-wen-ti-by-/
     *
     * 虽然命名一般用驼峰法，不过 dp_i、dp_i_1、dp_i_2 的写法感觉很清晰
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums, 0, n - 2),
                robRange(nums, 1, n - 1));
    }

    // 仅计算闭区间 [start,end] 的最优结果
    int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        // 记录 dp[i+1] 和 dp[i+2]
        int dp_i_1 = 0, dp_i_2 = 0;
        // 记录 dp[i]
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

}
