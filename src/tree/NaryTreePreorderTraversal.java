package tree;

// Source : https://leetcode.com/problems/n-ary-tree-preorder-traversal/
// Id     : 589
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-30
// Topic  : Tree
// Level  : Easy
// Other  :
// Tips   :
// Result : 100.00% 17.07%

import java.util.LinkedList;
import java.util.List;

public class NaryTreePreorderTraversal {

    // 100.00% 1ms 17.07%
    public List<Integer> preorder(Node root) {

        List<Integer> list = new LinkedList<>();
        pre(root, list);
        return list;
    }

    public void pre(Node root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        for (Node node : root.children)
            pre(node, list);
    }

//    public List<Integer> preorderIteratively(Node root) {
////        public List<Integer> preorder(Node root) {
//        List<Integer> list = new LinkedList<>();
//        if (root == null)
//            return null;
//        list.add(root.val);
//        while (!root.children.isEmpty()){
//            Node node = root.children.
//        }
//
//        pre(root, list);
//        return list;
//    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
