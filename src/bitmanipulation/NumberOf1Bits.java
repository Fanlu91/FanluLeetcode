package bitmanipulation;

// Source : https://leetcode.com/problems/number-of-1-bits/
// Id     : 191
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-02
// Topic  : Bit Manipulation
// Level  : Easy
// Other  :
// Tips   : 100% 100%
public class NumberOf1Bits {
    /**
     * In Java, there is no unsigned integer type.
     * You need to treat n as an unsigned value.
     * In Java, the compiler represents the signed integers using 2's complement notation.
     */

    public int hammingWeight(int n) {
        int retVal = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                retVal++;
            }
            mask <<= 1;
        }
        return retVal;
    }


    public int hammingWeight1(int n) {
        int retVal = 0;
        for (int i = 31; i >= 0; i--) {
            if (((n >> i) & 1) != 0)
                retVal++;
        }
        return retVal;
    }

    // 通过 n & (n - 1) 可以翻转最低有效比特为 1 的比特为 0
    // 100% 100%
    public int hammingWeight2(int n) {
        int retVal = 0;
        while (n != 0) {
            n = n & (n - 1);
            retVal++;
        }
        return retVal;
    }
}
