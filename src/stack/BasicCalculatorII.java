package stack;
// Source : https://leetcode.com/problems/basic-calculator-ii/
// Id     : 227,mst16.26
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/8
// Topic  : stack 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100% 23%

import java.util.ArrayList;
import java.util.Stack;

public class BasicCalculatorII {
    // 37 ms
    // 适应性有点差
    public int calculate(String s) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int val = c - '0';
                while (Character.isDigit(s.charAt(i + 1))) {
                    i++;
                    val = val * 10 + (s.charAt(i) - '0');
                }
                if (operatorStack.isEmpty() || operatorStack.peek() == '+' || operatorStack.peek() == '-')
                    operandStack.push(val);
                else {
                    char op = operatorStack.pop();
                    if (op == '*')
                        operandStack.push(val * operandStack.pop());
                    else
                        operandStack.push(operandStack.pop() / val);
                }
            } else if (c == ' ') {

            } else {
                operatorStack.push(c);
            }
            i++;
        }

        int res = 0;
        while (!operatorStack.isEmpty()) {
            char op = operatorStack.pop();
            int tmp = operandStack.pop();
            if (op == '+')
                res += tmp;
            else
                res -= tmp;
        }
        return res + operandStack.pop();
    }

    // 4ms
    // 用preSign preNum 起到栈的作用，用队列存储多出来的并且最后可以用累加计算的preNum，
    public int calculate2(String s) {
        int preNum = 0;
        char preSign = '+';
        int num = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = 0;
                for (; i < s.length() && Character.isDigit(s.charAt(i)); i++) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                i--;
            } else if (c == '-' || c == '+') { // 如果是 + - 优先级比较低的操作，可以把前面都算了
                preNum = operate(preNum, num, preSign);
                preSign = c;
            } else if (c == '/' || c == '*') {
                if (preSign == '/' || preSign == '*') {
                    preNum = operate(preNum, num, preSign);
                } else {
                    arr.add(preNum);
                    preNum = num;
                    if (preSign == '-') {
                        preNum = -num;
                    }
                }
                preSign = c;
            }
        }

        preNum = operate(preNum, num, preSign);
        for (int val : arr) {
            preNum += val;
        }
        return preNum;
    }

    private int operate(int a, int b, char op) {
        if (op == '-') {
            return a - b;
        }
        if (op == '+') {
            return a + b;
        }
        if (op == '/') {
            return a / b;
        }
        return a * b;
    }

    // 3ms
    // 数组实现栈，不用出栈直接做乘除操作
    public int calculate1(String s) {
//    public int calculate(String s) {
        int res = 0;
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] stack = new int[n];
        int top = -1;
        int num = 0;
        char operator = '+';
        for (int i = 0; i < n; ++i) {
            char c = cs[i];
            if ('0' <= c && c <= '9') {
                num = num * 10 + c - '0';
            }
            if ((c < '0' && c != ' ') || i == n - 1) {
                switch (operator) {
                    case '+':
                        stack[++top] = num;
                        break;
                    case '-':
                        stack[++top] = -num;
                        break;
                    case '*':
                        stack[top] *= num;
                        break;
                    default:
                        stack[top] /= num;
                }
                operator = c;
                num = 0;
            }
        }
        while (top != -1) {
            res += stack[top--];
        }
        return res;
    }
}