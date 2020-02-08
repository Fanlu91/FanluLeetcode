package string;

// Source : https://leetcode.com/problems/zigzag-conversion/
// Id     : 6
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-02-08
// Topic  : String
// Level  : Medium
// Other  :
// Tips   :
// Result : 94.85% 93.46%

public class ZigZagConversion {
    // 30.79% 18 ms 34.85%

    /**
     * 0 1 2 3 4 5 6 7 8 9 10
     * 0 1 2 3 2 1 0 1 2 3 2
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows < 2)
            return s;

        String[] stringRows = new String[numRows];
        for (int i = 0; i < numRows; i++) {
            stringRows[i] = "";
        }

        int index = 0;
        boolean downward = true;

        for (int i = 0; i < s.length(); i++) {
            stringRows[index] += s.charAt(i);

            if (index == numRows - 1)
                downward = false;
            if (index == 0)
                downward = true;

            if (downward)
                index++;
            else
                index--;
        }
        String ans = "";
        for (String row : stringRows)
            ans += row;
        return ans;
    }

    // 94.85% 4ms 93.46%
    public String convertImprove(String s, int numRows) {
//    public String convert(String s, int numRows) {
        if (numRows < 2)
            return s;

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                sb.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
                    sb.append(s.charAt(j + cycleLen - i));
                }
            }
        }
        return sb.toString();
    }
}
