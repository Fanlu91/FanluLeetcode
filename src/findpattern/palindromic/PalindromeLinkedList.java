package findpattern.palindromic;
// Source : https://leetcode.com/problems/palindrome-linked-list/
// Id     : 234
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/7
// Topic  : findpattern.palindromic 
// Level  : Easy+
// Other  :
// Tips   :
// Links  :
// Result : 99.87% 7.54%

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
            if (fast != null)
                fast = fast.next;
        }
        ListNode newHead = null, cur = slow, tmp = null;
        while (cur != null) {
            tmp = cur.next;
            cur.next = newHead;

            newHead = cur;
            cur = tmp;
        }

        ListNode p1 = head, p2 = newHead;
        while (p2 != null) {
            // System.out.println(p1.val + " "+ p2.val);
            if (p1.val != p2.val)
                return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}