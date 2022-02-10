任何题目先考虑暴力是否能够解决，暴力之后再考虑一些特别的技巧或者说套路。

# 暴力
```java
// 1303 ms
public int[] dailyTemperatures(int[] temperatures) {
	int d = 1, n = temperatures.length;
	int[] res = new int[n];
	for (int i = 0; i < n; i++) {
		int a = temperatures[i];
		while ((i + d) < n) {
			if (temperatures[i + d] > a) {
				res[i] = d;
				break;
			}
			d++;
		}
		if (i + d == n)
			res[i] = 0;
		d = 1;
	}
	return res;
}
```

# 单调栈
#must
很精妙，把后面没有更大数字的坐标先压入栈中，遇到了更高的再处理。
```java
// 22ms
public int[] dailyTemperatures(int[] temperatures) {
	int n = temperatures.length;
	int[] res = new int[n];
	Deque<Integer> stack = new LinkedList<>();
	for (int i = 0; i < n; i++) {
		int a = temperatures[i];
		while (!stack.isEmpty() && a > temperatures[stack.peek()]) {
			int val = stack.pop();
			res[val] = i - val;
		}
		stack.push(i);
	}
	return res;
}
```