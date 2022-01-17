

# 穷举解法
[[A-greedy]]
穷举过程读一读代码应该可以看明白，作为简单题没有太多绕的地方。

唯一自己可能不容易想到的有两个技巧：

## 1 主动在末尾增加字符避免溢出

```java
s += 0;
```
这样就省去了 `s.charAt(i + 1)` 取值时对 i 是否越界的判断。

## 2 先处理特殊情况的好处

先处理 `C`,`X`,`I` 这三个可能表示特殊情况（4、9）的字符，处理完之后，其实 MDLV 是可以直接免去特殊情况判断无脑累积的。

这样做降低了一定的代码复杂度。

## 具体实现
```java
// 3ms
public int romanToInt(String s) {
	int res = 0;
	// nice one
	s += 0;
	for (int i = 0; i < s.length(); i++) {
		if (s.charAt(i) == 'C') {
			if (s.charAt(i + 1) == 'D') {
				res += 400;
				i++;
			} else if (s.charAt(i + 1) == 'M') {
				res += 900;
				i++;
			} else {
				res += 100;
			}
		} else if (s.charAt(i) == 'X') {
			if (s.charAt(i + 1) == 'L') {
				res += 40;
				i++;
			} else if (s.charAt(i + 1) == 'C') {
				res += 90;
				i++;
			} else {
				res += 10;
			}
		} else if (s.charAt(i) == 'I') {
			if (s.charAt(i + 1) == 'V') {
				res += 4;
				i++;
			} else if (s.charAt(i + 1) == 'X') {
				res += 9;
				i++;
			} else {
				res += 1;
			}

		} else if (s.charAt(i) == 'M')
			res += 1000;
		else if (s.charAt(i) == 'D')
			res += 500;
		else if (s.charAt(i) == 'L')
			res += 50;
		else if (s.charAt(i) == 'V')
			res += 5;
	}
	return res;
}
```

# 数学法
[[T-math]]
如果对罗马计数法比较熟悉，其实可以利用一个判断来解决所有的特殊情况：
相比阿拉伯计数，虽然数字总体也是从大到小排列，但是可能出现前一位小于当前位的情况：即当前一位数 pre 小于 当前位数时，它们两个组合在一起的值是当前数减去前一位的数。

把上面的思想转化为代码，就是下面的实现方式：
```java
public int romanToInt(String s) {
	int sum = 0;
	int pre = getValue(s.charAt(0));
	for (int i = 1; i < s.length(); i++) {
		int num = getValue(s.charAt(i));
		if (pre < num) {
			sum -= pre;
		} else {
			sum += pre;
		}
		pre = num;
	}
	sum += pre;
	return sum;
}

private int getValue(char ch) {
	switch (ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
	}
}
```