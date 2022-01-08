[[A-math]]

唯一的难点是溢出的处理
```java
if ((tmp - tail) / 10 != result) {
	return 0;
}
```

```java
public int reverse(int x) {
	int result = 0, tmp = 0, tail = 0;
	while (x != 0) {
		tail = x % 10;
		tmp = result * 10 + tail;
		// if overflow return 0
		if ((tmp - tail) / 10 != result) {
			return 0;
		}
		result = tmp;
		x /= 10;
	}
	return result;
}
```