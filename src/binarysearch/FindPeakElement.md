
```java
 //0ms
public int findPeakElement(int[] nums) {
	// peak means out of order
	// nums[-1] = nums[n] = -∞
	// 当前比右边小，那么肯定在右边会有峰顶，最差情况走到头，也是一个峰顶；
	// 如果当前比左边小，那么肯定在左边会有峰顶，最差情况一路走到头，也是一个峰顶。
	int l = 0, r = nums.length - 1;
	while (l < r) {
		int mid = l + (r - l) / 2;
		if (nums[mid] < nums[mid + 1]) // nums[i] != nums[i + 1] for all valid i.
			l = mid + 1;
		else // mid 更大的时候 mid 自己可能是一个结果
			r = mid;
	}
	return l;
}

// 不改变二分的原本框架，针对特殊情况单独处理的解法
public int findPeakElement1(int[] nums) {
//    public int findPeakElement(int[] nums) {
	int n = nums.length, l = 0, r = n - 1;
	while (l <= r) {
		int mid = (l + r) / 2;
		if (l == r) //不添加这⼀⾏，测试⽤例[1]报错
			return mid;
		if (mid == 0) {
			if (nums[mid] > nums[mid + 1])
				return mid;
			else
				l = mid + 1;
		} else if (mid == n - 1) {
			if (nums[mid] > nums[mid - 1])
				return mid;
			else
				r = mid - 1;
		} else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
			return mid;
		} else if (nums[mid] < nums[mid + 1]) {
			l = mid + 1;
		} else {
			r = mid - 1;
		}
	}
	return -1;
}
```