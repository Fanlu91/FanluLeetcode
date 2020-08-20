package binarytree;
// Source : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// Id     : 236
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-04-24
// Topic  : Binary Tree
// Other  :
// Tips   :
// Result : 100.00% 44.21%

import java.util.LinkedList;
import java.util.Queue;

public class LowestCommonAncestorOfABinaryTree {

    /**
     * lowest common ancestor (LCA):
     * The lowest common ancestor is defined between two nodes p and q
     * as the lowest node in T that has both p and q as descendants
     * where we allow a node to be a descendant of itself.
     */
    private TreeNode ans;

    // This answer is copied from leetcode submission analysis sample
    // 100.00% (20% faster than original)  44.21%
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        this.recurse(root, p, q);
        return this.ans;
    }

    private boolean recurse(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }

        // rec left
        int left = this.recurse(node.left, p, q) ? 1 : 0;
        int right = this.recurse(node.right, p, q) ? 1 : 0;

        int cur = (node == p) || (node == q) ? 1 : 0;

        // this is better than comparing objects
        if (cur + left + right == 2) { // 只有lowest common ancestor位置会出现
            this.ans = node;
        }
        return (cur + left + right > 0);
    }

    /**
     * 通过递归的方式在左右子树中寻找p和q
     * <p>
     * 基本的方式是通过
     * 1. 节点与p或q的直接比较 能够匹配则返回
     * 2. 若左右子树都能匹配（不为空），则返回当前根结点
     * <p>
     * 上面的方式 可能被返回的有3种节点 p、q、其共同祖先 c
     * p + null
     * q + null
     * c + null (p+q)
     * 一旦出现了c + null ，在本题的设定下意味着其后的递归过程不会有前两种组合出现。
     * <p>
     * 此解法利用了本题中许多特别的条件，比如只有两个点p和q
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    //65.83%  40.60%
    public TreeNode lowestCommonAncestorOriginal(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root)
            return null;
        if (root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestorOriginal(root.left, p, q);
        TreeNode right = lowestCommonAncestorOriginal(root.right, p, q);

        //return left == null ? right : right == null ? left : root;
        if (null != left && null != right) { // 左右都不为空则表示是祖先
            return root;
        } else if (null == right) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * below are some no very successful attempts
     */
    Queue<TreeNode> pNodeList = new LinkedList<>();
    Queue<TreeNode> qNodeList = new LinkedList<>();

    //! Time Limit Exceeded
    public TreeNode lowestCommonAncestorTooSlow(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = root;
        dfsNode(root, p, q, new LinkedList<TreeNode>());
        while (!pNodeList.isEmpty()) {
            TreeNode tmp = pNodeList.poll();
//            System.out.println(tmp);
            if (tmp != qNodeList.poll()) {
                break;
            }
            result = tmp;
        }
        return result;
    }

    public void dfsNode(TreeNode treeNode, TreeNode p, TreeNode q, Queue<TreeNode> queue) {
        if (null == treeNode)
            return;

        Queue<TreeNode> tmpQueue = new LinkedList<>();
        tmpQueue.addAll(queue);

        tmpQueue.add(treeNode);
        if (treeNode.val == p.val) {
            pNodeList = tmpQueue;
        }

        if (treeNode.val == q.val) {
            qNodeList = tmpQueue;
        }

        dfsNode(treeNode.left, p, q, tmpQueue);
        dfsNode(treeNode.right, p, q, tmpQueue);
    }


    Queue<Integer> pList = new LinkedList<>();
    Queue<Integer> qList = new LinkedList<>();


    // implemented the int version
    public int lowestCommonAncestorReturnInt(TreeNode root, TreeNode p, TreeNode q) {
        int result = root.val;
        dfsValue(root, p.val, q.val, new LinkedList<Integer>());
        while (!pList.isEmpty()) {
            int tmp = pList.poll();
//            System.out.println(tmp);
            if (tmp != qList.poll()) {
                break;
            }
            result = tmp;
        }
        return result;
    }

    public void dfsValue(TreeNode treeNode, int p, int q, Queue<Integer> queue) {
        if (null == treeNode)
            return;

        Queue<Integer> tmpQueue = new LinkedList<>();
        tmpQueue.addAll(queue);

        tmpQueue.add(treeNode.val);
        if (treeNode.val == p) {
            pList = tmpQueue;
        }

        if (treeNode.val == q) {
            qList = tmpQueue;
        }

        dfsValue(treeNode.left, p, q, tmpQueue);
        dfsValue(treeNode.right, p, q, tmpQueue);
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode5.right = treeNode3;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        LowestCommonAncestorOfABinaryTree lowestCommonAncestorOfABinaryTree = new LowestCommonAncestorOfABinaryTree();
        lowestCommonAncestorOfABinaryTree.dfsValue(treeNode1, 7, 6, new LinkedList<Integer>());

        for (int i : lowestCommonAncestorOfABinaryTree.pList) {
            System.out.print(i + "*");
        }
        System.out.println();
        for (int i : lowestCommonAncestorOfABinaryTree.qList) {
            System.out.print(i + "-");
        }
        System.out.println();

        System.out.println("result: " + lowestCommonAncestorOfABinaryTree.lowestCommonAncestorReturnInt(treeNode1, treeNode7, treeNode6));
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
