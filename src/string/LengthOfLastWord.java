package string;

// Source : https://leetcode.com/problems/length-of-last-word/
// Id     : 58
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : String
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.32%

public class LengthOfLastWord {
    // 41.99% 1ms 5.36%
    public int lengthOfLastWord(String s) {
        if (s.length() == 0)
            return 0;
        System.out.println(s.length());
        int ans = 0;
        int index = s.length() - 1;
        while (index > 0 && s.charAt(index) == ' ')
            index--;

        while (index >= 0) {
            if (s.charAt(index) != ' ')
                ans++;
            else
                return ans;
            index--;
        }
        return ans;
    }

    // 100.00% 0ms  5.32%
    public int lengthOfLastWord2(String s) {
//    public int lengthOfLastWord(String s) {
        int ans = 0;
        s = s.trim();
        int index = s.length() - 1;

        while (index >= 0) {
            if (s.charAt(index) != ' ')
                ans++;
            else
                return ans;
            index--;
        }
        return ans;
    }
}
