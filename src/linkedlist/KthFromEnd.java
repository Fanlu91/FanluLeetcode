package linkedlist;
// Source : https://leetcode.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
// Id     : Offer22
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2021/2/19
// Topic  : Double Pointers
// Level  : Easy
// Other  :
// Tips   :
// Links  : Must
// Result : 100.00% 85.78%

public class KthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode res = head, tail = head;
        while (k > 0) {
            tail = tail.next;
            k--;
        }
        while (tail != null) {
            tail = tail.next;
            res = res.next;
        }
        return res;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}