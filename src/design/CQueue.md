虽然不难，但是没有做过的情况下短时间想到解法的可能性不大。

入栈直接入，出栈时借助另外一个栈倒腾。
```java
//两个栈，一个出栈，一个入栈
private Stack<Integer> stack1, stack2;

public CQueue() {
	stack1 = new Stack<>();
	stack2 = new Stack<>();
}

public void appendTail(int value) {
	stack1.push(value);
}

public int deleteHead() {
	if (!stack2.isEmpty()) {
		return stack2.pop();
	} else {
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		return stack2.isEmpty() ? -1 : stack2.pop();
	}
}
```

也可以在每次入栈的时候直接借助另一个栈，倒腾元素顺序，出栈的时候直接出。