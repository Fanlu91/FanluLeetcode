[[D-linkedlist]]

[[ReverseLinkedList]] 进阶，对于更深入的理解链表反转是很好的考验

最初的错误解
```java
输入
[1,2,3,4,5,6,7,8,9,10,11,12]
5
输出
[5,4,3,2,1,6,11,10,9,8,7,12]
预期结果
[5,4,3,2,1,10,9,8,7,6,11,12]


public ListNode reverseKGroup(ListNode head, int k) {
	if (head == null || head.next == null || k == 1)
		return head;
	ListNode sentinel = new ListNode(-1, head);
	ListNode start = sentinel, end = head;
	while (end != null) {
		int len = k;
		while (len > 0&&end!=null) {
			end = end.next;
			len--;
		}
		if (len == 0) {
			start.next = reverseGroup(start, end);
			start = end;
			end = end.next;
		}

	}
	return sentinel.next;
}

private ListNode reverseGroup(ListNode start, ListNode end) {
	
	ListNode pre=end, cur = start.next, next;

	while (cur != end) {
		next = cur.next;
		cur.next = pre;
		pre = cur;
		cur = next;
	}
	return pre;
}
```

调整后
```java
public ListNode reverseKGroup(ListNode head, int k) {
	if (head == null || head.next == null || k == 1)
		return head;
	ListNode sentinel = new ListNode(-1, head);
	ListNode start = sentinel, end = head;
	while (end != null) {
		int len = k;
		while (len > 0 && end != null) {
			end = end.next;
			len--;
		}
		if (len == 0) {
			ListNode tmp = start.next;
			start.next = reverseGroup(start, end);
			start = tmp;
		}

	}
	return sentinel.next;
}

private ListNode reverseGroup(ListNode start, ListNode end) {

	ListNode pre = end, cur = start.next, next;

	while (cur != end) {
		next = cur.next;
		cur.next = pre;
		pre = cur;
		cur = next;
	}
	return pre;
}
```