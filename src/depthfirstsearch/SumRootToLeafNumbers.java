package depthfirstsearch;
// Source : https://leetcode.com/problems/sum-root-to-leaf-numbers/
// Id     : 129
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/17
// Topic  : Depth-First Search
// Level  : Medium
// Other  :
// Tips   :
// Links  : 257
// Result : 100.00% 87.92%

import java.util.LinkedList;
import java.util.List;

public class SumRootToLeafNumbers {
    List<String> list = new LinkedList<>();

    // 6.22% 14ms 5.06%
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        dfs("", root);
        int sum = 0;
        for (String s : list) {
            System.out.println(s);
            sum += Integer.valueOf(s);
        }
        return sum;
    }

    public void dfs(String prefix, TreeNode node) {

        prefix += node.val;
        if (node.left == null && node.right == null) {
            list.add(prefix);
            return;
        }
        if (node.left == null)
            dfs(prefix, node.right);
        else if (node.right == null)
            dfs(prefix, node.left);
        else {
            dfs(prefix, node.right);
            dfs(prefix, node.left);
        }

    }


    int sum = 0;

    public int sumNumbers1(TreeNode root) {
//    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    public void dfs(TreeNode root, int curSum) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            sum += curSum * 10 + root.val;
            return;
        }
        dfs(root.left, curSum * 10 + root.val);
        dfs(root.right, curSum * 10 + root.val);
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
