package string;
// Source : https://leetcode.com/problems/reverse-string/
// Id     : 344
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/22
// Topic  : String
// Level  : Easy-
// Other  :
// Tips   :
// Links  : ToFeelGood
// Result : 100.00% 18.74%

public class ReverseString {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            swap(s, l, r);
            l++;
            r--;
        }
    }

    public void swap(char[] s, int l, int r) {
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
    }
}