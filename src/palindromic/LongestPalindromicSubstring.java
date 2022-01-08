package palindromic;

// Source : https://leetcode.com/problems/longest-palindromic-substring/
// Id     : 5
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-06
// Topic  : String
// Level  : Medium
// Other  :
// Tips   :
// Result : 87.40% 69.11%

public class LongestPalindromicSubstring {
    int left, len;

    // 17 ms
    public String longestPalindrome(String s) {
        if (s.length() == 1)
            return s;
        left = 0;
        len = 0;
        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(i, i, s);
            checkPalindrome(i, i + 1, s);
            if (i > s.length() / 2 + 1 && len >= s.length() / 2 + 1)
                break;
        }
        return s.substring(left, left + len);
    }

    public void checkPalindrome(int l, int r, String s) {
        int tmp = l == r ? -1 : 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            tmp += 2;
            l--;
            r++;
        }
        if (tmp > len) {
            len = tmp;
            left = l + 1;
        }
    }

    private void checkPalindrome2(int i, int j, String s) {
//    private void checkPalindrome(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        // j should be j--, i should be i ++
        // length equals to 1+ (j-1) -(i+1) = j - i -1
        if (j - i - 1 > len) {
            left = i + 1;
            len = j - i - 1;
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("aa"));
    }
}
