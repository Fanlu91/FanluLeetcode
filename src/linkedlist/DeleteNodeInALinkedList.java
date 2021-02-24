package linkedlist;
// Source : https://leetcode.com/problems/delete-node-in-a-linked-list/
// Id     : 237
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2019-05-29
// Topic  : Linked list
// Level  : Easy-
// Other  : This is certainly not the most exciting problem I guess. Learned something from it though.
// Tips   :
// Links  :
// Result : 100% 100%

public class DeleteNodeInALinkedList {
    //The usual way of deleting a node node from a linked list is to modify the next pointer of the node before it,
    // to point to the node after it.
    // Since we do not have access to the node before the one we want to delete,
    // we cannot modify the next pointer of that node in any way.
    // Instead, we have to replace the value of the node we want to delete with the value in the node after it,
    // and then delete the node after it.
    // the node we want to delete is not the tail of the list, we can guarantee that this approach is possible.
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
