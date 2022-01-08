package greedy;

// Source : https://leetcode.com/problems/integer-to-roman/
// Id     : 12
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-11-05
// Topic  : Greedy
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

    // 2 ms
    public String intToRoman1(int num) {
//    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        if (num >= 1000) {
            tmp = num / 1000;
            num %= 1000;
            for (int i = 0; i < tmp; i++) {
                sb.append("M");
            }
        }

        if (num >= 100) {
            tmp = num / 100;
            num %= 100;
            if (tmp == 9) {
                sb.append("CM");
            } else if (tmp == 4) {
                sb.append("CD");
            } else {
                if (tmp > 4) {
                    sb.append("D");
                    tmp -= 5;
                }
                while (tmp > 0) {
                    sb.append("C");
                    tmp--;
                }
            }
        }

        if (num >= 10) {
            tmp = num / 10;
            if (tmp == 9) {
                sb.append("XC");
            } else if (tmp == 4) {
                sb.append("XL");
            } else {
                if (tmp > 4) {
                    sb.append("L");
                    tmp -= 5;
                }
                while (tmp > 0) {
                    sb.append("X");
                    tmp--;
                }
            }
            num = num % 10;
        }

        if (num == 9) {
            sb.append("IX");
        } else if (num == 4) {
            sb.append("IV");
        } else {
            if (num >= 5) {
                sb.append("V");
                num -= 5;
            }
            while (num != 0) {
                sb.append("I");
                num--;
            }

        }
        return sb.toString();
    }

    // 4 ms
    public String intToRoman2(int num) {
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

    // 15 ms
    public String intToRoman3(int num) {
//    public String intToRoman(int num) {
        String[] M = {"", "M", "MM", "MMM"};
        String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
