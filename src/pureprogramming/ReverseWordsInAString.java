package pureprogramming;
// Source : https://leetcode.com/problems/reverse-words-in-a-string/
// Id     : 151
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/1/20
// Topic  : pureprogramming
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 16.01%

public class ReverseWordsInAString {
    // 2ms
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        int l = s.length() - 1, r = l;
        while (l > 0) {
            if (s.charAt(l) == ' ') {
                if (l != r)
                    sb.append(s.substring(l + 1, r + 1)).append(" ");
                l--;
                r = l;
            } else {
                if (s.charAt(r) == ' ')
                    r = l;
                l--;
            }
        }
        sb.append(s.substring(0, r + 1));

        return sb.toString();
    }



    public static void main(String[] args) {
        ReverseWordsInAString reverseWordsInAString = new ReverseWordsInAString();
        System.out.println(reverseWordsInAString.reverseWords(" ad df f"));

    }
}