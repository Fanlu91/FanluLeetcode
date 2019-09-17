package dynamicprogramming;
// Source : https://leetcode.com/problems/range-sum-query-immutable/
// Id     : 303
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-17
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Result : 95.71% 56.10%

public class RangeSumQueryImmutable {
    // 95.71% 50 ms 56.10%
    class NumArray {
        int dp[];

        public NumArray(int[] nums) {
            dp = new int[nums.length + 1];
            for (int i = 1; i < dp.length; i++) {
                dp[i] = dp[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int i, int j) {
            return dp[j + 1] - dp[i];
        }
    }
}
