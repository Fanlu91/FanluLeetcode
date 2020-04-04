package array;

// Source : https://leetcode.com/problems/plus-one/
// Id     : 66
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Array
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 5.10%

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int index = digits.length - 1;
        while (index > 0) {
            if (digits[index] == 9) {
                digits[index] = 0;
                index--;
            } else {
                digits[index]++;
                return digits;
            }
        }

        if (digits[0] == 9) {
            if (digits.length == 1)
                return new int[]{1, 0};

            int[] ans = new int[digits.length + 1];
            ans[0] = 1;
            ans[1] = 0;
            for (int i = 2; i < ans.length; i++) {
                ans[i] = digits[i - 1];
            }
            return ans;
        } else {
            digits[0]++;
            return digits;
        }
    }
}
