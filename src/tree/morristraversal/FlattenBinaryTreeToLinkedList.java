package tree.morristraversal;
// Source : https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// Id     : 114
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/17
// Topic  : Morris Traversal
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100% 99.89%

import java.util.LinkedList;
import java.util.List;

public class FlattenBinaryTreeToLinkedList {

    // 11.34%  3ms 19.72%
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        List<TreeNode> list = dfs(root);
        for (int i = 0; i < list.size() - 1; i++) {
            TreeNode node = list.get(i);
            node.left = null;
            node.right = list.get(i + 1);
        }
        return;
    }

    public List<TreeNode> dfs(TreeNode root) {
        List<TreeNode> list = new LinkedList<>();
        list.add(root);
        if (root.left != null)
            list.addAll(dfs(root.left));
        if (root.right != null)
            list.addAll(dfs(root.right));
        return list;
    }


    // 100.00% 0ms 99.89%
    public void flatten1(TreeNode root) {
//    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);//左子树先展开为单链表
        flatten(root.right);//右子树再展开为单链表
        // 这时，左右子树已经是flatten的了。即两条右子树单链

        // 核心处理逻辑在这里
        TreeNode tmp = root.right;//暂存右子树
        root.right = root.left;//将左子树连接到根节点的右节点
        root.left = null;//将左节点置空
        while (root.right != null) {//将右子树的单链表连接上去
            root = root.right;
        }
        root.right = tmp;

    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
