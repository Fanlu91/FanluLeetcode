package twopointers;

import java.util.HashSet;
import java.util.Set;

// Source : https://leetcode.com/problems/linked-list-cycle/
// Id     : 141
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Two Pointers
// Level  : Easy
// Other  :
// Tips   :
// Result :  100.00%  99.88%
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

    public boolean hasCycle1(ListNode head) {
//    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode fast = head, slow = head;
        while (fast.next != null) {
            fast = fast.next;
            if (fast == slow)
                return true;
            else {
                if (fast.next == null)
                    return false;
                fast = fast.next;
                slow = slow.next;
            }
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
