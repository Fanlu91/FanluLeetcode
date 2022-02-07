从后向前可以降低后面计算时的复杂度。


这里 n 的应用非常巧妙。n 代表走到 最近 和最后一个位置接上的地方需要跳跃的步数。
从后向前遍历，默认移动 1 ，距离默认也为 1。
最关键的点：如果 nums[i] < n ，那么 nums[i - 1] > n + 1 才能接上，以此类推

遍历到 i=0 时
```java
public boolean canJump(int[] nums) {
	int n = 1;
	for (int i = nums.length - 2; i >= 0; i--) {
		if (nums[i] >= n) {
			n = 1;
		} else {
			n++;
		}
	}
	return n > 1 ? false : true;
}
```

下面也是很好的思路，从前向后
```java
public boolean canJump(int[] nums) {
	int reachedMax = 0;
	for (int i = 0; i < nums.length; ++i) {
		if (i > reachedMax)
			return false;
		if (i + nums[i] > reachedMax) {
			reachedMax = i + nums[i];
		}
		if (reachedMax >= nums.length - 1)
			return true;
	}
	return false;
}
```