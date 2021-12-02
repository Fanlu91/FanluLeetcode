package twopointers;

import java.util.HashSet;
import java.util.Set;

// Source : https://leetcode.com/problems/linked-list-cycle/
// Id     : 141
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Two Pointers
// Level  : Easy
// Other  : Must
// Tips   :
// Result : 100.00%  99.88%
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node.next != null) {
            node = node.next;
            if (set.contains(node))
                return true;
            else
                set.add(node);
        }
        return false;
    }

    /**
     * 快指针每次移动两格，慢指针每次移动一格
     *
     * 感觉上似乎会有"恰好"被跳过的可能，实际上不会
     * 假设快慢都是移动一格，他们是相对静止的，现在快只是多移动一格，相当于快在一格一格的"追赶"慢指针
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
//    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
