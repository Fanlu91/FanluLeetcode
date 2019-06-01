package linkedlist;

// Source : https://leetcode.com/problems/remove-duplicates-from-sorted-list/
// Id     : 83
// Author : Fanlu Hai
// Date   : 2018-06-01
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 100.00% 99.97%

public class RemoveDuplicatesFromSortedList {

    // 34.92% 99.99%
    public ListNode deleteDuplicatesSlow(ListNode head) {
        ListNode node = head;
        while (node != null) {
            if (node.next == null)
                return head;
            else {
                if (node.val == node.next.val) {
                    node.next = node.next.next;
                } else {
                    node = node.next;
                }
            }
        }
        return head;
    }

    // 100.00% 99.97%
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;

        ListNode node = head;
        // keep commands as little as possible in side a while loop.
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

