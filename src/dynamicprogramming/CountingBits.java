package dynamicprogramming;

// Source : https://leetcode.com/problems/counting-bits/
// Id     : 338
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-20
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 100.00% 5.88%

public class CountingBits {


    /**
     * 1. list all bits (maybe from 0 to 15)
     * 2. try to figure out a pattern or function
     * 3. and there is :
     * f(i) = f(i >> 1) + i%2
     *
     * @param num
     * @return
     */
    // 99.78% 1ms 5.88%
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i < num + 1; i++) {
            res[i] = res[i >> 1] + i % 2;
        }
        return res;
    }

    /**
     * Learned from leetcode submission.
     * <p>
     * The idea is for any number i,
     * i*2 will have the same amount of 1s it has(besides an extra 0 to the right)
     * i*2 +1 will have 1 more 1 (extra 1 to the right)
     * Very nice!
     *
     * @param num
     * @return
     */
    // 100.00% 0ms 5.88%
    public int[] countBitsImprove(int num) {
//    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        helper(num, 1, 1, dp);
        return dp;
    }

    private void helper(int num, int i, int ones, int[] dp) {
        if (i > num)
            return;
        dp[i] = ones;

        helper(num, (i << 1), ones, dp);
        helper(num, (i << 1) + 1, ones + 1, dp);
    }
}
