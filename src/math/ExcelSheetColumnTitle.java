package math;

// Source : https://leetcode.com/problems/excel-sheet-column-title/
// Id     : 168
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   : Carry is the key.
// Result : 100.00% 14.29%

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int carry = n % 26;
            n /= 26;
            if (carry == 0) {
                carry = 26;
                n--;
            }
            sb.insert(0, (char) ('A' + carry - 1));
        }
        return sb.toString();
    }
}
