package linkedlist;

// Source : https://leetcode.com/problems/remove-linked-list-elements/
// Id     : 203
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 99.37% 99.72%

public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;

        if (head.next == null) {
            return head.val == val ? null : head;
        }


        // use sentinel and its next as head
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode tmp = sentinel;

        while (tmp.next != null) {
            if (tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
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
