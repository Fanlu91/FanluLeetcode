package linkedlist;
// Source : https://leetcode.com/problems/intersection-of-two-linked-lists/
// Id     : 160
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked list
// Level  : Easy
// Other  :
// Tips   :
// Links  : Must
// Result : 100.00%  22.65%


import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {

    // if two string with intersect has different length,
    // the intersect node will not exist in the "extra part" compare with the other list,
    // because otherwise the rest won't have equal length which can't be true
    // So first we can trim them to the same size
    // Then it will be very easy since we only need to compare node at the same "index"
    // because otherwise rest won't have equal length which can't be true
    // 97.33%  53.55%
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA;
        ListNode pointerB = headB;
        int nodesCountA = 1;
        int nodesCountB = 1;

        // get size
        while (pointerA.next != null) {
            nodesCountA++;
            pointerA = pointerA.next;
        }
        while (pointerB.next != null) {
            nodesCountB++;
            pointerB = pointerB.next;
        }

        // trim list
        pointerA = headA;
        pointerB = headB;
        while (nodesCountA > nodesCountB) {
            pointerA = pointerA.next;
            nodesCountA--;
        }
        while (nodesCountB > nodesCountA) {
            pointerB = pointerB.next;
            nodesCountB--;
        }

        // start from the same index
        while (pointerA != pointerB) {
            pointerA = pointerA.next;
            pointerB = pointerB.next;
        }
        return pointerA;
    }

    // 7.26% 19.70%
    public ListNode getIntersectionNodeVeryPoor(ListNode headA, ListNode headB) {
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> checkListA = new HashSet<>();
        Set<ListNode> checkListB = new HashSet<>();

        while (headA != null || headB != null) {
            if (headA != null) {
                if (checkListB.contains(headA))
                    return headA;
                else {
                    checkListA.add(headA);
                    headA = headA.next;
                }
            }
            if (headB != null) {
                if (checkListA.contains(headB))
                    return headB;
                else {
                    checkListB.add(headB);
                    headB = headB.next;
                }
            }
        }
        return null;
    }

    /**
     * 三段距离 a b e
     * a + e + b
     * =
     * b + e + a
     * 相遇的地方即相交
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            if (nodeA == null)
                nodeA = headB;
            else
                nodeA = nodeA.next;
            if (nodeB == null)
                nodeB = headA;
            else
                nodeB = nodeB.next;
        }
        return nodeA;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
