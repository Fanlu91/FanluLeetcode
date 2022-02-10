package stack;
// Source : https://leetcode.com/problems/basic-calculator/
// Id     : 224
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/8
// Topic  : stack 
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 43.08% 17.47%

import java.util.Stack;

public class BasicCalculator {
    // 27 ms
    public int calculate(String s) {
        Stack<Character> opsStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        char[] arr = s.toCharArray();
        char preSign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = arr[i];
            if (c == ' ')
                continue;
            if (Character.isDigit(c)) {
                int val = c - '0';
                while (i + 1 < s.length() && Character.isDigit(arr[i + 1])) {
                    i++;
                    val = val * 10 + (arr[i] - '0');
                }
                numStack.push(val);
                if (preSign == '(') // 处理 ( 后面紧跟的数字
                    opsStack.push('+');
            } else if (c == ')') {
                int val = 0;
                char ops = opsStack.pop();
                while (ops != '(') {
                    if (ops == '+')
                        val += numStack.pop();
                    else
                        val -= numStack.pop();
                    ops = opsStack.pop();
                }
                numStack.push(val);
            } else {
                preSign = c;
                opsStack.push(c);
            }
        }
        int res = 0;
        while (!opsStack.isEmpty()) {
            char ops = opsStack.pop();
            if (ops == '+')
                res += numStack.pop();
            else
                res -= numStack.pop();
        }
        return numStack.isEmpty() ? res : res + numStack.pop();
    }
}