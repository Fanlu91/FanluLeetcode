package binarysearchtree;
// Source : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// Id     : 235
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/22
// Topic  : Binary Search Tree
// Level  : Easy
// Other  :
// Tips   :
// Links  : 236
// Result : 99.85% 6.06%

public class LowestCommonAncestorOfABinarySearchTree {
    /**
     * p和q 分别在LCA的左右子树上，或其中有一个就是LCA本身
     * <p>
     * 上述关系在 BST 中可以得到
     * - LCA的值一定是大于等于其中一个，小于等于另外一个（符合这样条件的值不止一个，但是不一定具备亲缘关系）
     * - 通过亲缘关系自上而下的找，找到的第一个满足上述关系的点是LCA
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int rootVal = root.val, pVal = p.val, qVal = q.val;

        if (rootVal < pVal && rootVal < qVal)
            return lowestCommonAncestor(root.right, p, q);
        if (rootVal > qVal && rootVal > pVal)
            return lowestCommonAncestor(root.left, p, q);
        return root;
    }

    /**
     * 迭代方式
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val, qVal = q.val;
        // Start from the root node of the tree
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {
            // Value of ancestor/parent node.
            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                // If both p and q are greater than parent
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                // If both p and q are lesser than parent
                node = node.left;
            } else {
                // We have found the split point, i.e. the LCA node.
                return node;
            }
        }
        return null;
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