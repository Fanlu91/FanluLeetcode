### Two's complement
Two's complement is a mathematical operation on binary numbers, and is an example of a radix complement. It is used in computing as a method of signed number representation.



我们常说的补码，一般指二进制补码，是一种用二进制表示**有符号数**的方法。

- 补码是针对带符号数来说的，正数使用原码，而负数使用2的字长的指数减负数的绝对值表示
  - 获得一个数的补码只需要简单的取反再加上一
- 4bit 补码系统例子
  - 3的2进制表示 0011，补码0011
  - -3的补码是 - 0011取反得到1100加一得到1101
  - 3 -3 = 3+（-3） 0111+1101 = 0000



Radix —— 基数，在这里等价于“进制”

反码是Radix Complement 

补码是Diminished Radix Complement(缩小基数补码）

Radix Complement=Diminished Radix Complement + 1



#### 用途？

补码的用途很专一：**用加法操作来代替减法操作**

- 一种加法电路就可以处理各种有号数加法

- 减法可以用一个数加上另一个数的补码来表示
- n bit加法溢出时，忽略第n+1个比特



```java


```





### 相关java 语法

#### >> / >>> 

`>>` is arithmetic shift right, `>>>` is logical shift right.

In an arithmetic shift, the **sign bit is extended to preserve the signedness** of the number.

```java
>> 算数右移
>>> 逻辑右移
```



#### | 

```java
int n = 9999
n | 1 = ? 不管n多大结果为1 或者 0。常用来取数字的最低位。
```

