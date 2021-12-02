package twopointers;
// Source : https://leetcode.com/problems/linked-list-cycle-ii/
// Id     : 142
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Two Pointers
// Level  : Medium
// Other  : After 141
// Tips   :
// Result : 100.00% 5.04%

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII {

    // 100.00% 0ms 5.04% 35.5 MB
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                // by now, fast has run twice the distance than slow
                // the distance consist :
                // a = head to the start point of cycle
                // b = start to current point
                // c = extra distance fast run
                // a + b + c= 2 (a+b)

                // lets assume d = c - b, which represents the extra distance fast run till the start of cycle
                // we have a + b + c= a+ b + c + d = 2(a+b)
                // then d = a
                //
                // so below is the head runs a and slow runs d, they will meet at the start of the cycle.
                while (head != slow) {
                    slow = slow.next;
                    head = head.next;
                }
                return head;
            }
        }
        return null;
    }

    // 21.47% 5 ms 13.15% 35.3 MB
    public ListNode detectCycleUsingSet(ListNode head) {
        Set<ListNode> set = new HashSet<ListNode>();
        while (head != null) {
            if (!set.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
