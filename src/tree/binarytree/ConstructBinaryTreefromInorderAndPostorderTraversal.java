package tree.binarytree;
// Source : https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// Id     : 106
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/21
// Topic  : Binary Tree
// Level  : Medium
// Other  :
// Tips   :
// Links  : 105
// Result : 74.17% 61.90%

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreefromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0)
            return null;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return buildTree(inMap, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(Map<Integer, Integer> inMap, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        /**
         * 画出示意图
         * 注意 根结点 左子树/右子树 所在节点对应的的下标
         */
        int rootIndex = inMap.get(root.val);
        int nodesInLeft = rootIndex - inStart;

        root.left = buildTree(inMap, inStart, rootIndex - 1, postorder, postStart, postStart + nodesInLeft -1);
        root.right = buildTree(inMap, rootIndex + 1, inEnd, postorder, postStart + nodesInLeft -1 + 1, postEnd - 1);
        return root;
    }
}