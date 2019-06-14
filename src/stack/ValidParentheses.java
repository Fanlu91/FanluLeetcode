package stack;

// Source : https://leetcode.com/problems/valid-parentheses/
// Id     : 20
// Author : Fanlu Hai
// Date   : 2018-06-12
// Topic  : Stack
// Level  : Easy
// Other  :
// Tips   : char[] is more efficient than string.charAt[]
// Result : 98.69% 99.98%

import java.util.Stack;

public class ValidParentheses {

    //70.77% 2 ms 100.00%
    public boolean isValidOrigin(String s) {
        if (s.isEmpty())
            return true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty())
                    return false;
                char tmp = stack.pop();
                if (!((tmp == '{' && s.charAt(i) == '}')
                        || (tmp == '(' && s.charAt(i) == ')')
                        || (tmp == '[' && s.charAt(i) == ']')))
                    return false;
            }
        }

        return stack.isEmpty() ? true : false;
    }

    // only replaced string.charAt to char[]
    // 98.69% 99.98%
    public boolean isValid(String s) {
        if (s.isEmpty())
            return true;
        Stack<Character> stack = new Stack<>();
        char[] list = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (list[i] == '(' || list[i] == '{' || list[i] == '[') {
                stack.push(list[i]);
            } else {
                if (stack.isEmpty())
                    return false;
                char tmp = stack.pop();
                if (!((tmp == '{' && list[i] == '}')
                        || (tmp == '(' && list[i] == ')')
                        || (tmp == '[' && list[i] == ']')))
                    return false;
            }
        }

        return stack.isEmpty() ? true : false;
    }
}
