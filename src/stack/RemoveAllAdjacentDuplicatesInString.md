```java
public String removeDuplicates(String s) {
	Deque<Character> stack = new LinkedList<>();
	char[] arr = s.toCharArray();
	for (int i = 0; i < s.length(); i++) {
		if (stack.isEmpty() || arr[i] != stack.peek())
			stack.push(arr[i]);
		else
			stack.pop();
	}
	StringBuilder sb = new StringBuilder();
	while (!stack.isEmpty())
		sb.append(stack.pollLast());
	return sb.toString();
}
```