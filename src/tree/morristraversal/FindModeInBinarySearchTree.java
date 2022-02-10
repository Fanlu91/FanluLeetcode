package tree.morristraversal;
// Source : https://leetcode.com/problems/find-mode-in-binary-search-tree/
// Id     : 501
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/18
// Topic  : Morris Traversal
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 100% 100%

public class FindModeInBinarySearchTree {

//    public int[] findMode(TreeNode root) {
//
//    }

    public void morrisTraversal(TreeNode node) {
        while (node != null) {
            if (node.left == null) { // 完成了左子树的遍历
                // 则打印当前节点，然后进入右孩子。
                System.out.print(node.val);
                node = node.right;
            } else { // 未完成左子树的遍历
                TreeNode pre = getPredecessor(node); // 查找当前节点的前序节点
                if (pre.right == null) { //前序节点的右孩子是空
                    // 则把前序节点的右孩子指向当前节点 进入当前节点的左孩子
                    pre.right = node;
                    node = node.left;
                } else if (pre.right == node) { //前序节点的右孩子指向了它
                    // 则把前序节点的右孩子设置为空，打印当前节点，然后进入右孩子
                    pre.right = null;
                    System.out.print(node.val);
                    node = node.right;
                }
            }
        }
    }

    private TreeNode getPredecessor(TreeNode node) {
        TreeNode pre = node;
        if (node.left != null) {
            pre = pre.left;
            while (pre.right != null && pre.right != node) {
                pre = pre.right;
            }
        }
        return pre;
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
