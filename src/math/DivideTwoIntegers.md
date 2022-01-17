
[[T-primitivetype]]
#detail 

# 硬循环

```java
// 2736 ms
public int divide(int dividend, int divisor) {
	if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE)
		return 1;
	if (dividend == 0 || divisor == Integer.MIN_VALUE)
		return 0;
	if (divisor == 1)
		return dividend;

	boolean negative = false;

	if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
		negative = true;
	int res = 0;
	divisor = Math.abs(divisor);

	if (dividend == Integer.MIN_VALUE) {
		dividend += Math.abs(divisor);
		res++;
	}
	dividend = Math.abs(dividend);

	while (dividend >= divisor) {
		dividend -= divisor;
		res++;
	}

	return negative ? -res : res;
}
```

# 倍增乘法
通过位运算提高结果逼近的效率。位运算实际效果类似2的乘除法，但本质上来说并不属于乘除法。
类似二分的算法思想。
```java
// 1 ms
public int divide(int dividend, int divisor) {
	if (dividend == Integer.MIN_VALUE && divisor == -1)
		return Integer.MAX_VALUE;
	long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
	int res = 0;
	for (int i = 31; i >= 0; i--) {
		if ((a >> i) >= b) {
			res += 1 << i;
			a -= b << i;
		}
	}
	return (dividend > 0) == (divisor > 0) ? res : -res;
}
```