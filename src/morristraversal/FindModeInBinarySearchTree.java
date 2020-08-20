package morristraversal;
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

    private TreeNode pre = null;
    private int[] ans;
    private int retCount = 0;
    private int maxCount = 0;
    private int currCount = 0;

    public int[] findMode(TreeNode root) {
        inOrder(root);
        pre = null;
        ans = new int[retCount];
        retCount = 0;
        currCount = 0;
        inOrder(root);
        return ans;
    }

    private void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        if (pre != null && pre.val == root.val)
            currCount++;
        else
            currCount = 1;
        if (currCount > maxCount) {
            maxCount = currCount;
            retCount = 1;
        } else if (currCount == maxCount) {
            if (ans != null)
                ans[retCount] = root.val;
            retCount++;
        }
        pre = root;
        inOrder(root.right);
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
