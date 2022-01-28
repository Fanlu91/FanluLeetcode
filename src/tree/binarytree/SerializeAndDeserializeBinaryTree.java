package tree.binarytree;
// Source : https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// Id     : 297
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/7/24
// Topic  : Binary Tree
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 26.24% 33.57%

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {

    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }

    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
        List<String> list = new LinkedList<String>(Arrays.asList(array));
        return rdeserialize(list);
    }

    public TreeNode rdeserialize(List<String> list) {
        if (list.get(0).equals("None")) {
            list.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = rdeserialize(list);
        root.right = rdeserialize(list);

        return root;
    }


    public String serialize1(TreeNode root) {
//    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializePre(root, sb);
        return sb.toString();
    }

    void serializePre(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");
        serializePre(root.left, sb);
        serializePre(root.right, sb);
    }

    public TreeNode deserializePre(String data) {
        String[] values = data.split(",");
        List<String> list = new LinkedList<>(Arrays.asList(values));
        return deserializePreHelper(list);
    }

    private TreeNode deserializePreHelper(List<String> list) {
        if (list.get(0).equals("#")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        list.remove(0);
        root.left = deserializePreHelper(list);
        root.right = deserializePreHelper(list);
        return root;
    }


    public TreeNode deserialize1(String data) {
//    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        return deserializePre(data);
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