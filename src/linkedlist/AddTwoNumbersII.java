package linkedlist;

import java.util.Stack;

// Source : https://leetcode.com/problems/add-two-numbers-ii/
// Id     : 445
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-04
// Topic  : Linked list
// Level  : Medium
// Other  :
// Tips   :
// Result : 99.96% 74.91%
public class AddTwoNumbersII {

    // 70.60% 80.13%
    public ListNode addTwoNumbersOrigin(ListNode l1, ListNode l2) {
        // below code did not save time but consumed more space

        Stack<Integer> s1 = new Stack();
        Stack<Integer> s2 = new Stack();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode node = new ListNode(0);
        int sum = 0;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            if (!s1.isEmpty()) {
                sum += s1.pop();
            }
            if (!s2.isEmpty()) {
                sum += s2.pop();
            }

            node.val = sum % 10;
            // sum equals carry bit
            sum /= 10;
            ListNode newHead = new ListNode(sum);
            newHead.next = node;
            node = newHead;

        }

        return node.val == 0 ? node.next : node;
    }

    // learned from leetcode submission
    // use reversed linkedlist instead of stack
    // 99.96% 74.91%
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // create two new reversed list
        ListNode pre1 = new ListNode(l1.val);
        ListNode pre2 = new ListNode(l2.val);

        ListNode cur = l1.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre1;
            pre1 = cur;
            cur = next;
        }

        cur = l2.next;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre2;
            pre2 = cur;
            cur = next;
        }

        ListNode node = new ListNode(0);
        int sum = 0;

        while (pre1 != null || pre2 != null) {
            if (pre1 != null) {
                sum += pre1.val;
                pre1 = pre1.next;
            }
            if (pre2 != null) {
                sum += pre2.val;
                pre2 = pre2.next;
            }

            node.val = sum % 10;
            ListNode newHead = new ListNode(sum / 10);
            newHead.next = node;
            node = newHead;
            sum /= 10;
        }
        return node.val == 0 ? node.next : node;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
