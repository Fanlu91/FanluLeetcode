package linkedlist;
// Source : https://leetcode.com/problems/reverse-nodes-in-k-group/
// Id     : 25
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/10/29
// Topic  : linkedlist
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 37.77%

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;
        ListNode sentinel = new ListNode(-1, head);
        ListNode start = sentinel, end = head;
        while (end != null) {
            int len = k;
            while (len > 0 && end != null) {
                end = end.next;
                len--;
            }
            if (len == 0) {
                ListNode tmp = start.next;
                start.next = reverseGroup(start, end);
                start = tmp;
                // end = end.next;
            }

        }
        return sentinel.next;
    }

    private ListNode reverseGroup(ListNode start, ListNode end) {

        ListNode pre = end, cur = start.next, next;

        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
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