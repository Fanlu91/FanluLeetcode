package dynamicprogramming;
// Source : https://leetcode-cn.com/problems/paint-fence/
// Id     : 276
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/15
// Topic  : Dynamic Programming
// Level  : Easy
// Other  :
// Tips   :
// Links  : 256
// Result : 100% 100%

public class PaintFence {
    /**
     * F(n) = F(n - 2) * (k - 1) + F(n - 1) * (k - 1)
     *
     * @param n
     * @param k
     * @return
     */
    // 100.00% 0ms 100.00%
    public int numWays(int n, int k) {
        if (n == 0 || k == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k * k;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
        }
        return dp[n - 1];

    }
}