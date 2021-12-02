//package linkedlist;
//// Source : https://leetcode.com/problems/swap-nodes-in-pairs/
//// Id     : 24
//// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
//// Date   : 2021/10/29
//// Topic  : linkedlist
//// Level  : Medium
//// Other  :
//// Tips   :
//// Links  :
//// Result :
//
//public class SwapNodesInPairs {
//
//    public ListNode swapPairs(ListNode head) {
//        if (head == null || head.next == null)
//            return head;
//        boolean evenNode = false;
//        ListNode sentinel = new ListNode();
//        sentinel.next = head;
//        ListNode pre = null, cur = head, next = null;
//        while (cur != null) {
//            if (evenNode){
//                next = cur.next;
//                cur.next = pre;
//                pre = cur;
//                cur = next;
//                evenNode = true;
//            }else {
//                next = cur.next;
//                pre = cur;
//                cur = next;
//            }
//        }
//        return sentinel.next;
//    }
//
//
//    class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode() {
//        }
//
//        ListNode(int val) {
//            this.val = val;
//        }
//
//        ListNode(int val, ListNode next) {
//            this.val = val;
//            this.next = next;
//        }
//    }
//
//}