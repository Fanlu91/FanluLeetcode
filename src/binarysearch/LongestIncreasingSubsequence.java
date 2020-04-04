package binarysearch;

// Source : https://leetcode.com/problems/longest-increasing-subsequence/
// Id     : 300
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search ,Dynamic Programming
// Level  : Medium
// Other  :
// Tips   : The difference between >> and >>> would only show up when shifting negative numbers. The >> operator shifts a 1 bit into the most significant bit if it was a 1, and the >>> shifts in a 0 regardless.
// Result : 94.63% 5.63%

public class LongestIncreasingSubsequence {

    /**
     * dp method
     * dp[] stores LIS length till i
     *
     * @param nums
     * @return
     */
    // 58.44% 14 ms 5.07%
    public int lengthOfLIS(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    /**
     * learned from leetcode submission
     * using dp[] to store the actual LIS
     * ans tracks its length
     * for each num in nums, do binary search in current LIS
     *
     * @param nums
     * @return
     */
    // 94.63% 1ms 5.63%
    public int lengthOfLIS2(int[] nums) {
//    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int ans = 0;
        for (int num : nums) {
            int i = 0, j = ans;
            while (i < j) {
                int mid = (i + j) >>> 1; // unsigned right bit-shift
                if (dp[mid] >= num)
                    j = mid;
                else
                    i = mid + 1;
            }
            dp[i] = num;
            if (j == ans) // means i = mid = ans
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        l.lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
    }
}
