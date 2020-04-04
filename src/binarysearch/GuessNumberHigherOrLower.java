package binarysearch;

// Source : https://leetcode.com/problems/guess-number-higher-or-lower/
// Id     : 374
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.21%

public class GuessNumberHigherOrLower {
    public int guessNumber(int n) {
        int l = 0, r = n;
        while (true) {
            int m = l + (r - l) / 2;
            int res = guess(m);
            if (res == 0)
                return m;
            if (res == -1) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
    }

    public int guess(int n) {
        return 0;
    }
}
