package string;

// Source : https://leetcode.com/problems/longest-common-prefix/
// Id     : 14
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : String
// Level  : Easy
// Other  :
// Tips   :
// Result : 74.35% 100%

public class LongestCommonPrefix {
    // 74.35% 1ms 100%
    public String longestCommonPrefix(String[] strs) {
        String res = "";
        if (strs.length == 0)
            return res;

        for (int i = 0; i < strs[0].length(); i++) {
            for (String s : strs) {
                if (s.length() == i || s.charAt(i) != strs[0].charAt(i))
                    return res;
            }
            res += strs[0].charAt(i);
        }
        return res;
    }
}
