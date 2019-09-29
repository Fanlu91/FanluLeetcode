package string;

// Source : https://leetcode.com/problems/valid-palindrome/
// Id     : 125
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-25
// Topic  : String
// Level  : Easy
// Other  :
// Tips   :
// Result : 100% 100%
public class ValidPalindrome {

    // 12.90% 23 ms 90.18%
    public boolean isPalindrome(String s) {
        if (s.length() < 2)
            return true;
        String processed = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        for (int i = 0; i < processed.length() / 2; i++) {
            if (processed.charAt(i) != processed.charAt(processed.length() - 1 - i))
                return false;
        }
        return true;
    }

    // 81.37% 4ms 100.00%
    public boolean isPalindromeImprove(String s) {
//    public boolean isPalindrome(String s) {
        if (s.length() < 2)
            return true;
        String processed = s.trim().toUpperCase();
        int left = 0, right = processed.length() - 1;
        while (left < right) {
            int index = processed.charAt(left) - '0';
            if (index < 0 || index > 42 || (index > 9 && index < 17)) {
                left++;
                continue;
            }
            index = processed.charAt(right) - '0';
            if (index < 0 || index > 42 || (index > 9 && index < 17)) {
                right--;
                continue;
            }

            if (processed.charAt(left) != processed.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    // 100.00% 1ms 100%
    public boolean isPalindromeBetter(String s) {
//    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        char[] c = s.toCharArray();
        while (i < j) {
            if (c[i] >= 'A' && c[i] <= 'Z')
                c[i] += 'a' - 'A';
            if (c[j] >= 'A' && c[j] <= 'Z')
                c[j] += 'a' - 'A';

            if (!(c[i] >= 'a' && c[i] <= 'z' || c[i] >= '0' && c[i] <= '9')) {
                i++;
                continue;
            }
            if (!(c[j] >= 'a' && c[j] <= 'z' || c[j] >= '0' && c[j] <= '9')) {
                j--;
                continue;
            }
            if (c[i] != c[j])
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome v = new ValidPalindrome();
        System.out.println(v.isPalindromeImprove("A m"));
    }
}
