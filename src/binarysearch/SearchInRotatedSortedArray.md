#must
典型的二分查找变体。

在不考虑重复值的情况下，实际上还是比较有规律可循

首先是需要熟练掌握二分查找的实现，即l、r、mid移动

然后次题的困难在于，数组被部分旋转导致原本的左右选取规则不一定适用。
- 不适用的原因在于不一定有序

[[T-findpattern]] 这里如果能够找到规律会思路清晰很多：
假设数组是升序的，基于上面的问题，我们取这样的数组上的一个元素mid，可以发现一条规律，**这个元素要么左侧是有序的，要么右侧是有序的**
并且我们可以通过比较 `nums[mid] 与 nums[l]`帮助判断
- `nums[mid]<nums[l]` 由于原本是升序的，因此可以得出左侧有序
- 同理若`nums[mid]>nums[l]` 说明左侧有颠倒的情况，右侧是有序的

利用上述规律：
我们在有序的一侧做判断，如果能够落入有序一侧则进一步深入判断，否则就继续在无序的一侧找

```java
public int search(int[] nums, int target) {
	int l = 0, r = nums.length - 1, mid = -1;
	while (l <= r) {
		mid = l + (r - l) / 2;
		if (nums[mid] == target)
			return mid;
		if (nums[l] <= nums[mid]) {//left side sorted
			if (target >= nums[l] && target < nums[mid]) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		} else {// rigth side sorted
			if (target > nums[mid] && target <= nums[r]) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
	}
	return -1;
}
```