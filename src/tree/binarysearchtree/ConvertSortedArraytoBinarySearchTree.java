package tree.binarysearchtree;

// Source : https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// Id     : 108
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Binary Search Tree
// Level  : Easy
// Other  :
// Tips   : 二叉搜索树的中序遍历刚好可以输出一个升序数组
// Result : 100.00% 5.06%

public class ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = null;
        return divide(nums, 0, nums.length - 1);
    }

    private TreeNode divide(int[] nums, int left, int right) {
        if (right < left)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = divide(nums, left, mid - 1);
        root.right = divide(nums, mid + 1, right);
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


