package math;

// Source : https://leetcode.com/problems/palindrome-number/
// Id     : 9
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-25
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.02%
public class PalindromeNumber {
    // 100.00% 6ms 5.02%
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0))
            return false;
        int num = 0;
        while (x > num) {
            num = num * 10 + x % 10;
            x = x / 10;
        }
        return x == num || x == num / 10;
    }
}
