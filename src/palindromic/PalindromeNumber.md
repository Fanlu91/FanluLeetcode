[[T-palindromic]]

转换成 string 然后双指针遍历是最直观的解法。不过题目建议不要这么做。

此题利用回文的性质 - 回文反转还是自己 来解决问题，将int反转后于原来比较。

```java
//5ms
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

反转一半也可以，但意外的是在 leetcode 这些测试案例执行的时间看 效率并没有更高
```java
// 8ms
public boolean isPalindrome(int x) {
	if (x < 0)
		return false;
	if (x < 10)
		return true;
	if (x % 10 == 0) // 防止末尾为0的情况 123210  出现 123 123
		return false;
	int tmp = 0;
	while (tmp < x) {
		tmp = tmp * 10 + x % 10;
		x /= 10;
	}
	return x == tmp ? true : x == tmp / 10 ? true : false;
}
```


