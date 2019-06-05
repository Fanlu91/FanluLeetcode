package linkedlist;

// Source : https://leetcode.com/problems/linked-list-cycle-ii/
// Id     : 142
// Author : Fanlu Hai
// Date   : 2018-05-29
// Topic  : Linked list
// Other  : this one should be a medium to my understanding.
// Tips   :
// Result : 96.51% 99.89%
public class PalindromeLinkedList {

    // this problem feels like it combines middle of the linked list/reverse linked list/ and part of cycle II together.
    // 96.51% 99.89%
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            // that's what leetcode says, so
            return true;
        if (head.next == null)
            return true;

        ListNode curr = head, pre = null, next = null;
        ListNode fast = head;

        // reverse half of the list

        while (fast != null) {

            // we split fast's double next in order to handle odd/even number situation
            fast = fast.next;
            if (fast == null) {
                // if the node has odd number, we need to move next ahead for once.
                next = curr.next;
                break;
            }
            fast = fast.next;

            // do the reverse
            next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }
        // count palindrome


        while (next != null) {
            if (pre == null || next.val != pre.val)
                return false;
            next = next.next;
            pre = pre.next;
        }
        return pre == null ? true : false;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

