[[D-linkedlist]]


注意最后的处理，even.next = null；
```java
//0ms
public ListNode oddEvenList(ListNode head) {
	if (head == null || head.next == null)
		return head;
	boolean isOdd = true;
	ListNode odd = head, even = head.next;
	ListNode cur = head.next.next, evenHead = even;
	while (cur != null) {
		if (isOdd) {
			odd.next = cur;
			odd = odd.next;
			isOdd = false;
		} else {
			even.next = cur;
			even = even.next;
			isOdd = true;
		}
		cur = cur.next;
	}
	odd.next = evenHead; 
	even.next = null;

	return head;
}
```