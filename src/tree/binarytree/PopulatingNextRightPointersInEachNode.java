package tree.binarytree;
// Source : https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// Id     : 116
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/21
// Topic  : Binary Tree
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode {
    // 50.54% 2ms 6.67%
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
                    queue.add(cur.right);
                }
                sentinel.next = cur;
                sentinel = cur;
            }
        }
        return root;
    }


    /**
     * learned from submission
     *
     * use the next you just added as an advantage
     * @param root
     * @return
     */
    // 100% 0ms 100%
    public Node connect1(Node root) {
//    public Node connect(Node root) {
        if (root == null)
            return root;
        // Start with the root node. There are no next pointers
        // that need to be set up on the first level
        Node leftmost = root;
        // Once we reach the final level, we are done
        while (leftmost.left != null) {
            // Iterate the "linked list" starting from the head
            // node and using the next pointers, establish the
            // corresponding links for the next level
            Node head = leftmost;
            while (head != null) {
                // CONNECTION 1
                head.left.next = head.right;
                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                // Progress along the list (nodes on the current level)
                head = head.next;
            }
            // Move onto the next level
            leftmost = leftmost.left;
        }
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