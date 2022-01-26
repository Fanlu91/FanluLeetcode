[[T-math]]

一道真正必须用数学思想解的题，使用其他算法会TLE。

题目的返回值类型其实是一个提示，要求返回 int 数组。一般来说返回list更通用，数组创建时需要知道长度，而一开始不知道有几个解无法定义就很别扭，那么这道题找解之前，是不是应该先找找规律看是否能够计算出解数组的长度呢？
第二个提示是需要排序，同样的，解有可能是递归回来的、回溯回来的，让它有序并不合理。
感觉需要用数学知识。

数学思路听起来很可怕，这道题我们要怕吗？

把问题抽象一下，假设分别有 x 个 s 和 y 个 l，并且有 x + y = k，题目不就是让求所有`x*s + y*l`嘛。
这..化简一下`x*s +（k - x）*l`，再合并一下`(s - l)x + k*l`，这不就小学数学么...

再啰嗦一段
- 如果`s==l`，则只有一个解`k*l`
- 其他情况下，`0<=x<=k`，会有 k + 1 个不同的解

来我们用java解了它

```java
// 0ms
public int[] divingBoard(int shorter, int longer, int k) {
    if (k == 0)
        return new int[0];
    if (shorter == longer)
        return new int[]{longer * k};
    int[] res = new int[k + 1];

    for (int i = 0; i <= k; i++) {
        res[k - i] = k * longer + i * (shorter - longer);
    }
    return res;
}
```