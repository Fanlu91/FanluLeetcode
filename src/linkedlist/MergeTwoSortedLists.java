package linkedlist;
// Source : https://leetcode.com/problems/merge-two-sorted-lists/
// Id     : 21
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked List
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 79.05%

public class MergeTwoSortedLists {
    // 100.00% 56.90%
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
                if (l1.next != null) { // if l1 is the last node
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

    // 100.00% 0ms 79.05%
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }

        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
