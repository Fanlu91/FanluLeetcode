package linkedlist;

// Source : https://leetcode.com/problems/add-two-numbers/
// Id     : 2
// Author : Fanlu Hai
// Date   : 2018-06-03
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 94.51% 90.73%
public class AddTwoNumbers {

    // Time Limit Exceeded
    public ListNode addTwoNumbersTooSlow(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        boolean ifCarry = false;
        int bitSum = l1.val + l2.val;

        if (bitSum > 9) {
            bitSum -= 10;
            ifCarry = true;
        }

        ListNode head = new ListNode(bitSum);
        ListNode node = head;
        ListNode zero = new ListNode(0);

        while (l1.next != null || l2.next != null) {
            if (l1.next == null)
                l1.next = zero;

            if (l2.next == null)
                l2.next = zero;

            l1 = l1.next;
            l2 = l2.next;

            bitSum = ifCarry ? 1 + l1.val + l2.val : l1.val + l2.val;

            if (bitSum > 9) {
                bitSum -= 10;
                ifCarry = true;
            } else {
                ifCarry = false;
            }
            node.next = new ListNode(bitSum);
            node = node.next;
        }

        if (ifCarry)
            node.next = new ListNode(1);


        return head;
    }

    // 94.51% 90.73%
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, false);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, boolean isCarry) {

        if (isCarry) {
            if (l1 == null) {
                if (l2 == null) {
                    return new ListNode(1);
                } else {
                    if (l2.val != 9) {
                        l2.val = l2.val + 1;
                        isCarry = false;
                    } else {
                        isCarry = true;
                        l2.val = 0;
                        l2.next = addTwoNumbers(null, l2.next, isCarry);
                    }
                    return l2;
                }
            } else {
                if (l2 == null) {
                    if (l1.val != 9) {
                        l1.val = l1.val + 1;
                        isCarry = false;
                    } else {
                        isCarry = true;
                        l1.val = 0;
                        l1.next = addTwoNumbers(null, l1.next, isCarry);
                    }
                    return l1;
                } else {

                    int sum = l1.val + l2.val + 1;
                    if (sum > 9) {
                        isCarry = true;
                        sum -= 10;
                    } else {
                        isCarry = false;
                    }
                    ListNode node = new ListNode(sum);
                    node.next = addTwoNumbers(l1.next, l2.next, isCarry);
                    return node;
                }
            }
        } else {

            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            int sum = l1.val + l2.val;

            if (sum > 9) {
                isCarry = true;
                sum -= 10;
            } else {
                isCarry = false;
            }
            ListNode node = new ListNode(sum);
            node.next = addTwoNumbers(l1.next, l2.next, isCarry);

            return node;
        }
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
