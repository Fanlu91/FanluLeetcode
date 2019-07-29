package linkedlist;
// Source : https://leetcode.com/problems/merge-two-sorted-lists/
// Id     : 21
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 100.00% 56.90%

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode sentinel = new ListNode(-1);
        ListNode tmp = sentinel;
        while (true) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                tmp = tmp.next;
                if (l1.next != null) {
                    l1 = l1.next;
                } else {
                    tmp.next = l2;
                    break;
                }
            } else {
                tmp.next = l2;
                tmp = tmp.next;
                if (l2.next != null) {
                    l2 = l2.next;
                } else {
                    tmp.next = l1;
                    break;
                }
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
