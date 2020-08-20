package heap;

import binarytree.SameTree;

import java.util.PriorityQueue;
// Source : https://leetcode.com/problems/kth-largest-element-in-a-stream/
// Id     : 703
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Topic  : Heap
// Date   : 2019-05-05
// Other  :
// Tips   :
// Result : 99.55% 64.02%


public class KthLargestElementInAStream {
    /**
     * should implement priority queue manually;
     * The head of priority queue is the least element with respect to the specified ordering
     */
    /*private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int size;

    public KthLargestElementInAStream(int k, int[] nums) {
//  public KthLargest(int k, int[] nums) {
        this.size = k;
        for (int n : nums) {
            add(n);
        }
    }

    //99.55% 64.02%
    public int add(int val) {
        if (minHeap.size() < size)
            minHeap.offer(val);
        else if (minHeap.peek() < val) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }*/


    int k;
    TreeNode root;
    int count;
    int min = Integer.MAX_VALUE;

    public KthLargestElementInAStream(int k, int[] nums) {
//    public KthLargest(int k, int[] nums) {
        this.k = k;
        if (nums == null)
            return;
        for (int n : nums)
            addVal(n);
    }

    public void deleteMin() {
        if (root == null) {
            return;
        }
        count--;
        TreeNode cp = null;
        TreeNode c = root;
        for (; ; ) {
            if (c.left == null) {
                break;
            }
            cp = c;
            c = c.left;
        }
        if (cp != null) {
            cp.left = c.right;
            c.right = null;
            c = cp;
        } else {
            root = c.right;
            c = root;
        }
        if (c == null) {
            this.min = Integer.MAX_VALUE;
        } else {
            for (; ; ) {
                if (c.left == null) {
                    this.min = c.val;
                    break;
                }
                c = c.left;
            }
        }
    }

    public void addVal(int val) {
        if (count >= k) {
            if (val < min) {
                return;
            } else {
                while (count >= k) {
                    deleteMin();
                }
            }
        }
        // 添加
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode p = root;
            for (; ; ) {
                if (val <= p.val) {
                    if (p.left == null) {
                        p.left = newNode;
                        break;
                    }
                    p = p.left;
                } else {
                    if (p.right == null) {
                        p.right = newNode;
                        break;
                    }
                    p = p.right;
                }
            }
        }
        this.min = Math.min(min, val);
        count++;
    }

    public int add(int val) {
        addVal(val);
        return min;
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

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
