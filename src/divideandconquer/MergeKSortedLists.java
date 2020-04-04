package divideandconquer;

// Source : https://leetcode.com/problems/merge-k-sorted-lists/
// Id     : 23
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-01-01
// Topic  : Divide & Conquer
// Level  : Hard
// Other  :
// Tips   :
// Result : 84.07% 48.81%


public class MergeKSortedLists {
    /**
     * O(K * N)
     *
     * @param lists
     * @return
     */
    // 8.45% 347 ms 49.70%
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];

        ListNode ans = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    /**
     * O(N * logK)
     *
     * @param lists
     * @return
     */
    // 84.07% 3ms 48.81%
    public ListNode mergeKLists2(ListNode[] lists) {
//    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];

        int length = lists.length;

        while (length > 1) {
            int i = 0;
            for (; i < length / 2; i++) {
                lists[i] = mergeTwoLists(lists[i], lists[i + length / 2]);
            }
            if (length % 2 != 0) {
                lists[i] = lists[length - 1];
                length++;
            }
            length /= 2;
        }

        return lists[0];
    }

    // not much of a difference
    public ListNode mergeKLists3(ListNode[] lists) {
//        public ListNode mergeKLists (ListNode[]lists){
        return mergesort(lists, 0, lists.length - 1);
    }

    public ListNode mergesort(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (end == start) {
            return lists[start];
        }

        int mid = (start + end) / 2;
        ListNode l1 = mergesort(lists, start, mid);
        ListNode l2 = mergesort(lists, mid + 1, end);
        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }

        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
