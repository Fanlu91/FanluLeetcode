这道题目最关键的点不在于具体的算法，在于寻找`(`和`)`组合的规律[[T-findpattern]] 。

我一开始受定式思维用了stack去存符号，效率很低实现也比较复杂。

其实有更简单和巧妙的方式来判断符号的前后合法性：
> `(`和`)`逐渐增加时，前面出现的`(`的数量不少于`)`就符合语法

基于这个规律，我们比较容易结合[[A-backtracking(dfs)]] 回溯实现第一种解法

# 1 回溯法

`(`和`)`逐渐增加时，前面出现的`(`的数量不少于`)`就符合语法，在代码中实现就是回溯过程中通过下面的语句判断是否跳出
```java
if (l > r) {
	return;
}
```
其中`l`表示可用即可以继续追加的`(`的个数，`r`表示可用的`)`个数。

其他逻辑并不复杂，属于非常典型的回溯模版，读一读代码应该可以理解含义。


```java
// 0ms
public List<String> generateParenthesis(int n) {
	List<String> res = new LinkedList<>();
	backtracking(n, n, res, new StringBuilder());
	return res;
}

private void backtracking(int l, int r, List<String> res, StringBuilder stringBuilder) {
	if (l == 0 && r == 0) {
		res.add(stringBuilder.toString());
		return;
	}

	if (l > r) {
		return;
	}

	if (l > 0) {
		stringBuilder.append('(');
		backtracking(l - 1, r, res, stringBuilder);
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
	}
	if (r > 0) {
		stringBuilder.append(')');
		backtracking(l, r - 1, res, stringBuilder);
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
	}
}
```


# 2 动态规划
[[A-dynamicprogramming]]
上面看似简单的规律说实话实际并不容易想到，当然可以找的规律也并不只有这一个，放括号是否能让人略微联系到一步或两步爬楼梯的感觉？
我们把已经存在的结果暂且当作一个字符串 Sn，我们要在 Sn 中插入 `(`和`)`有多少种情况？显然结果取决于括号对 Sn 的拆分，假设拆分成 a，b两段，可能的结果是
`()ab`,`ab()`,`(a)b` ,`a(b)`和`(ab)`，这里我们把 a 和 b 进一步的根据题意去理解，它们也分别都是Sa和Sb，并且
- 并没有顺序之分
- 也可能为空
因此上面可能的结果其实只有一种：`a(b)`。
至此我们推导出了一个递推公示：`dp[n]="("+dp[a]+")"+dp[b]`

基于此可以实现动态规划解法，或者说递归算法：
```java
// 9ms
public List<String> generateParenthesis(int n) {
	List<String> res = new LinkedList<>();
	if (n == 0) {
		res.add("");
		return res;
	}
	for (int i = 0; i < n; i++) {
		int j = n - i - 1;
		List<String> iRes = generateParenthesis(i);
		List<String> jRes = generateParenthesis(j);
		for (String s1 : iRes) {
			for (String s2 : jRes) {
				res.add(s1 + "(" + s2 + ")");
			}
		}
	}
	return res;
}
```

上述解法有一些重复计算，引入“记忆”结构，得到下面的优化写法
```java
public List<String> generateParenthesis(int n) {
	Map<Integer, List<String>> mem = new HashMap<>();
	return dp(n, mem);
}

private List<String> dp(int n, Map<Integer, List<String>> mem) {
	if (mem.containsKey(n))
		return mem.get(n);
	List<String> res = new LinkedList<>();
	if (n == 0) {
		res.add("");
		return res;
	}
	for (int i = 0; i < n; i++) {
		int j = n - i - 1;
		List<String> iRes = dp(i, mem);
		List<String> jRes = dp(j, mem);
		for (String s1 : iRes) {
			for (String s2 : jRes) {
				res.add(s1 + "(" + s2 + ")");
			}
		}
	}
	mem.put(n, res);
	return res;
}
```