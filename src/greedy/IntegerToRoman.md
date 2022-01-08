[[A-greedy]]

本题属于比较典型的贪心题目。如果题目一开始给的场景较为复杂，可以先尝试在一个更小的范围、或者更可控的条件下尝试去解决问题，然后进行思路延展。

# 1 贪心解法
## 简化场景

字符串拼接，首先定义`StringBuilder sb`。
如果说题目乍一看有点复杂，可以先尝试简化。3999有点复杂，先思考 10 以内或者说个位数如何处理找找思路应该OK？

个位数只需考虑 I V X这三个字母的组合，一共4种情况
1. 1 - 3 纯I组成
2. 4 特殊情况IV
3. 5- 8 V加I
4. 9 特殊情况IX

把上面的关系搞清楚后，其实代码逻辑并不复杂。
```java
if (num == 9) {
	sb.append("IX");
} else if (num == 4) {
	sb.append("IV");
} else {
	if (num >= 5) {
		sb.append("V");
		num -= 5;
	}
	while (num != 0) {
		sb.append("I");
		num--;
	}

}
```
其实到这里，这道题目你已经解开百分之六十了。

## 套路扩展
有了一点眉目，尝试把范围扩大到100，或者说再看看十位应该怎么处理。
稍加观察即可发现逻辑几乎一致。定义一个变量`tmp` 用于处理十位的情况，这时候`tmp`起到了上面`num`的作用

```java
if (num >= 10) {
	tmp = num / 10;
	if (tmp == 9) {
		sb.append("XC");
	} else if (tmp == 4) {
		sb.append("XL");
	} else {
		if (tmp > 4) {
			sb.append("L");
			tmp -= 5;
		}
		while (tmp > 0) {
			sb.append("X");
			tmp--;
		}
	}
	num = num % 10;
}
```

处理完十位的情况后，剩下的数字交给刚刚个位的代码去处理。

以此类推解决百位和千位，问题就迎刃而解了。

### 最终代码
```java
// 2ms
public String intToRoman(int num) {
	StringBuilder sb = new StringBuilder();
	int tmp = 0;
	if (num >= 1000) {
		tmp = num / 1000;
		num %= 1000;
		for (int i = 0; i < tmp; i++) {
			sb.append("M");
		}
	}

	if (num >= 100) {
		tmp = num / 100;
		num %= 100;
		if (tmp == 9) {
			sb.append("CM");
		} else if (tmp == 4) {
			sb.append("CD");
		} else {
			if (tmp > 4) {
				sb.append("D");
				tmp -= 5;
			}
			while (tmp > 0) {
				sb.append("C");
				tmp--;
			}
		}
	}

	if (num >= 10) {
		tmp = num / 10;
		if (tmp == 9) {
			sb.append("XC");
		} else if (tmp == 4) {
			sb.append("XL");
		} else {
			if (tmp > 4) {
				sb.append("L");
				tmp -= 5;
			}
			while (tmp > 0) {
				sb.append("X");
				tmp--;
			}
		}
		num = num % 10;
	}

	if (num == 9) {
		sb.append("IX");
	} else if (num == 4) {
		sb.append("IV");
	} else {
		if (num >= 5) {
			sb.append("V");
			num -= 5;
		}
		while (num != 0) {
			sb.append("I");
			num--;
		}

	}
	return sb.toString();
}
```

# 2 硬编码解法
```java
// 15ms
public String intToRoman(int num) {
	String[] M = {"", "M", "MM", "MMM"};
	String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
}
```
硬编码的方法十分巧妙，但是说实在的没什么好讲，代码完全是self-explanatory，自解释的。

代码很简洁，但是实际效率不如穷举方式。