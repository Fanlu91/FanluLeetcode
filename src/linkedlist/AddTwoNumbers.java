package linkedlist;

// Source : https://leetcode.com/problems/add-two-numbers/
// Id     : 2
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-03
// Topic  : Linked list
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 94.51% 90.73%
public class AddTwoNumbers {

    // 2 ms
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode sentinel = new ListNode(-1);
        ListNode cur = sentinel, pre = null;
        while (l1 != null && l2 != null) {
            int tmp = l1.val + l2.val + carry;
            carry = tmp > 9 ? 1 : 0;
            cur.next = new ListNode(tmp % 10);

            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (l1 == null)
            cur.next = l2;
        else
            cur.next = l1;

        pre = cur;
        cur = cur.next;

        while (carry == 1 && cur != null) {
            if (cur.val == 9) {
                cur.val = 0;
                carry = 1;
                pre = cur;
                cur = cur.next;
            } else {
                cur.val = cur.val + 1;
                carry = 0;
            }
        }

        if (carry == 1)
            pre.next = new ListNode(1);

        return sentinel.next;
    }

    // 2ms
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode cur = result, pre = new ListNode();
        boolean carry = false;

        while (l1 != null || l2 != null || carry) {
            int val = 0;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            if (carry) {
                val++;
                carry = false;
            }
            if (val > 9) {
                val = val % 10;
                carry = true;
            }
            cur.val = val;
            pre = cur;
            ListNode next = new ListNode();
            cur.next = next;
            cur = cur.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        return result;
    }

    // practice
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(), cur = sentinel;
        int carry = 0;
        while (true) {
            if (l1 == null || l2 == null) {
                if (carry == 0) {
                    if (l1 == null) {
                        cur.next = l2;
                        break;
                    }
                    if (l2 == null) {
                        cur.next = l1;
                        break;
                    }
                } else {
                    if (l1 == null && l2 == null) {
                        cur.next = new ListNode(1);
                        break;
                    } else {
                        ListNode tmp = l1 == null ? l2 : l1; // l1 or l2
                        int val = tmp.val + carry;
                        if (val > 9) {
                            carry = 1;
                            val = val % 10;
                        } else {
                            carry = 0;
                        }
                        cur.next = new ListNode(val);
                        cur = cur.next;
                        if (tmp == l1) {
                            l1 = l1.next;
                        } else {
                            l2 = l2.next;
                        }
                    }
                }
            } else {
                int val = l1.val + l2.val + carry;
                if (val > 9) {
                    carry = 1;
                    val = val % 10;
                } else {
                    carry = 0;
                }
                cur.next = new ListNode(val);
                cur = cur.next;
                l1 = l1.next;
                l2 = l2.next;
            }
        }
        return sentinel.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int x) {
            val = x;
        }
    }
}
