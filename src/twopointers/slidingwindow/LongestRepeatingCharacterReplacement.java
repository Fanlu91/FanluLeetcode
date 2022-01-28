package twopointers.slidingwindow;

// Source : https://leetcode.com/problems/longest-repeating-character-replacement/
// Id     : 424
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-03
// Topic  : Sliding Window
// Level  : Medium
// Other  :
// Tips   :
// Result : 33.67% 100.00%

public class LongestRepeatingCharacterReplacement {

    // 23.86% 11 ms 100.00%
    public int characterReplacement(String s, int k) {
        int res = 0, left = 0, right = 0;
        int[] counts = new int[26];

        while (right < s.length()) {
            counts[s.charAt(right) - 'A']++;
            int maxCharCount = getMostCharCount(counts);
            right++;
            if (right - left - maxCharCount > k) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }
        }
        return right - left;
    }

    private int getMostCharCount(int[] counts) {
        int ans = 0;
        for (int n : counts)
            ans = Math.max(ans, n);
        return ans;
    }

    // 33.67% 9ms 100.00%
    public int characterReplacement1(String s, int k) {
//    public int characterReplacement(String s, int k) {
        int res = 0, tmpMax = 0;
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'A']++;
            tmpMax = Math.max(tmpMax, counts[s.charAt(i) - 'A']);

            if (res - tmpMax < k)
                res++;
            else
                counts[s.charAt(i - res) - 'A']--;
        }
        return res;
    }


    private int[] map = new int[26];

    public int characterReplacement2(String s, int k) {
//    public int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = 0;
        int historyCharMax = 0;
        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }
}
