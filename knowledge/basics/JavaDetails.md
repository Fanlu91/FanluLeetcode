# 编程细节
## sting+ 与 StringBuilder
## 条件限定范围尽量精确
** `a!=b 与 a >b`**
a>b 是可以满足a!=b条件的，但如果期望的效果仅是排除 a - b 〉0 一种情况，建议使用a != b，这样效率的提升可能可以忽略不计，但是代码可读性会显著增强（不会让读代码的人的注意力被 a > b 的其他情况所分散） [[src/twopointers/ThreeSum]]


## Integer

求数字后面有几位0。 [[src/greedy/IPToCIDR]]

```java
// Long也支持此方法
Integer.numberOfTrailingZeros(number);
```