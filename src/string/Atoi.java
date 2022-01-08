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
// Result : 99.87% 93.46%

import java.util.HashMap;
import java.util.Map;

public class Atoi {
    // 2ms
    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0)
            return 0;
        boolean negative = false;
        if (!Character.isDigit(s.charAt(0))) {
            if (s.charAt(0) == '-')
                negative = true;
            else if (s.charAt(0) == '+')
                ;
            else
                return 0;
            s = s.substring(1);
        }

        int index = 0, res = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int value = Character.getNumericValue(s.charAt(index));
            // 处理溢出问题
            if (res > (Integer.MAX_VALUE - value) / 10) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            int tmp = res * 10 + value;
//            System.out.println(res + " " + tmp);
            res = tmp;
            index++;
        }

        return negative ? -res : res;
    }

    public static void main(String[] args) {

        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoi("2147483649"));
//        System.out.println(Integer.MAX_VALUE + " " + (Integer.MAX_VALUE - 9));
    }

    // 3 ms
    public int myAtoi1(String str) {
//    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

}


class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";

    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ?
                    Math.min(ans, (long) Integer.MAX_VALUE) :
                    Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ')
            return 0;
        if (c == '+' || c == '-')
            return 1;
        if (Character.isDigit(c))
            return 2;
        return 3;
    }
}
