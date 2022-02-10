package stack;
// Source : https://leetcode.com/problems/basic-calculator-iii/
// Id     : 772
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/8
// Topic  : stack 
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 46.17% 20.92%

import java.util.*;

public class BasicCalculatorIII {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};

    // 5ms
    public int calculate(String s) {
        char[] arr = s.toCharArray();
        Deque<Integer> nums = new ArrayDeque<>();
        nums.addLast(0); // 为了防止第一个数为负数，先往 nums 加个 0
        Deque<Character> ops = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = arr[i];
            if (c == ' ')
                continue;
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else if (Character.isDigit(c)) {
                int val = c - '0';
                while (i + 1 < s.length() && Character.isDigit(arr[i + 1])) {
                    i++;
                    val = val * 10 + (arr[i] - '0');
                }
                nums.addLast(val);
            } else {
                if (i > 0 && (arr[i - 1] == '(')) { // (+ (- 情况处理
                    nums.addLast(0);
                }
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                while (!ops.isEmpty() && ops.peekLast() != '(') {
                    char preOps = ops.peekLast();
                    if (map.get(preOps) >= map.get(c)) {
                        calc(nums, ops);
                    } else {
                        break;
                    }
                }
                ops.addLast(c);
            }
        }

        // 将剩余的计算完
        while (!ops.isEmpty())
            calc(nums, ops);
        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2)
            return;
        if (ops.isEmpty())
            return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.addLast(ans);
    }


    // 7ms
    // using stack instead of deque
    public int calculate1(String s) {
//    public int calculate(String s) {
        // 将所有的空格去掉
        char[] arr = s.toCharArray();

        Stack<Integer> nums = new Stack<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.push(0);
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = arr[i];
            if (c == ' ')
                continue;
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peek() != '(') {
                        calc1(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }

            } else {
                if (Character.isDigit(c)) {
                    int val = c - '0';
                    while (i + 1 < s.length() && Character.isDigit(arr[i + 1])) {
                        i++;
                        val = val * 10 + (arr[i] - '0');
                    }
                    nums.push(val);
                } else {
                    if (i > 0 && (arr[i - 1] == '(' || arr[i - 1] == '+' || arr[i - 1] == '-')) {
                        nums.push(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        char prev = ops.peek();
                        if (map.get(prev) >= map.get(c)) {
                            calc1(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.push(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty())
            calc1(nums, ops);
        return nums.pop();
    }

    void calc1(Stack<Integer> nums, Stack<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2)
            return;
        if (ops.isEmpty())
            return;
        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.push(ans);
    }

}