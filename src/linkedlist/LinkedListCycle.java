package linkedlist;

// Source : https://leetcode.com/problems/linked-list-cycle/
// Id     : 141
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked list
// Other  :
// Tips   :
// Result :  100.00%  99.88%
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head.next == null)
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
