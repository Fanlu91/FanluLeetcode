package linkedlist;
// Source : https://leetcode.com/problems/odd-even-linked-list/
// Id     : 328
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/7
// Topic  : linkedlist 
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 8.12%

public class OddEvenLinkedList {
    //0ms
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        boolean isOdd = true;
        ListNode odd = head, even = head.next;
        ListNode cur = head.next.next, evenHead = even;
        while (cur != null) {
            if (isOdd) {
                odd.next = cur;
                odd = odd.next;
                isOdd = false;
            } else {
                even.next = cur;
                even = even.next;
                isOdd = true;
            }
            cur = cur.next;
        }
        odd.next = evenHead;
        even.next = null;

        return head;
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