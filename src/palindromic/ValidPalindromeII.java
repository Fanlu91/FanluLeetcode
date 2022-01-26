package palindromic;

// Source : https://leetcode.com/problems/valid-palindrome-ii/
// Id     : 680
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-25
// Topic  : String
// Level  : Easy
// Other  :
// Tips   :
// Result : 99.68% 94.44%
public class ValidPalindromeII {

    // 79.58% 7ms 100%
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
                continue;
            } else {
                return isPalindromeAfterDelete(s, i + 1, j) || isPalindromeAfterDelete(s, i, j - 1);
            }
        }
        return true;
    }

    public boolean isPalindromeAfterDelete(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    // 99.68% 4 ms 94.44%
    public boolean validPalindromeImprove(String s) {
//    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        return validPalindrome(s, 0, s.length() - 1, 0);
    }

    private boolean validPalindrome(String s, int i, int j, int noMatchCount) {
        if (noMatchCount == 2) {
            return false;
        }
        while (j > i) {
            if (s.charAt(i) != s.charAt(j)) {
                return validPalindrome(s, i + 1, j, noMatchCount + 1) || validPalindrome(s, i, j - 1, noMatchCount + 1);
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindromeII v = new ValidPalindromeII();
        v.validPalindrome("cbbcc");
    }
}
