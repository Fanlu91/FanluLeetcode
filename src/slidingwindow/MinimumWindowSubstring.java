package slidingwindow;

// Source : https://leetcode.com/problems/minimum-window-substring/
// Id     : 76
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-03
// Topic  : Sliding Window
// Level  : Hard
// Other  :
// Tips   :
// Result : 42.34% 13.33%

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MinimumWindowSubstring {

    // 42.34% 20 ms 13.33%
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0)
            return "";
        int left = 0, right = 0;
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int required = tMap.keySet().size(), formed = 0;
        Map<Character, Integer> windowMap = new HashMap<>();

        // ans[0] 存放字符长度，ans[1]和ans[2]分别存放左右索引
        int[] ans = new int[3];
        ans[0] = Integer.MAX_VALUE;

        while (right < s.length()) {
            char character = s.charAt(right);
            windowMap.put(character, windowMap.getOrDefault(character, 0) + 1);

            // do not compare Integer with ==. It can only work within -128 ~ 127.
//            if (tMap.containsKey(character) && windowMap.get(character) == tMap.get(character))
            if (tMap.containsKey(character) && windowMap.get(character).intValue() == tMap.get(character).intValue())
                formed++;

            while (left <= right && (formed == required)) {
                character = s.charAt(left);

                if (right - left + 1 < ans[0]) {
                    ans = new int[]{right - left + 1, left, right};
                }
                windowMap.put(character, windowMap.getOrDefault(character, 1) - 1);

                if (tMap.containsKey(character) && windowMap.get(character) < tMap.get(character))
                    formed -= 1;

                left++;
            }
            right++;
        }
        return ans[0] == Integer.MAX_VALUE ? "" : s.substring(ans[1], ans[2] + 1);
    }

}
