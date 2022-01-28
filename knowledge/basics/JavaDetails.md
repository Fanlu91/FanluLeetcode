# 编程细节
## string,StringBuilder,StringBuffer
[[LetterCombinationsOfAPhoneNumber]]
Objects of String are immutable, and objects of StringBuffer and StringBuilder are mutable.

StringBuffer and StringBuilder are similar, but StringBuilder is **faster** and preferred over StringBuffer for the single-threaded program. If **thread safety** is needed, then StringBuffer is used.
- 两者提供的方法几乎是一样的，常用的是append(String)和deleteCharAt(index)等.

## 条件限定范围尽量精确
** `a!=b 与 a >b`**
a>b 是可以满足a!=b条件的，但如果期望的效果仅是排除 a - b 〉0 一种情况，建议使用a != b，这样效率的提升可能可以忽略不计，但是代码可读性会显著增强（不会让读代码的人的注意力被 a > b 的其他情况所分散） [[ThreeSum]]


# Integer

求数字后面有几位0。 [[IPToCIDR]]

```java
// Long也支持此方法
Integer.numberOfTrailingZeros(number);
```


# swtich

switch 有一些不符合直觉的设计
1. 必须主动break
不加 break，则会从这个位置的位置向下去执行，
- 执行剩下所有的语句（包括其他case里面的），直到最后或者遇见break为止。

2. default 并不是else，更像是finally
switch没有else 可以用

还有一些需要注意的点：

switch不能以null为条件，因此如果需要处理null的情况，应该在语句之前去判断处理。

[[ValidParentheses]]


# 字符字母
```java
// ⼤写转⼩写
// s.toLowerCase()
private char toLower(char c) {
	if (c >= 'a' && c <= 'z') return c;
	if (c >= '0' && c <= '9') return c;
//⼤写A~Z 65~90，⼩写a~z 97~122
	return (char)((int)c+32);
}


// 判断是不是数字或字⺟
// Character.isLetterOrDigit(c);
private boolean isAlpha(char c) {
	if (c >= 'a' && c <= 'z') return true;
	if (c >= 'A' && c <= 'Z') return true;
	if (c >= '0' && c <= '9') return true;
	return false;
}
```