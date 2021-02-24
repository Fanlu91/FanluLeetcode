package string;
// Source : https://leetcode.com/problems/add-strings/
// Id     : 415
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/22
// Topic  : Math
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 99.62% 26.75%

public class AddStrings {
    // 64.21% 3 ms 31.71%
    public String addStrings(String num1, String num2) {
        int lastIndex1 = num1.length() - 1, lastIndex2 = num2.length() - 1;
        int size = Math.min(num1.length(), num2.length());
        StringBuilder sb = new StringBuilder();
        int tmp = 0, carry = 0;
        for (int i = 0; i < size; i++) {
            tmp = carry + Character.getNumericValue(num1.charAt(lastIndex1 - i)) + Character.getNumericValue(num2.charAt(lastIndex2 - i));
            if (tmp > 9) {
                tmp -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            sb.append(tmp);
        }

        if (num1.length() > num2.length()) {
            size = num1.length() - num2.length();
            for (int i = 0; i < size; i++) {
                tmp = carry + Character.getNumericValue(num1.charAt(lastIndex1 - num2.length() - i));
                if (tmp > 9) {
                    tmp -= 10;
                    carry = 1;
                } else {
                    carry = 0;
                }
                sb.append(tmp);

            }
        } else if (num1.length() < num2.length()) {
            size = num2.length() - num1.length();
            for (int i = 0; i < size; i++) {
                tmp = carry + Character.getNumericValue(num2.charAt(lastIndex2 - num1.length() - i));
                if (tmp > 9) {
                    tmp -= 10;
                    carry = 1;
                } else {
                    carry = 0;
                }
                sb.append(tmp);
            }
        }
        if (carry == 1)
            sb.append(1);
        return sb.reverse().toString();
    }


    // 99.62% 2 ms 26.75%
    public String addStrings1(String num1, String num2) {
//    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int lastIndex1 = num1.length() - 1, lastIndex2 = num2.length() - 1, carry = 0;
        while (lastIndex1 >= 0 || lastIndex2 >= 0) {
            int tmp = 0;
            int n1 = lastIndex1 >= 0 ? num1.charAt(lastIndex1) - '0' : 0;
            int n2 = lastIndex2 >= 0 ? num2.charAt(lastIndex2) - '0' : 0;
            tmp = carry + n1 + n2;
            carry = tmp / 10;
            res.append(tmp % 10);
            lastIndex1--;
            lastIndex2--;
        }
        if (carry == 1)
            res.append(1);
        return res.reverse().toString();    //要将res反转
        // return res.toString();
    }


    public static void main(String[] args) {
        AddStrings a = new AddStrings();
        System.out.println(a.addStrings("9", "99"));
    }
}