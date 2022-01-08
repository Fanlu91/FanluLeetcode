

## java int

### int取值范围 -2147483648 到  2147483647
In binary, -2147483648 is 10000000000000000000000000000000 and it's the smallest  number that will fit in 32 bits when using the "two's complement" notation

In binary, 2147483647 is 01111111111111111111111111111111 and it's the biggest positive number that will fit in 32 bits when using the "two's complement" notation

## 转换
```java

Character.isDigit(c);
int value = Character.getNumericValue(c);

```

## 溢出判断

[[Atoi#判断溢出]]
```java
int value = Character.getNumericValue(s.charAt(index));
// 处理溢出问题
if (res > (Integer.MAX_VALUE - value) / 10) {
	return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
}
```

[[ReverseInteger]]
```java
if ((tmp - tail) / 10 != result) {
	return 0;
}
```

