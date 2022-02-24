package sorting;
// Source : https://leetcode.com/problems/sort-list/
// Id     : 148
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/18
// Topic  : sorting 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 99.32% 14.00%

public class SortList {

    // 冒泡 TLE
    public ListNode sortList1(ListNode head) {
//    public ListNode sortList(ListNode head) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        while (true) {
            boolean finished = true;
            ListNode pre = sentinel, cur = pre.next;
            while (cur != null && cur.next != null) {
                if (cur.val > cur.next.val) {
                    ListNode next = cur.next.next;
                    cur.next.next = cur;
                    pre.next = cur.next;
                    cur.next = next;
                    finished = false;
                }
                pre = cur;
                cur = cur.next;
            }
            if (finished)
                return sentinel.next;
        }
    }

    // merge sort
    // 13 ms
    public ListNode sortList(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        ListNode mid = findMidNode(head);
        ListNode rightHead = mid.next;
        mid.next = null;
        ListNode leftHead = sortList(head);
        rightHead = sortList(rightHead);
        return mergeList(leftHead, rightHead);
    }

    // 注意寻找中点要找偏前的位置，避免偏后位置向后切分，和没有找中点前一样。
    private ListNode findMidNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode mergeList(ListNode leftHead, ListNode rightHead) {
        if (leftHead == null)
            return rightHead;
        if (rightHead == null)
            return leftHead;
        if (leftHead.val > rightHead.val) {
            rightHead.next = mergeList(leftHead, rightHead.next);
            return rightHead;
        } else {
            leftHead.next = mergeList(leftHead.next, rightHead);
            return leftHead;
        }

    }

    // 5ms
    public ListNode sortList2(ListNode head) {
//    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode m = slow.next;
        slow.next = null;

        ListNode right = sortList(m);
        ListNode left = sortList(head);

        ListNode init = new ListNode();
        ListNode cur = init;
        while (right != null && left != null) {
            if (right.val <= left.val) {
                cur.next = right;
                right = right.next;
            } else {
                cur.next = left;
                left = left.next;
            }
            cur = cur.next;
        }
        cur.next = right == null ? left : right;
        return init.next;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}