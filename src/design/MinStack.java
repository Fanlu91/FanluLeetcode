package design;

// Source : https://leetcode.com/problems/min-stack/
// Id     : 155
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-06-13
// Topic  : Design
// Level  : Medium
// Other  :
// Tips   :
// Result : 78.13% 14.46%

import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty())
            minStack.push(x);
        else
            minStack.push(Math.min(getMin(), x));
    }

    public void pop() {
        if (stack.isEmpty())
            return;
        stack.pop();
        minStack.pop();
    }

    public int top() {
        if (stack.isEmpty())
            throw new RuntimeException("栈中元素为空，此操作非法");
        return stack.peek();
    }

    /**
     * 可以在每个元素 a 入栈时把当前栈的最小值 m 存储起来。
     * 在这之后无论何时，如果栈顶元素是 a，我们就可以直接返回存储的最小值 m
     *
     * @return
     */
    public int getMin() {
        if (stack.isEmpty())
            throw new RuntimeException("栈中元素为空，此操作非法");
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */