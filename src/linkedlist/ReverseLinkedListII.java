package linkedlist;

// Source : https://leetcode.com/problems/reverse-linked-list-ii/
// Id     : 92
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-31
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 100.00% 100.00%

public class ReverseLinkedListII {

    // 100.00% 100.00%
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // skip if there are less than two nodes of nothing to reverse.
        if (head == null || head.next == null || n == m) {
            return head;
        }

        //  keep track of the start of the list
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        //  keep track of the node and its next node where reverse starts in order to link afterward
        ListNode preReverseNode = preHead, firstReverseNode;
        for (int i = 1; i < m; i++) {
            preReverseNode = preReverseNode.next;
        }

        ListNode cur, pre = null, next = null;
        cur = preReverseNode.next;
        firstReverseNode = cur;

        // start to reverse
        // in order to do that between x nodes, we need to change "next pointer" x +1 times.
        n = n - m + 1;
        for (int i = 0; i < n; i++) {
            // 1. save next pointer
            next = cur.next;
            // 2. reverse current node
            cur.next = pre;
            // 3. advance current and prev
            pre = cur;
            cur = next;
        }

        // preReverseNode is the node before reversed nodes
        // firstReverseNode is the first node reversed which means it is the tail of the reversed link
        //
        // after reversing using the function above
        // pre points to the last node reversed which means it is the head of the reversed link
        // next points to the node after reversed link
        preReverseNode.next = pre;
        firstReverseNode.next = next;

        return preHead.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
