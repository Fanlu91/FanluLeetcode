package linkedlist;
// Source : https://leetcode.com/problems/merge-two-sorted-lists/
// Id     : 21
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked List, Recursion
// Level  : Easy
// Other  :
// Tips   :
// Links  : Must
// Result : 100.00% 79.05%

public class MergeTwoSortedLists {
    // 100.00% 56.90%
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode sentinel = new ListNode(-1), cur = sentinel;
        while (true) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = l1;
                if (l1.next == null) { // if l1 is the last node
                    cur.next = l2;
                    break;
                }
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = l2;
                if (l2.next == null) {
                    cur.next = l1;
                    break;
                }
                l2 = l2.next;
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
        } else { // 这里可以省掉else，不过带上代码可读性更强
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // rewrite
    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

/*        ListNode sentinel = new ListNode(-1), cur = null;
        if (l1.val > l2.val) {
            cur = l2;
            l2 = l2.next;
        } else {
            cur = l1;
            l1 = l1.next;
        }
        sentinel.next = cur;*/

        // cur = sentinel 就替代了上面的代码
        ListNode sentinel = new ListNode(-1), cur = sentinel;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
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
