package linkedlist;

// Source : https://leetcode.com/problems/reverse-linked-list/
// Id     : 206
// Author : Fanlu Hai
// Date   : 2018-05-29
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 100.00% 99.87%

public class ReverseLinkedList {

    // 100.00% 99.87%
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        /**
         * 翻转有指向的两个Node很简单，把后一个指向前一个就行，是很简单的操作（下2）
         * 但是问题在于这样操作后后一个原本的指向丢失了，无法继续遍历链表，所以需要在操作前保存后一个原本的指针（下1）
         * 保存之后才能让指针继续前进（下3）
         */
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            // 1. save next pointer
            next = cur.next;
            // 2. reverse current node
            cur.next = pre;
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
