package binarysearch;

// Source : https://leetcode.com/problems/sqrtx/
// Id     : 69
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.04%

public class SqrtX {
    public int mySqrt(int x) {
        double l = 0, r = x;
        while ((int)l!=(int)r) {
            double ans = (l + r) / 2;
            if (ans * ans == x)
                return (int)ans;

            if (ans * ans > x) {
                r = ans;
            } else {
                l = ans;
            }
        }
        return (int)l;
    }
}
