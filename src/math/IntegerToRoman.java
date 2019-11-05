package math;

// Source : https://leetcode.com/problems/integer-to-roman/
// Id     : 12
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Math
// Level  : Medium-
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class IntegerToRoman {
    // 31.03% 5ms 100%
    public String intToRoman(int num) {
        int thousands = 0, hundreds = 0, tens = 0;
        String res = "";

        if (num > 999) {
            thousands = num / 1000;
            num %= 1000;

            for (int i = 0; i < thousands; i++) {
                res += 'M';
            }
        }
        if (num > 99) {
            hundreds = num / 100;
            num %= 100;
            if (hundreds == 9) {
                res += "CM";
            } else if (hundreds == 4) {
                res += "CD";
            } else {
                if (hundreds > 4) {
                    res += 'D';
                    hundreds -= 5;
                }
                for (int i = 0; i < hundreds; i++) {
                    res += 'C';
                }
            }
        }
        if (num > 9) {
            tens = num / 10;
            num = num % 10;
            if (tens == 9) {
                res += "XC";
            } else if (tens == 4) {
                res += "XL";
            } else {
                if (tens > 4) {
                    res += 'L';
                    tens -= 5;
                }
                for (int i = 0; i < tens; i++) {
                    res += 'X';
                }
            }
        }

        if (num == 9)
            res += "IX";
        else if (num == 4)
            res += "IV";
        else {
            if (num > 4) {
                res += "V";
                num -= 5;
            }
            for (int i = 0; i < num; i++) {
                res += 'I';
            }
        }
        return res;
    }

    public String intToRomanImprove(int num) {
//    public String intToRoman(int num) {

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                stringBuilder.append(romans[i]);
            }
        }
        return stringBuilder.toString();
    }
}
