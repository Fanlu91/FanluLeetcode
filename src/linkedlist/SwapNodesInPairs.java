package linkedlist;
// Source : https://leetcode.com/problems/swap-nodes-in-pairs/
// Id     : 24
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/10/29
// Topic  : linkedlist
// Level  : Medium-
// Other  :
// Tips   :
// Links  :
// Result : 100% 55.56%

public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode sentinel = new ListNode(-1, head);
        ListNode pre = sentinel, cur = head, tmp = head;
        while (cur != null) {
            if (cur.next == null)
                return sentinel.next;
            pre.next = cur.next;
            tmp = cur.next;
            cur.next = cur.next.next;
            tmp.next = cur;

            pre = cur;
            cur = cur.next;
        }
        return sentinel.next;
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