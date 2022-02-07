[[T-palindromic]]

很经典

[[ReverseLinkedList]] + [[MiddleOfTheLinkedList]]

```java
public boolean isPalindrome(ListNode head) {
	ListNode fast = head, slow = head;
	while (fast != null) {
		fast = fast.next;
		slow = slow.next;
		if (fast != null) 
			fast = fast.next;
	}
	ListNode newHead = null, cur = slow, tmp = null;
	while (cur != null) {
		tmp = cur.next;
		cur.next = newHead;

		newHead = cur;
		cur = tmp;
	}

	ListNode p1 = head, p2 = newHead;
	while (p2 != null) {
		// System.out.println(p1.val + " "+ p2.val);
		if (p1.val != p2.val)
			return false;
		p1 = p1.next;
		p2 = p2.next;
	}
	return true;
}
```
