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


    // practice
    public ListNode reverseKGroup1(ListNode head, int k) {
//    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel, p2 = sentinel;
        int count = 0;
        while (p2.next != null) {
            p2 = p2.next;
            count++;
            if (count == k) {
                p2 = reverseGroup1(p1, p2.next);
                p1 = p2;
                count = 0;
            }
        }
        return sentinel.next;
    }

    // sentinel & end nodes are not in the group
    // return tail to be the next sentinel
    private ListNode reverseGroup1(ListNode sentinel, ListNode end) {
        // System.out.println(sentinel.val+" "+end.val);
        ListNode newHead = end, cur = sentinel.next, tmp = null, tail = cur;
        while (cur != end) {
            tmp = cur.next;
            cur.next = newHead;

            newHead = cur;
            cur = tmp;
        }
        sentinel.next = newHead;
        return tail;
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