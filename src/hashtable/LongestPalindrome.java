package hashtable;

// Source : https://leetcode.com/problems/longest-palindrome/
// Id     : 409
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-24
// Topic  : Hashtable
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 100%
public class LongestPalindrome {
    // 68.10% 2ms  100.00%
    public int longestPalindrome(String s) {
        int[] table = new int[52];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'A';
            if (index < 26)
                table[index]++;
            else
                table[index - 6]++;
        }

        int count = 0;
        boolean odd = false;
        for (int i = 0; i < 52; i++) {
            if ((table[i] & 1) == 0)
                count += table[i];
            else {
                odd = true;
                if (table[i] > 1)
                    count += table[i] - 1;
            }
        }
        return odd ? count + 1 : count;
    }

    public int longestPalindromeImprove(String s) {
//    public int longestPalindrome(String s) {
        int count = 0;
        boolean[] table = new boolean[52];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'A';
            if (index < 26) {
                if (table[index]) {
                    count += 2;
                    table[index] = false;
                } else
                    table[index] = true;
            } else {
                if (table[index - 6]) {
                    count += 2;
                    table[index - 6] = false;
                } else
                    table[index - 6] = true;
            }
        }

        for (int i = 0; i < 52; i++) {
            if (table[i] == true)
                return count + 1;
        }
        return count;
    }

    public int longestPalindromeImprove2(String s) {
//    public int longestPalindrome(String s) {
        int count = 0;
        boolean[] table = new boolean[58];// A 65 z 122
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'A';
            if (table[index]) {
                count += 2;
                table[index] = false;
            } else
                table[index] = true;
        }

        return s.length() > count ? count + 1 : count;
    }

    // 100.00% 1ms  100.00%
    public int longestPalindromeBest(String s) {
//    public int longestPalindrome(String s) {
        int count = 0;
        int[] table = new int[58];// A 65 z 122
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'A']++;
        }

        for (int i = 0; i < 58; i++) {
            count += (table[i] >> 1) << 1;
        }
        return s.length() > count ? count + 1 : count;
    }
}
