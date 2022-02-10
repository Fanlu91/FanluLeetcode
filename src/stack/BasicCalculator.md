先有做 [[BasicCalculatorII]] 的基础，一些坑避免了，不过还是遇到一个问题
`(` 后面紧接的数字，可能有符号，比如‘-’，也可能没有，没有的话需要补充，否则（在我的算法中）会少加这个数字。
下面我的处理办法是补上+，也可以采取补一个数字0的方式。


```java
// 27ms
public int calculate(String s) {
	Stack<Character> opsStack = new Stack<>();
	Stack<Integer> numStack = new Stack<>();
	char[] arr = s.toCharArray();
	char preSign = '+';

	for (int i = 0; i < s.length(); i++) {
		char c = arr[i];
		if (c == ' ')
			continue;
		if (Character.isDigit(c)) {
			int val = c - '0';
			while (i + 1 < s.length() && Character.isDigit(arr[i + 1])) {
				i++;
				val = val * 10 + (arr[i] - '0');
			}
			numStack.push(val);
			if (preSign == '(') // 处理 ( 后面紧跟的数字
				opsStack.push('+');
		} else if (c == ')') {
			int val = 0;
			char ops = opsStack.pop();
			while (ops != '(') {
				if (ops == '+')
					val += numStack.pop();
				else
					val -= numStack.pop();
				ops = opsStack.pop();
			}
			numStack.push(val);
		} else {
			preSign = c;
			opsStack.push(c);
		}
	}
	int res = 0;
	while (!opsStack.isEmpty()) {
		char ops = opsStack.pop();
		if (ops == '+')
			res += numStack.pop();
		else
			res -= numStack.pop();
	}
	return numStack.isEmpty() ? res : res + numStack.pop();
}
```

