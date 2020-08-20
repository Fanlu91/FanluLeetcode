package orderedmap;
// Source : https://leetcode.com/problems/data-stream-as-disjoint-intervals/
// Id     : 352
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020/8/13
// Topic  : Ordered Map
// Level  : Hard
// Other  :
// Tips   :
// Links  :
// Result : 75.24% 75.00%

import java.util.TreeMap;

public class DataStreamAsDisjointIntervals {

    private TreeMap<Integer, Node> treeMap;
    private Node dummy;
    private int count;

    /**
     * Initialize your data structure here.
     */
    public DataStreamAsDisjointIntervals() {
//    public SummaryRanges() {
        treeMap = new TreeMap<>();
        dummy = new Node(-2, -2, null);
        treeMap.put(-2, dummy); // key采用区间的右端点
        count = 0;
    }

    public void addNum(int val) {

        Node pre = treeMap.lowerEntry(val).getValue(); // 当前值前一个区间是什么
        Node cur = pre.next;
        if (cur != null && cur.left <= val) { // 如果前一个区间的下一个区间的左测小于当前值，则不插入
            return;
        }
        boolean isNull =
                cur == null;
        if (isNull) {
            cur = new Node(val + 2, val + 2, null); // 如果前一个区间没有next，用val+2 生成一个当前区间
        }

        if (pre.right + 1 == val) { // 如果前一个区间的右侧加一等于当前值，则
            if (val + 1 == cur.left) {
                treeMap.remove(pre.right);
                treeMap.remove(cur.right);
                pre.right = cur.right;
                pre.next = cur.next;
                treeMap.put(cur.right, pre);
                count--;
            } else {
                treeMap.remove(pre.right);
                pre.right++;
                treeMap.put(pre.right, pre);
            }
        } else if (val + 1 == cur.left) {
            cur.left--;
        } else {
            Node node = new Node(val, val, isNull ? null : cur);
            treeMap.put(val, node);
            count++;
            pre.next = node;
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[count][2];
        Node node = dummy.next;
        int id = 0;
        while (node != null) {
            res[id++] = new int[]{node.left, node.right};
            node = node.next;
        }
        return res;
    }

    private class Node {
        int left, right;
        Node next;

        public Node(int left, int right, Node next) {
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
}


