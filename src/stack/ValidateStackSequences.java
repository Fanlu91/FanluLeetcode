package stack;
// Source : https://leetcode.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
// Id     : 946,Offer31
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/9
// Topic  : stack 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 96.92% 10.81%

import java.util.Deque;
import java.util.LinkedList;

public class ValidateStackSequences {
    // 1ms
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int i = 0, j = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (j != n) {
            // System.out.println(i+" "+j);
            // 如果栈顶与出栈不一致，唯一可以做的就是push，如果不能push，则false
            if (stack.isEmpty() || stack.peek() != popped[j]) {
                if (i == n)// 不能再push
                    return false;
                stack.push(pushed[i]);
                i++;
            } else {// 因为数字唯一，栈顶与出栈一致，必须出栈
                stack.pop();
                j++;
            }
        }
        return true;
    }
}