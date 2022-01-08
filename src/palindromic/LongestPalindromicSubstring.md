#todo

[[T-palindromic]]

# 扩展中心

## 模版
题目要求返回String，如何表示/记录这个结果呢，如果用String记录效率不高，设立一个模版，用left表示回文串开始的位置，len表示其长度。
-  结果是`s.substring(left, left + len)`


## 回文计算
利用回文的特点，通过扩展中心法计算。遇到较大的回文，更新记录 left 和 len 。

以 l 和 r 为中心（l可能等于r），向外扩展计算。
```java
private void checkPalindrome(int l, int r, String s) {
	int tmp = l == r ? -1 : 0;
	while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
		tmp += 2;
		l--;
		r++;
	}
	if (tmp > len) {
		len = tmp;
		left = l + 1;
	}
}
```


## 区分奇偶
遍历时，还需要考虑回文长度的奇偶[[T-parity]]，即区分aba和abba两种情况。

综合上述思路，可以得到下面的解法。

```java
int left, len;

// 17 ms
public String longestPalindrome(String s) {
	if (s.length() == 1)
		return s;
	left = 0;
	len = 0;
	for (int i = 0; i < s.length(); i++) {
		checkPalindrome(i, i, s);
		checkPalindrome(i, i + 1, s);
		if (i > s.length() / 2 + 1 && len >= s.length() / 2 + 1)
			break;
	}
	return s.substring(left, left + len);
}

private void checkPalindrome(int l, int r, String s) {
	int tmp = l == r ? -1 : 0;
	while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
		tmp += 2;
		l--;
		r++;
	}
	if (tmp > len) {
		len = tmp;
		left = l + 1;
	}
}
```


# 动态规划
上面的解法思路比较清晰，但是效率方面的缺陷也比较明显，即重复做了很多判断。使用数组将结果存下来，可以避免很多计算。

表格法 [[A-dynamicprogramming]]
`dp[l][r]`