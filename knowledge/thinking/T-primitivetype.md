
# java int

## 转换为int
```java

Character.isDigit(c);
int value = Character.getNumericValue(c);


```

## 溢出
### max & min

int取值范围 -2147483648 到  2147483647

In binary, -2147483648 is 10000000000000000000000000000000 and it's the smallest  number that will fit in 32 bits when using the "two's complement" notation

In binary, 2147483647 is 01111111111111111111111111111111 and it's the biggest positive number that will fit in 32 bits when using the "two's complement" notation

```java
System.out.println(Integer.MIN_VALUE);  
System.out.println(Integer.MAX_VALUE);  
System.out.println(Math.abs(Integer.MIN_VALUE));  
System.out.println(Math.abs(Integer.MIN_VALUE + 1));

-2147483648
2147483647
-2147483648
2147483647
```

注意 Math.abs(Integer.MIN_VALUE) 仍然为负！
> Note that if the argument is equal to the value of **Integer.MIN_VALUE**, the most negative representable int value, the result is that same value, which is **negative**.

### 溢出判断
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

