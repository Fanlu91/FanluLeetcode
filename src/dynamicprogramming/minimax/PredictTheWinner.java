package dynamicprogramming.minimax;

// Source : https://leetcode.com/problems/predict-the-winner/
// Id     : 486
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-27
// Topic  : Minimax
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 5.45%

public class PredictTheWinner {
    // 78.07% 1ms 5.45%
    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for (int i : nums)
            sum += i;
        int[][] mem = new int[nums.length][nums.length];
        return sum <= findMax(0, nums.length - 1, nums, mem) * 2;
    }

    private int findMax(int l, int r, int[] nums, int[][] mem) {
        if (l < 0 || l > r)
            return 0;
        if (mem[l][r] != 0)
            return mem[l][r];

        if (l == r) {
            mem[l][r] = nums[l];
            return nums[l];
        }
        mem[l][r] = Math.max(nums[l] + Math.min(findMax(l + 1, r - 1, nums, mem), findMax(l + 2, r, nums, mem)),
                nums[r] + Math.min(findMax(l + 1, r - 1, nums, mem), findMax(l, r - 2, nums, mem)));
        return mem[l][r];
    }

    // 100.00% 0ms 5.45%
    public boolean PredictTheWinner2(int[] nums) {
//    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++)
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
        }
        return dp[0][nums.length - 1] >= 0;
    }

    // 100.00% 0ms 5.45%
    public boolean PredictTheWinner3(int[] nums) {
//    public boolean PredictTheWinner(int[] nums) {
        if (nums.length == 1 || nums.length % 2 == 0)
            return true;

        int[] dp = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = nums[i];
            for (int j = i + 1; j < nums.length; j++)
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
        }
        return dp[nums.length - 1] >= 0;
    }
}
