package design;
// Source : https://leetcode.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
// Id     : Offer09
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/18
// Topic  : Queue, Stack
// Level  : Easy
// Other  :
// Tips   :
// Links  : Must
// Result : 69.99% 40.92%

import java.util.Stack;

/**
 * 用两个栈实现一个队列。
 * 队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

public class CQueue {
    //两个栈，一个出栈，一个入栈
    private Stack<Integer> stack1, stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.isEmpty() ? -1 : stack2.pop();
        }
    }
}
