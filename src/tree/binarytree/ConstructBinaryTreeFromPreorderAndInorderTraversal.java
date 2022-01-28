package tree.binarytree;
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
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);

    }

    TreeNode helper(int[] preorder, int preStart, int preEnd,
                    int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        /**
         * 画出示意图
         * 注意 根结点 左子树/右子树 所在节点对应的的下标
         */
        int inRootIndex = inMap.get(root.val);
        int numsLeft = inRootIndex - inStart;

        root.left = helper(preorder, preStart + 1, preStart + numsLeft,
                inorder, inStart, inRootIndex - 1, inMap);
        root.right = helper(preorder, preStart + numsLeft + 1, preEnd,
                inorder, inRootIndex + 1, inEnd, inMap);
        return root;
    }


    /**
     * learned from leetcode submission.
     *
     * 遍历preorder数组，当当前节点值等于中序遍历首字符时，返回。
     * （参考中序和前序的递归写法，中序在开始返回的第一个节点才开始记录）那么对于此节点，若其右节点不存在，中序的下一个值会是其父节点的值。
     *
     * 变量 pre 保存当前要构造的树的 root
     * 变量 in 保存 inorder 数组中可以成为 root 的数字们的开头那个
     *
     *
     * 每次递归调用，都会确定出一个停止点，它告诉子调用在哪里停止
     * 自己的根节点值作为左子树调用的停止点
     * 自己的（父调用给下来的）停止点作为右子树的停止点
     *
     *
     * @param preorder
     * @param inorder
     * @return
     */
    int pre = 0, in = 0;

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return recursive(preorder, inorder, Integer.MAX_VALUE);
    }

    public TreeNode recursive(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length)  // pre走到preorder末尾
            return null;
        if (inorder[in] == stop) {// in指针走到了停止点
            in++; // stop点废弃了，in推进一位
            return null;
        }
        int curVal = preorder[pre];
        pre++;
        TreeNode cur = new TreeNode(curVal);
        cur.left = recursive(preorder, inorder, curVal);  // 左子树的停止点是当前的根节点的值
        cur.right = recursive(preorder, inorder, stop);  // 右子树的停止点是当前树的停止点
        return cur;
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