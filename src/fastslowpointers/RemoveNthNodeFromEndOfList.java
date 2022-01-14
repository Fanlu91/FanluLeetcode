package fastslowpointers;
// Source : https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// Id     : 19
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/3/2
// Topic  : Two Pointers
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 94.24%

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode n1 = head, n2 = head;
        for (int i = 0; i < n; i++) {
            n2 = n2.next;
        }
        while (n2 != null) {
            n1 = n1.next;
            n2 = n2.next;
        }
        if (n1.next != null)
            n1.next = n1.next.next;

        return sentinel.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}