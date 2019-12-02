package string;

// Source : https://leetcode.com/problems/implement-strstr/
// Id     : 28
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-06
// Topic  : String
// Level  : Easy
// Other  :
// Tips   :
// Result : 53.86% 64.88%

public class ImplementStrStr {

    // 53.86% 2 ms  64.88%
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        if (haystack.length() < needle.length())
            return -1;

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = 0, index = i;
            while (j < needle.length() && haystack.charAt(index + j) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length())
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        ImplementStrStr i = new ImplementStrStr();
        System.out.println(i.strStr("mississippi", "issip"));
    }
}
