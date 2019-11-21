package string;

// Source : https://leetcode.com/problems/longest-palindromic-substring/
// Id     : 5
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-06
// Topic  : String
// Level  : Medium
// Other  :
// Tips   :
// Result : 98.90% 100%

public class LongestPalindromicSubstring {
    int left, length;

    // 96.98% 5ms 100%
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;

        left = 0;
        length = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            checkPalindrom(i, i, s);
            checkPalindrom(i, i + 1, s);
            if (length > s.length() / 2 + 1 && i > s.length() / 2 + 1)
                break;
        }
        return s.substring(left, left + length);
    }

    private void checkPalindromOld(int i, int j, String s) {
        int l = 0;
        if (i == j)
            l--;

        while (s.charAt(i) == s.charAt(j)) {
            l += 2;
            i--;
            j++;
            if (i < 0 || j > s.length() - 1)
                break;
        }
        if (l > length) {

            left = i + 1;
            length = l;
        }
    }

    // 98.90% 4ms
    private void checkPalindrom(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        // j should be j--, i should be i ++
        // length equals to 1+ (j-1) -(i+1) = j - i -1
        if (j - i - 1 > length) {
            left = i + 1;
            length = j - i - 1;
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("abababa"));
    }
}
