#done
很好的递归练习题目[[A-recursion]]
```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	if (l1 == null)
		return l2;
	if (l2 == null)
		return l1;

	if (l1.val < l2.val) {
		l1.next = mergeTwoLists(l1.next, l2);
		return l1;
	} else { // 这里可以省掉else，不过带上代码可读性更强
		l2.next = mergeTwoLists(l1, l2.next);
		return l2;
	}
}
```

非递归方式
```java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
	ListNode sentinel = new ListNode(), cur = sentinel;
	while (list1 != null && list2 != null) {
		if (list1.val > list2.val) {
			cur.next = list2;
			cur = list2;
			list2 = list2.next;
		} else {
			cur.next = list1;
			cur = list1;
			list1 = list1.next;
		}
	}
	if (list1 == null)
		cur.next = list2;
	if (list2 == null)
		cur.next = list1;
	return sentinel.next;
}

```