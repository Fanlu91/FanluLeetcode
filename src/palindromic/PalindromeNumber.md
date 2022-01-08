[[T-palindromic]]

转换成 string 然后双指针遍历是最直观的解法。不过题目建议不要这么做。

此题利用回文的性质 - 回文反转还是自己 来解决问题，将int反转后于原来比较。

```java
public boolean isPalindrome(int x) {
	if (x < 0)
		return false;
	int cur = 0;
	int num = x;
	while (num != 0) {
		cur = cur * 10 + num % 10;
		num /= 10;
	}
	return cur == x;
}
```

