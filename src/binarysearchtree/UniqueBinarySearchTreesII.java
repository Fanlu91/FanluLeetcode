package binarysearchtree;
// Source : https://leetcode.com/problems/unique-binary-search-trees-ii/
// Id     : 95
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/14
// Topic  : Binary Search Tree;Dynamic Programming
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 100.00% 99.41%

import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesII {

    // 39.37% 2 ms 82.76%
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<TreeNode>();
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> ans = new LinkedList<TreeNode>();
        if (start > end) {
            ans.add(null);
            return ans;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    ans.add(currTree);
                }
            }
        }
        return ans;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}