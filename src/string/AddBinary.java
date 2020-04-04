package string;
// Source : https://leetcode.com/problems/add-binary/
// Id     : 67
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-12
// Topic  : String
// Level  : Easy
// Other  :
// Tips   :
// Result : 97.15% 5.12%

public class AddBinary {
    // 97.15% 2 ms 5.12%
    public String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int carry = 0;

        if (a.length() < b.length()) {
//            String tmp = a;
//            a = b;
//            b = tmp;
            return addBinary(b, a);
        }

        for (int i = 0; i < a.length(); i++) {
            int valueA = a.charAt(a.length() - 1 - i) - '0';
            int valueB = i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;
            int sum = valueA + valueB + carry;
            if (sum == 2) {
                stringBuilder.append(0);
                carry = 1;
            } else if (sum == 3) {
                stringBuilder.append(1);
                carry = 1;
            } else {
                stringBuilder.append(sum);
                carry = 0;
            }
        }
        if (carry == 1)
            stringBuilder.append(1);

        return stringBuilder.reverse().toString();
    }
}
