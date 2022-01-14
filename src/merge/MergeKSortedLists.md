[[T-merge]]

```java
// 2ms
public ListNode mergeKLists(ListNode[] lists) {
	if (lists.length == 0)
		return null;
	if (lists.length == 1)
		return lists[0];
	int len = lists.length;
	while (len > 1) {
		int i;
		for (i = 0; i < len / 2; i++) {
			lists[i] = mergeTwoLists(lists[i], lists[i + len / 2]);
		}
		if (len % 2 != 0) {
			lists[i] = lists[len - 1];
			len++;
		}
		len /= 2;
	}
	return lists[0];
}

private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	if (l1 == null)
		return l2;
	if (l2 == null)
		return l1;
	if (l1.val < l2.val) {
		l1.next = mergeTwoLists(l1.next, l2);
		return l1;
	} else {
		l2.next = mergeTwoLists(l2.next, l1);
		return l2;
	}

}
```

```java
public ListNode mergeKLists (ListNode[]lists){
	return mergesort(lists, 0, lists.length - 1);
}

public ListNode mergesort(ListNode[] lists, int start, int end) {
	if (start > end) {
		return null;
	}
	if (end == start) {
		return lists[start];
	}

	int mid = (start + end) / 2;
	ListNode l1 = mergesort(lists, start, mid);
	ListNode l2 = mergesort(lists, mid + 1, end);
	return mergeTwoLists(l1, l2);
}
private ListNode mergeTwoLists(ListNode l1,ListNode l2){
	if(l1==null)
		return l2;
	if(l2==null)
		return l1;
	if(l1.val<l2.val){
		l1.next = mergeTwoLists(l1.next,l2);
		return l1;
	}else{
		l2.next = mergeTwoLists(l2.next,l1);
		return l2;
	}

}
```