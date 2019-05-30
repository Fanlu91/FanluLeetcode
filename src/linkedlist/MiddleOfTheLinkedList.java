package linkedlist;
// Source : https://leetcode.com/problems/middle-of-the-linked-list/
// Id     : 876
// Author : Fanlu Hai
// Date   : 2018-05-29
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class MiddleOfTheLinkedList {

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null) {
            fast = fast.next;
            if (fast.next == null)
                // If there are two middle nodes, return the second middle node.
                return slow.next;
            else {
                fast = fast.next;
                slow = slow.next;
            }
        }

        return slow;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */