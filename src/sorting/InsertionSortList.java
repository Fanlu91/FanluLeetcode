package sorting;
// Source : https://leetcode.com/problems/insertion-sort-list/
// Id     : 147
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2022/2/18
// Topic  : sorting 
// Level  : Medium
// Other  :
// Tips   :
// Links  :
// Result : 99.20% 5.02%

public class InsertionSortList {

    // 2 ms
    public ListNode insertionSortList(ListNode head) {
        ListNode sentinel = new ListNode(Integer.MIN_VALUE, head);
        if (head.next == null)
            return head;
        ListNode pre = sentinel, cur = head;
        while (cur != null) {
            if (cur.val >= pre.val) {
                pre = cur;
                cur = cur.next;
            } else {
                ListNode next = cur.next;
                ListNode tmpPre = sentinel, tmpCur = sentinel.next;
                while (tmpCur.val <= cur.val) {
                    tmpCur = tmpCur.next;
                    tmpPre = tmpPre.next;
                }
                tmpPre.next = cur;
                cur.next = tmpCur;

                pre.next = next;
                cur = next;
            }
        }
        return sentinel.next;
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