package tree.binarytree;
// Source : https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
// Id     : 117
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/21
// Topic  : Binary Tree
// Level  : Medium
// Other  :
// Tips   :
// Links  : 117
// Result : 45.96% 50.00%

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNodeII {
    // 45.96% 2ms 50.00%
    public Node connect(Node root) {
        if (root == null)
            return null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node sentinel = new Node();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
                sentinel.next = cur;
                sentinel = cur;
            }
        }
        return root;
    }


    public Node connect1(Node root) {
//    public Node connect(Node root) {
        if (root == null)
            return null;
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                Node p = root.next;
                while (p != null && p.left == null && p.right == null) {
                    p = p.next;
                }
                if (p == null) {
                    root.left.next = null;
                } else {
                    root.left.next = (p.left != null) ? p.left : p.right;
                }
            }
        }

        if (root.right != null) {
            Node p = root.next;
            while (p != null && p.left == null && p.right == null) {
                p = p.next;
            }
            if (p == null) {
                root.right.next = null;
            } else {
                root.right.next = (p.left != null) ? p.left : p.right;
            }
        }

        connect(root.right);
        connect(root.left);

        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}