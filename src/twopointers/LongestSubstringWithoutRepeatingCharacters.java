package twopointers;

// Source : https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Id     : 3
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Two Pointers
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.83% 80.19%

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    // 82.36% 7ms 99.76%
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<>();
        int res = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                res = Math.max(res, i - left);
            } else {
                int tmp = map.get(s.charAt(i));
                map.put(s.charAt(i), i);
                if (tmp >= left)
                    left = tmp + 1;
                else
                    res = Math.max(res, i - left);
            }
        }

        return res + 1;
    }

    /**
     * Improve performance with array instead of hashmap
     *
     * @param s
     * @return
     */
    // 99.83%  2ms 80.19%
    public int lengthOfLongestSubstringImprove(String s) {
//    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        int[] map = new int[128];
        Arrays.fill(map, -1);

        int res = 0, left = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map[c] >= left)
                left = map[s.charAt(i)] + 1;
            map[c] = i;
            res = Math.max(res, i - left);
        }

        return res + 1;
    }


    public int lengthOfLongestSubstringTLE(String s) {
//    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                String tmp = s.substring(i, j + 1);
                if (allUnique(tmp))
                    res = Math.max(res, 1 + j - i);
            }
        }
        return res;
    }

    public boolean allUnique(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i)))
                return false;
            else
                set.add(s.charAt(i));
        }
        return true;
    }

    // 11.10% 146 ms 5.20%
    public int lengthOfLongestSubstring1(String s) {
//    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        for (int left = 0; left < s.length(); left++) {
            Set<Character> set = new HashSet<>();
            int right = left;
            while (right < s.length()) {
                if (set.contains(s.charAt(right)))
                    break;
                else {
                    set.add(s.charAt(right));
                    right++;
                    res = Math.max(res, right - left + 1);
                }
            }
        }
        return res;
    }

    // 41.51% 10 ms 5.20%
    public int lengthOfLongestSubstring2(String s) {
//    public int lengthOfLongestSubstring(String s) {
        int res = 0, left = 0, right = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (set.contains(s.charAt(right)))
                while (left < right) {
                    if (s.charAt(left) == s.charAt(right)) {
                        set.remove(s.charAt(left));
                        left++;
                        break;
                    }
                    set.remove(s.charAt(left));
                    left++;
                }

            set.add(s.charAt(right));
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

    // 85.43% 6 ms 5.20%
    public int lengthOfLongestSubstring3(String s) {
//    public int lengthOfLongestSubstring(String s) {
        int res = 0, left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length() && left < s.length() - res) {
            if (map.containsKey(s.charAt(right)))
                left = Math.max(left, map.get(s.charAt(right)) + 1);

            map.put(s.charAt(right), right);
            res = Math.max(res, right - left + 1);
            right++;
        }

        return res;
    }

}
