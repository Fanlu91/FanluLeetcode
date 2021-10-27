package linkedlist;

// Source : https://leetcode.com/problems/reverse-linked-list-ii/
// Id     : 92
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-31
// Topic  : Linked list
// Level  : Medium
// Other  : After 206
// Tips   :
// Result : 100.00% 100.00%

public class ReverseLinkedListII {

    // 100.00% 100.00%
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // skip if there are less than two nodes of nothing to reverse.
        if (head == null || head.next == null || right == left) {
            return head;
        }
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        //  keep track of the node and its next node where reverse starts in order to link afterward
        ListNode preReverseNode = sentinel, firstReverseNode;
        for (int i = 1; i < left; i++)
            preReverseNode = preReverseNode.next;

        ListNode cur, pre = null, next = null;
        cur = preReverseNode.next;
        firstReverseNode = cur;

        // start to reverse
        // in order to do that between x nodes, we need to change "next pointer" x +1 times.
        int n = right - left + 1;
        for (int i = 0; i < n; i++) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // preReverseNode is the node before reversed nodes
        // firstReverseNode is the first node reversed which means it is the tail of the reversed link
        preReverseNode.next = pre;
        firstReverseNode.next = next;

        return sentinel.next;
    }


    // 重新手写
    public ListNode reverseBetween1(ListNode head, int left, int right) {
//    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || right == left) {
            return head;
        }
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode PreReverse = sentinel;
        int size = right - left + 1;

        while (left != 1) {
            left--;
            PreReverse = PreReverse.next;
        }

        ListNode firstReverseNode = PreReverse.next;
        ListNode pre = null, cur = firstReverseNode, next = null;

        while (size > 0) {
            size--;
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        firstReverseNode.next = next;
        PreReverse.next = pre;

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
