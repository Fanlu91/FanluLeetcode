package math;

// Source : https://leetcode.com/problems/factorial-trailing-zeroes/
// Id     : 172
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class FactorialTrailingZeroes {


    // TLE
    public int trailingZeroes(int n) {

        int count = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                count++;
                currentFactor /= 5;
            }
        }
        return count;
    }

    /**
     * 因为每隔 5 个数出现一个 5，出现了多少个 5可以用 n/5 计算。
     * 对于5的多次幂 需要除多次
     *
     * @param n
     * @return
     */
    public int trailingZeroes1(int n) {
//    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}