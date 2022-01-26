package stack;

// Source : https://leetcode.com/problems/longest-valid-parentheses/
// Id     : 32
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021-06-12
// Topic  : Stack
// Level  : Hard
// Other  :
// Tips   :
// Result : 98.69% 99.98%

import java.util.Stack;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s.length() < 2)
            return 0;
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='('){
                stack.push(i);
            }else{

            }
        }
        return res;
    }
}
