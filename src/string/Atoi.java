package string;
// Source : https://leetcode.com/problems/string-to-integer-atoi/
// Id     : 8
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/8/18
// Topic  : string
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result :

public class Atoi {
    public int myAtoi(String s) {
        s = s.trim();
        boolean negative = false;
        if (!Character.isDigit(s.charAt(0))) {
            if (s.charAt(0) == '-')
                negative = true;
            else if (s.charAt(0) == '+')
                ;
            else
                return 0;
        }

        int p = 1;
        int res = 0;
        while (p < s.length() - 1) {
            if (!Character.isDigit(s.charAt(p)))
                return res;
            res += Math.pow(10, p) * (int)(s.charAt(p));
            p++;
        }
        return res;
    }

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("+1"));
    }
}