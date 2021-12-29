#must

# hashtable
此题最直接的思路是使用hashtable，无脑解就可以，没有练习价值。

# two pointer
使用[[A-twopointers]] 会高效一些。此题设定只有一个解，因此双指针实现非常简单。

```java
public int[] twoSum1(int[] nums, int target) {
	int left = 0, right = nums.length - 1;
	while (left < right) {
		if (nums[left] + nums[right] == target)
			return new int[]{left + 1, right + 1};
		else if (nums[left] + nums[right] < target)
			left++;
		else
			right--;
	}
	return null;
}
```


其实这里有一个点，相比指针的双向移动，这里两指针都只会朝着一个方向去移动，会不会不会漏掉某些情况呢，即会不会移过了？
这点区别是非常关键的，不仅大大简化了实现，同时也因为[[T-缩减搜索空间]]而显著提升了效率。
这里的关键在于从有序的数组两边向中间搜索，即从两个极限（nums[left] ，nums[right]）向内逼近
- 假设其中一个点是nums[left]，这时求和比target还大，要知道left已经是当前能取的最小值了，因此right显然应当左移
- 针对nums[right]也一样，可以放心的让left右移扩大


# 二分查找
看到有序数组，第一时间也应该想到[[A-binarysearch]]，区别在于可以获得对数时间的复杂度。

```java
public int[] twoSum(int[] nums, int target) {
	int start = 0, end = nums.length - 1;
	while (true) {
		int mid = start + (end - start) / 2;
		if (nums[start] + nums[end] == target) {
			return new int[]{start + 1, end + 1};
		} else if (nums[start] + nums[end] < target) {
			start = nums[mid] + nums[end] < target ? mid : start + 1;
		} else {
			end = nums[mid] + nums[start] > target ? mid : end - 1;
		}
	}
}
```