[[T-findpattern]]
寻找规律类的题目。

找规律的方法除了画图，还应该考虑把可以量化的内容都列出来。

这道题目要求zig-zag分布数据，抽象一点来看，重点是多了纵向的数据分布，来看 0-10，3 这组数据纵向的分布情况：
```less
id    0 1 2 3 4 5 6 7 8 9 10  
index 0 1 2 3 2 1 0 1 2 3 2
```
数组的id和分布的层号index有较为明显的规律，即向下到最底、向上到顶后折返。

明白了上述规律，解这道题目就有眉目了。


# 纯 index 角度遍历
```java
// 14 ms
public String convert(String s, int numRows) {
	if (numRows >= s.length() || numRows == 1)
		return s;

	String[] stringRows = new String[numRows];
	for (int i = 0; i < numRows; i++) {
		stringRows[i] = "";
	}

	int index = 0;
	boolean downward = true;

	for (int i = 0; i < s.length(); i++) {
		stringRows[index] += s.charAt(i);

		if (index == numRows - 1)
			downward = false;
		if (index == 0)
			downward = true;

		if (downward)
			index++;
		else
			index--;
	}
	String ans = "";
	for (String row : stringRows)
		ans += row;
	return ans;
}
```

# index 与 id 结合
实际上 index 的值与 id 之间也有规律可循。

我们可以利用这种规律，一次性遍历出string，省去中间存储过程。

```java
public String convert(String s, int numRows) {
	if (numRows >= s.length() || numRows == 1)
		return s;

	StringBuilder sb = new StringBuilder();
	int n = s.length();
	int cycleLen = 2 * numRows - 2;

	for (int i = 0; i < numRows; i++) {
		for (int j = 0; j + i < n; j += cycleLen) {
			sb.append(s.charAt(j + i));
			if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
				sb.append(s.charAt(j + cycleLen - i));
			}
		}
	}
	return sb.toString();
}

```