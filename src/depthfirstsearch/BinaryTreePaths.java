package depthfirstsearch;
// Source : https://leetcode.com/problems/binary-tree-paths/
// Id     : 257
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/17
// Topic  : Depth-First Search
// Level  : Easy
// Other  :
// Tips   :
// Links  :
// Result : 93.46% 37.08%

import java.util.LinkedList;
import java.util.List;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<>();
        helper(root, paths, "");
        return paths;
    }

    public void helper(TreeNode root, List<String> paths, String path) {
        if (root == null)
            return;
        path += String.valueOf(root.val); // 记录节点
        if (isLeaf(root)) { // 到达叶子节点，已经得到一条完整路径
            paths.add(path);
        } else {    // 没有到达叶子节点，继续递归构造路径
            path += "->";
            helper(root.left, paths, path);
            helper(root.right, paths, path);
        }
    }

    public boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
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
