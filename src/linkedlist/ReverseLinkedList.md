[[D-linkedlist]]

其实可以理解为是头部插入

# 基本翻转

首先把握好链表数据结构的三个关键指针 pre cur next。

**两个Node翻转这件事本身很简单，不要想复杂了**
- 把要反转的节点`cur`指向其前一个节点`pre`就行，是很简单的操作（下2）。至此**翻转已经完成**。

问题在于这样操作后，节点cure原本的指向cur.next会被覆盖，如果不存下来无法继续遍历链表，所以需要在操作前保存后一个原本的指针到next（下1），保存之后才能让指针继续前进并继续翻转（下3）。

```java
public ListNode reverseList(ListNode head) {
  if (head == null || head.next == null)
    return head;
  ListNode pre = null, cur = head, next;

  while (cur != null) {
    next = cur.next;    // 1. save next pointer
    cur.next = pre;     // 2. reverse current node
    // 3. advance current and prev
    pre = cur;
    cur = next;

  }
  // from the last iteration, pre pointed to current node which does not have a next,
  // it means pre is the new head
  return pre;
}
```
特别注意pre的位置，每次遍历，


#### 递归解

递归到链表的结尾处开始翻转。开始翻转时，可以认为当前节点cur之后的节点已经完成了翻转，需要做的是把cur.next节点的next指向cur，并把cur.next置空。

```java
public ListNode reverseListRecursive(ListNode cur) {
  if (cur == null || cur.next == null)
    return cur;

  ListNode newHead = reverseListRecursive(cur.next);
  //reverse current cur
  cur.next.next = cur;
  cur.next = null;
  return newHead;
}
```


java 反转链表的操作：

```
Collections.reverse(list);
```


