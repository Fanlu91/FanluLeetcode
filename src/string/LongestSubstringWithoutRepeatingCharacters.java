package string;

// Source : https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Id     : 3
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : String
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.83% 80.19%

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
}
