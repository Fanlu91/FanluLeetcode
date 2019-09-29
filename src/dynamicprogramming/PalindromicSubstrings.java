package dynamicprogramming;
// Source : https://leetcode.com/problems/palindromic-substrings/
// Id     : 647
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-20
// Topic  : Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.97% 100.00%

public class PalindromicSubstrings {
    int count = 0;

    // 99.97% 1ms 100.00%
    public int countSubstrings(String s) {
        if (s.length() == 0)
            return 0;
        if (s.length() == 1)
            return 1;
        for (int i = 0; i < s.length(); i++) {
            helper(i, i, s);
            helper(i, i + 1, s);
        }
        return count;
    }

    public void helper(int left, int right, String s) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            System.out.println("left: " + left + " right: " + right + " count: " + count);
            left--;
            right++;
        }
    }


    public int countSubstringsImprove(String s) {
//    public int countSubstrings(String s) {
        char[] chs = s.toCharArray();
        int index = 0;
        int[] ans = new int[1];
        while (index < chs.length) {
            index = expand(chs, index, ans);
        }

        return ans[0];
    }

    private int expand(char[] chs, int index, int[] ans) {
        int i = index - 1;
        int j = index;
        while (j < chs.length - 1 && chs[j] == chs[j + 1]) j++;
        int count = j - i;
        ans[0] += (1 + count) * count / 2;
        int tail = ++j;
        while (i >= 0 && j < chs.length && chs[i] == chs[j]) {
            i--;
            j++;
            ans[0]++;
        }

        return tail;
    }

    public static void main(String[] args) {
        PalindromicSubstrings p = new PalindromicSubstrings();
        p.countSubstrings("aaa");
    }
}
