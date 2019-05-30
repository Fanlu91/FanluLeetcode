package linkedlist;

// Source : https://leetcode.com/problems/design-linked-list/
// Id     : 707
// Author : Fanlu Hai
// Date   : 2018-05-29
// Topic  : Linked list
// Other  : This question has some bad test cases in my opinion.
// Tips   : I used the simplest singly linked list with a size attribute.
// Result : 80.06% 88.98%

public class MyLinkedList {

    private Node head;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0)
            return -1;
        if (index < size) {
            Node tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
            return tmp.val;
        }
        return -1;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node tmp = new Node(val);
        if (size == 0) {
            head = tmp;
            size++;
        } else {
            tmp.next = head;
            head = tmp;
            size++;
        }
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (size == 0)
            head = new Node(val);
        else {
            Node tmp = head;
            while (null != tmp.next) {
                tmp = tmp.next;
            }
            tmp.next = new Node(val);
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size)
            return;
        if (index == size) {
            addAtTail(val);
            return;
        }

        // this <= is confusing as some test case leetcode provides has negative index indicates to add at head.
        // I don't think it is appropriate but in order to ac that's what you have to do.
        if (index <= 0) {
            addAtHead(val);
        }

        Node tmp = head;
        for (int i = 0; i < index - 1; i++) {
            // get the node before index-th
            tmp = tmp.next;
        }
        Node newNode = new Node(val, tmp.next);
        tmp.next = newNode;
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (size == 0 || index >= size || index < 0)
            return;
        if (size == 1) {
            head = null;
            size = 0;
            return;
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node tmp = head;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            tmp.next = tmp.next.next;
        }

        size--;
    }

    class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public void printList() {
        Node node = head;
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();

        obj.addAtHead(100);
        obj.addAtTail(200);
        obj.addAtIndex(1, 101);
        int param_1 = obj.get(0);
        obj.printList();
        obj.deleteAtIndex(0);
        obj.printList();
    }

}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */