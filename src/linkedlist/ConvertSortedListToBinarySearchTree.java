package linkedlist;

// Source : https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
// Id     : 109
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-06-03
// Topic  : Linked list , Binary Tree
// Other  : a height-balanced binary tree is defined as a binary tree
//          in which the depth of the two subtrees of every node never differ by more than 1
// Tips   :
// Result : 97.35% 100.00%
public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);
        return sortedListToBST(head, null);
    }

    public TreeNode sortedListToBST(ListNode head, ListNode end) {

        // head == end means there's no sub tree at all.
        if (head == null || head == end)
            return null;
        if (head.next == null)
            return new TreeNode(head.val);

        ListNode mid = findMiddle(head, end);

        TreeNode root = new TreeNode(mid.val);
        System.out.println(root.val);

        root.left = sortedListToBST(head, mid);
        root.right = sortedListToBST(mid.next, end);
        return root;
    }

    public ListNode findMiddle(ListNode head, ListNode end) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head;
        if (end == null) {
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
        } else {
            while (fast != end && fast.next != end) {
                slow = slow.next;
                fast = fast.next.next;
            }
        }
        return slow;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}


