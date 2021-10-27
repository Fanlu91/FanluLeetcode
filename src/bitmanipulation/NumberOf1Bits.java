package bitmanipulation;

// Source : https://leetcode.com/problems/number-of-1-bits/
// Id     : 191
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-02
// Topic  : Bit Manipulation
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

public class NumberOf1Bits {
    /**
     * In Java, there is no unsigned integer type.
     * You need to treat n as an unsigned value.
     * In Java, the compiler represents the signed integers using 2's complement notation.
     */

    public int hammingWeight(int n) {
        int res = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                res++;
            }
            mask <<= 1;
        }
        return res;
    }


    // 右移 32 次
    // 100.00% 79.18%
    public int hammingWeight1(int n) {
//        public int hammingWeight(int n) {
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if (((n >> i) & 1) != 0)
                res++;
        }
        return res;
    }

    // 通过 n & (n - 1) 可以翻转最低有效比特为 1 的比特为 0
    // 100% 100%
    public int hammingWeight2(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    /**
     *    public static int bitCount(int i) {
     *         // HD, Figure 5-2
     *         i = i - ((i >>> 1) & 0x55555555);
     *         i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
     *         i = (i + (i >>> 4)) & 0x0f0f0f0f;
     *         i = i + (i >>> 8);
     *         i = i + (i >>> 16);
     *         return i & 0x3f;
     *     }
     */
    public int hammingWeight3(int n) {
//        public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
