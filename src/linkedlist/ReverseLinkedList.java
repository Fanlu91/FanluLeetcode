package linkedlist;

// Source : https://leetcode.com/problems/reverse-linked-list/
// Id     : 206
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Double Pointers, Recursive
// Level  : Easy
// Other  :
// Tips   :
// Links  : Must
// Result : 100.00% 99.87%

public class ReverseLinkedList {

    // 100.00% 99.87%
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null, cur = head, next;

        while (cur != null) {
            next = cur.next;    // 1. save next pointer
            cur.next = pre;     // 2. reverse current node
            // 3. advance current and prev
            pre = cur;
            cur = next;

        }
        // from the last iteration, pre pointed to current node which does not have a next,
        // it means pre is the new head
        return pre;
    }

    // 100.00% 99.87%
    public ListNode reverseListRecursive(ListNode cur) {
        if (cur == null || cur.next == null)
            return cur;
        /**
         * 1. before current node reverse, make sure from current's next all nodes has been reversed already.
         * 2. if so, reverse current by point current's next node's next pointer to current
         * and make current point to none
         * 3. if current is somebody else's next, its pointer will be assigned during that process
         * otherwise current is the new tail.
         */
        ListNode newHead = reverseListRecursive(cur.next);
        //reverse current cur
        cur.next.next = cur;
        cur.next = null;
        return newHead;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
