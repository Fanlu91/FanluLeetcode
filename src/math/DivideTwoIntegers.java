package math;
// Source : https://leetcode.com/problems/divide-two-integers/
// Id     : 29
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/15
// Topic  : math 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 88% 49%

public class DivideTwoIntegers {
    // 2736 ms
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE)
            return 1;
        if (dividend == 0 || divisor == Integer.MIN_VALUE)
            return 0;
        if (divisor == 1)
            return dividend;

        boolean negative = false;

        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
            negative = true;
        int res = 0;
        divisor = Math.abs(divisor);

        if (dividend == Integer.MIN_VALUE) {
            dividend += Math.abs(divisor);
            res++;
        }
        dividend = Math.abs(dividend);

        while (dividend >= divisor) {
            dividend -= divisor;
            res++;
        }

        return negative ? -res : res;
    }

    // 1 ms
    public int divide1(int dividend, int divisor) {
//    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {
                res += 1 << i;
                a -= b << i;
            }
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs(Integer.MIN_VALUE + 1));
    }
}