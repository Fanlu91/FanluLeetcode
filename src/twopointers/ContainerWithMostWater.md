#done
# 双指针
[[A-twopointers]]
首先应当能够写出的是下面的解法，从两边开始逐渐缩减范围，每次都比一把。
```java
public int maxArea(int[] height) {
	int res = 0, left = 0, right = height.length - 1;
	while (left < right) {
		res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
		if (height[left] <= height[right])
			left++;
		else
			right--;
	}

	return res;
}
```
每次都比较，几乎相当于暴力搜索了，因此也不需要考虑可能移动漏的问题。

# 优化
但无意义的比较是浪费时间的，应该想办法减少比较次数。

抓住计算面积这个重点
- 向内移动时，其中一个乘数会变小，如果高度比外围的最低高度`h = Math.min(height[left], height[right]))` 还低，则没有比较的意义 -- 两个乘数都缩小了。
- 即，当前的height[x] 大于 h 时不要移动/移动到这儿后停下来

具体到代码层面就是
```java
while (height[left] <= h)
	left++;
while (height[right] <= h) {
	right--;
}
```

把这个优化点变成具体实现引入上面的解法：

```java
public int maxArea(int[] height) {
	int res = 0, h = 0, left = 0, right = height.length - 1;
	while (left < right) {
		h = Math.min(height[left], height[right]);
		res = Math.max(res, (right - left) * h);
		while (height[left] <= h && left < right)
			left++;
		while (height[right] <= h && left < right) {
			right--;
		}
	}
	return res;
}
```

