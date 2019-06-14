package design;

// Source : https://leetcode.com/problems/lru-cache/
// Id     : 146
// Author : Fanlu Hai
// Date   : 2018-06-13
// Topic  : Design
// Level  : Medium
// Other  :
// Tips   : Use double sentinel if there is a tail also.
// Result : 91.27% 67.29%

import java.util.HashMap;

public class LRUCache {

    class Node {
        int key;
        int value;
        Node next;
        Node pre;

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int size, maxSize;
    HashMap<Integer, Node> map;


    /*
    Node sentinel, tail;
    // 31.87%  67 ms 69.05%
    public LRUCache(int capacity) {
        this.size = 0;
        this.maxSize = capacity;
        this.map = new HashMap<>();
        this.sentinel = new Node(-1, -1);
    }

    public int getOrigin(int key) {
        Node tmpNode;
        if (map.containsKey(key)) {
            tmpNode = map.get(key);
            if (tail != tmpNode) {
                tmpNode.pre.next = tmpNode.next;
                tmpNode.next.pre = tmpNode.pre;

                tail.next = tmpNode;
                tmpNode.pre = tail;
                tail = tmpNode;
            }
            return tmpNode.value;
        } else
            return -1;
    }

    public void putOrigin(int key, int value) {
        Node newNode = new Node(key, value);
        Node tmpNode;
        if (size == 0) {
            newNode.pre = sentinel;
            sentinel.next = newNode;
            tail = newNode;
            map.put(key, newNode);
            size = 1;
        } else if (size == maxSize) {
            if (map.containsKey(key)) {

                tmpNode = map.get(key);
                if (tail != tmpNode) {
                    tmpNode.pre.next = tmpNode.next;
                    tmpNode.next.pre = tmpNode.pre;

                    tail.next = newNode;
                    newNode.pre = tail;
                    tail = newNode;
                } else {
                    tail.pre.next = newNode;
                    newNode.pre = tail.pre;
                    tail = newNode;
                }

            } else {

                tmpNode = sentinel.next;
                if (tmpNode != tail) {
                    sentinel.next = tmpNode.next;
                    tmpNode.next.pre = sentinel;

                    tail.next = newNode;
                    newNode.pre = tail;
                    tail = newNode;
                } else {
                    sentinel.next = newNode;
                    newNode.pre = sentinel;
                    tail = newNode;
                }
                map.remove(tmpNode.key);

            }

            map.put(key, newNode);
        } else {
            if (map.containsKey(key)) {
                tmpNode = map.get(key);
                if (tmpNode != tail) {
                    tmpNode.pre.next = tmpNode.next;
                    tmpNode.next.pre = tmpNode.pre;

                    tail.next = newNode;
                    newNode.pre = tail;
                } else {
                    newNode.pre = tail.pre;
                    tail.pre.next = newNode;
                }
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                size++;
            }

            tail = newNode;
            map.put(key, newNode);
        }
    }*/

    // my original approach store the last visit at tail below used the opposite
    // it also used double sentinel, which is the main difference
    Node sentinelHead, sentinelTail;

    // 91.27% 59 ms 67.29%
    public LRUCache(int capacity) {

        this.size = 0;
        this.maxSize = capacity;
        this.map = new HashMap<>();
        sentinelHead = new Node();
        sentinelTail = new Node();
        sentinelHead.next = sentinelTail;
        sentinelTail.pre = sentinelHead;
    }

    public void addNode(Node node) {
        node.pre = sentinelHead;
        node.next = sentinelHead.next;

        sentinelHead.next.pre = node;
        sentinelHead.next = node;
    }

    public void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    public Node popTail() {
        Node res = sentinelTail.pre;
        removeNode(res);
        return res;
    }

    public int get(int key) {
        Node tmp = map.get(key);
        if (tmp == null)
            return -1;
        else {
            moveToHead(tmp);
        }
        return tmp.value;
    }

    public void put(int key, int value) {

        Node node = map.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNode(newNode);
            size++;
            if (size > maxSize) {
                Node tail = popTail();
                map.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    public static void main(String[] args) {
/*        LRUCache l = new LRUCache(1);
        l.put(2, 1);
        l.get(2);
        l.put(3,2);
        l.get(2);
        l.get(3);*/

        LRUCache l = new LRUCache(2);
        l.put(2, 1);
        l.put(2, 2);
        l.get(2);
        l.put(1, 1);
        l.put(4, 1);
        l.get(2);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
