package palindromic;

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
    // 6ms
    public boolean isPalindrome(int x) {
        if (x == 0)
            return true;
        if (x < 0 || x % 10 == 0)
            return false;
        int tmp = 0;
        while (x > tmp) {
            tmp = tmp * 10 + x % 10;
            x /= 10;
        }
        return x == tmp || x == tmp / 10;
    }

    // 5 ms
    public boolean isPalindrome1(int x) {
//    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int cur = 0;
        int num = x;
        while (num != 0) {
            cur = cur * 10 + num % 10;
            num /= 10;
        }
        return cur == x;
    }

    // practice
    // 8ms
    public boolean isPalindrome2(int x) {
//    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x < 10)
            return true;
        if (x % 10 == 0)
            return false;
        int tmp = 0;
        while (tmp < x) {
            tmp = tmp * 10 + x % 10;
            x /= 10;
        }
        return x == tmp ? true : x == tmp / 10 ? true : false;
    }
}
