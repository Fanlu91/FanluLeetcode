package dynamicprogramming;

// Source : https://leetcode.com/problems/house-robber/
// Id     : 198
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100.00%
public class HouseRobber {
    // 100.00% 0ms 100.00%
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length + 1];

        dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[nums.length];
    }
}
