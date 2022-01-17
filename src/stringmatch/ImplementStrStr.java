package stringmatch;

// Source : https://leetcode.com/problems/implement-strstr/
// Id     : 28
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-06
// Topic  : String
// Level  : Easy+
// Other  :
// Tips   :
// Result : 53.86% 64.88%

public class ImplementStrStr {

    // 1619 ms
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty())
            return 0;
        if (haystack.isEmpty() || needle.length() > haystack.length())
            return -1;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = 0, tmp = i;
            while (j < needle.length()) {
                if (haystack.charAt(tmp) != needle.charAt(j))
                    break;
                else {
                    j++;
                    tmp++;
                }
            }
            if (j == needle.length())
                return i;
        }
        return -1;
    }

    // 380ms
    public int strStr1(String haystack, String needle) {
//    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        return haystack.indexOf(needle);
    }



    public static void main(String[] args) {
        ImplementStrStr i = new ImplementStrStr();
        System.out.println(i.strStr("qqhh", "hh"));
    }
}
