package linkedlist;
// Source : https://leetcode.com/problems/split-linked-list-in-parts/
// Id     : 725
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-04
// Topic  : Linked list
// Other  :
// Tips   :
// Result : 100.00%  100.00%

public class SplitLinkedListInParts {

    // 100.00%  100.00%
    public ListNode[] splitListToParts(ListNode root, int k) {

        if (root == null) {
            return new ListNode[k];
        }

        int length = getLength(root);
        int quotient = length / k;
        int remainder = length % k;

        ListNode[] array = new ListNode[k];
        ListNode tmp = root;
        ListNode pre = null;

        for (int i = 0; i < remainder; i++) {
            if (tmp == null) {
                array[i] = null;
            } else {
                array[i] = tmp;
                for (int j = 0; j < quotient + 1; j++) {
                    if (tmp == null)
                        break;
                    pre = tmp;
                    tmp = tmp.next;
                }
                pre.next = null;
            }
        }

        for (int i = remainder; i < k; i++) {
            if (tmp == null) {
                array[i] = null;
            } else {
                array[i] = tmp;
                for (int j = 0; j < quotient; j++) {
                    if (tmp == null)
                        break;
                    pre = tmp;
                    tmp = tmp.next;
                }
                pre.next = null;
            }
        }
        return array;

    }

    public int getLength(ListNode root) {
        int length = 1;
        while (root.next != null) {
            length++;
            root = root.next;
        }
        return length;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
