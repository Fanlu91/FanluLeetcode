package gcd;
// Source : https://leetcode.com/problems/greatest-common-divisor-of-strings/
// Id     : 1071
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/13
// Topic  : Greatest Common Divisor
// Level  : Easy
// Other  :
// Tips   : Euclidean algorithm 辗转相除法求公约数 gcd(a,b) = gcd(b,a mod b)。
// Links  :
// Result : 97.81% 100.00%

public class GreatestCommonDivisorOfStrings {
    public String gcdOfStrings(String str1, String str2) {
        // 下面的公式是满足题意的充要条件
        if (!(str1 + str2).equals(str2 + str1))
            return "";

        int divisorLength = gcd(str1.length(), str2.length());
        return str1.substring(0, divisorLength);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
        /*return b == 0? a: gcd(b, a % b);*/

        /*if (n == 0)
            return m;
        int r = m % n;
        return gcd(n, r);*/
    }
}