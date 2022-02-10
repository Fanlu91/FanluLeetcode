package stack;
// Source : https://leetcode.com/problems/implement-stack-using-queues/
// Id     : 225
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/11
// Topic  : Stack, Queue
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}