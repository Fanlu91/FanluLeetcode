package greedy;

// Source : https://leetcode.com/problems/maximum-subarray/
// Id     : 53
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Greedy, DP
// Level  : Easy
// Other  :
// Tips   :
// Links  : Must
// Result : 100.00% 99.53%

public class MaximumSubarray {
    // 85.40% 1ms 5.16%
    public int maxSubArray(int[] nums) {
        int tmp = 0, res = Integer.MIN_VALUE;
        for (int n : nums) {
            if (tmp + n >= 0) {
                tmp += n;
                res = Math.max(tmp, res);
            } else
                tmp = 0;
        }

        if (res == Integer.MIN_VALUE) {
            for (int i = 0; i < nums.length; i++) {
                res = Math.max(res, nums[i]);
            }
        }

        return res;
    }

    // 100.00% 0ms 99.53%
    public int maxSubArrayImprove(int[] nums) {
//    public int maxSubArray(int[] nums) {
        int sum = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > res) {
                res = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }
        return res;
    }

    // 93.81% 1ms
    public int maxSubArrayDP(int[] nums) {
//    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int res = nums[0];
        int[] dp = new int[nums.length + 1];
        dp[0] = Math.max(0, nums[0]);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 93.81% 1ms
    public int maxSubArrayDP1(int[] nums) {
//    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];

        int res = nums[0];
        int a = Math.max(0, nums[0]), b;

        for (int i = 1; i < nums.length; i++) {
            b = Math.max(a + nums[i], nums[i]);
            res = Math.max(res, b);
            a = b;
        }
        return res;
    }

}
