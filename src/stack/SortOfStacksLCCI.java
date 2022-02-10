package stack;
// Source : https://leetcode.com/problems/sort-of-stacks-lcci/
// Id     : mst03.05
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/9
// Topic  : stack 
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result : 99.82% 10.49%

import java.util.Deque;
import java.util.LinkedList;

public class SortOfStacksLCCI {

    // 非惰性解法 160 ms
    // 惰性解法 12 ms
    Deque<Integer> stack = new LinkedList<>(), tmp = new LinkedList<>();


    // 非惰性解法 844 ms
    // 惰性解法 13 ms
//    Stack<Integer> stack = new Stack<>(), tmp = new Stack<>();

    public SortOfStacksLCCI() {
//    public SortedStack() {

    }

    /*public void push(int val) {
        if (isEmpty())
            stack.push(val);
        else {
            int top = stack.peek();
            while (top < val) {
                tmp.push(stack.pop());
                if (stack.isEmpty())
                    break;
                top = stack.peek();
            }
            stack.push(val);
            while (!tmp.isEmpty()) {
                stack.push(tmp.pop());
            }
        }

    }

    public void pop() {
        if (!stack.isEmpty())
            stack.pop();
    }

    public int peek() {
        if (stack.isEmpty())
            return -1;
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }*/


    /**
     * 惰性更新
     */
    public void push(int val) {
        int minBig = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
        int maxSmall = tmp.isEmpty() ? Integer.MIN_VALUE : tmp.peek();

        while (true) {
            if (val > minBig) {
                tmp.push(stack.pop());
                minBig = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
            } else if (val < maxSmall) {
                stack.push(tmp.pop());
                maxSmall = tmp.isEmpty() ? Integer.MIN_VALUE : tmp.peek();
            } else { // maxSmall<val<minBig
                stack.push(val);
                break;
            }
        }
    }

    public void pop() {
        //将辅助栈元素push回原始栈
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
        if (!stack.isEmpty())
            stack.pop();
    }

    public int peek() {
        //将辅助栈元素push回原始栈
        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }
        return stack.isEmpty() ? -1 : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty() && tmp.isEmpty();
    }

}