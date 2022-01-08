package greedy;

// Source : https://leetcode.com/problems/roman-to-integer/
// Id     : 13
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Result : 94.96% 94.49%
public class RomanToInteger {
    // 3ms
    public int romanToInt(String s) {
        int res = 0;
        // nice one
        s += 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'C') {
                if (s.charAt(i + 1) == 'D') {
                    res += 400;
                    i++;
                } else if (s.charAt(i + 1) == 'M') {
                    res += 900;
                    i++;
                } else {
                    res += 100;
                }
            } else if (s.charAt(i) == 'X') {
                if (s.charAt(i + 1) == 'L') {
                    res += 40;
                    i++;
                } else if (s.charAt(i + 1) == 'C') {
                    res += 90;
                    i++;
                } else {
                    res += 10;
                }
            } else if (s.charAt(i) == 'I') {
                if (s.charAt(i + 1) == 'V') {
                    res += 4;
                    i++;
                } else if (s.charAt(i + 1) == 'X') {
                    res += 9;
                    i++;
                } else {
                    res += 1;
                }

            } else if (s.charAt(i) == 'M')
                res += 1000;
            else if (s.charAt(i) == 'D')
                res += 500;
            else if (s.charAt(i) == 'L')
                res += 50;
            else if (s.charAt(i) == 'V')
                res += 5;
        }
        return res;
    }

    // 4ms
    public int romanToInt1(String s) {
//    public int romanToInt(String s) {
        int sum = 0;
        int pre = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (pre < num) {
                sum -= pre;
            } else {
                sum += pre;
            }
            pre = num;
        }
        sum += pre;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
