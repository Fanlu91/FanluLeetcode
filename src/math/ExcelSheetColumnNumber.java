package math;

// Source : https://leetcode.com/problems/excel-sheet-column-number/
// Id     : 171
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07
// Topic  : Math
// Level  : Easy -
// Other  :
// Tips   : Carry is the key.
// Result : 100.00% 5.55%

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int time = 1, ans = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            ans += (s.charAt(i) - 'A' + 1) * time;
            time *= 26;
        }
        return ans;
    }
}
