package binarytree;
// Source : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// Id     : 105
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/18
// Topic  : Binary Tree
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 77.57% 70.00%

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    // 77.57% 3ms 70.00%
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0)
            return null;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);

    }

    TreeNode helper(int[] preorder, int preStart, int preEnd,
                    int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inRootIndex = inMap.get(root.val);
        int numsLeft = inRootIndex - inStart;

        root.left = helper(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRootIndex - 1, inMap);
        root.right = helper(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRootIndex + 1, inEnd, inMap);
        return root;
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