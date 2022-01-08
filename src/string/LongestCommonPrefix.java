package string;

// Source : https://leetcode.com/problems/longest-common-prefix/
// Id     : 14
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : String
// Level  : Easy-
// Other  :
// Tips   :
// Result : 74.35% 100%

public class LongestCommonPrefix {
    // 1ms
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

    // 1ms
    public String longestCommonPrefix1(String[] strs) {
//    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String res = strs[0];

        if (strs.length == 1)
            return res;

        for (int i = 1; i < strs.length; i++) {
            if (res.length() == 0)
                return "";
            for (int j = 0; j < res.length(); j++) {
                if (j == strs[i].length() || res.charAt(j) != strs[i].charAt(j)) {
                    res = res.substring(0, j);
                    break;
                }
            }
        }
        return res;
    }
}
