package math;

// Source : https://leetcode.com/problems/reverse-integer/
// Id     : 7
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-04
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 11.11%

public class ReverseInteger {

    // 8.73% 3ms 5.55%
    public int reverse(int x) {

        if (x == 0 || x == Integer.MIN_VALUE)
            return 0;

        boolean positive = true;
        String MAX = String.valueOf(Integer.MAX_VALUE);

        if (x < 0) {
            positive = false;
            x = -x;
        }

        String res = "";
        while (x != 0) {
            int tail = x % 10;
            x /= 10;
            res = res + tail;
        }
//        System.out.println(res);

        if (res.length() == 10) {
            //compare with 2147483647
            for (int i = 0; i < 10; i++) {
                if (res.charAt(i) < MAX.charAt(i))
                    break;
                if (res.charAt(i) > MAX.charAt(i))
                    return 0;
            }
        }

        if (!positive) {
            return -Integer.parseInt(res);
        }
        return Integer.parseInt(res);
    }

    // 100.00% 1ms 11.11%
    public int reverseImprove(int x) {
//    public int reverse(int x) {
        int result = 0, tmp = 0, tail = 0;
        while (x != 0) {
            tail = x % 10;
            tmp = result * 10 + tail;
            // if overflow return 0
            if ((tmp - tail) / 10 != result) {
                return 0;
            }
            result = tmp;
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(r.reverse(Integer.MIN_VALUE + 10));
        System.out.println(r.reverse(0));
    }
}
