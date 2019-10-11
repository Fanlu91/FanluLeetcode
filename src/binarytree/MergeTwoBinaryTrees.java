package binarytree;

// Source : https://leetcode.com/problems/merge-two-binary-trees/
// Id     : 617
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-09-30
// Topic  : Binary Tree
// Level  : Easy
// Other  :
// Tips   :
// Result : 00.00% 17.28%

public class MergeTwoBinaryTrees {
    TreeNode root;

    //  69.01% 1ms 100.00%
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        root = new TreeNode(t1.val + t2.val);

        root.left = mergeTreesR(t1.left, t2.left);
        root.right = mergeTreesR(t1.right, t2.right);

        return root;
    }

    public TreeNode mergeTreesR(TreeNode t1, TreeNode t2) {

        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;

        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTreesR(t1.left, t2.left);
        node.right = mergeTreesR(t1.right, t2.right);
        return node;
    }


    /**
     * There's no need to new a root node, we can reuse node from either one of the original nodes.
     *
     * @param t1
     * @param t2
     * @return
     */
    // 100.00% 0ms 17.28%
    public TreeNode mergeTreesImprove(TreeNode t1, TreeNode t2) {
//    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
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
