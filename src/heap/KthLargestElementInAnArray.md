# 小顶堆
[[D-heap]]

```java
public int findKthLargest(int[] nums, int k) {
	if (nums.length == 1)
		return nums[0];
	if (k == 1) {
		int res = nums[0];
		for (int i = 1; i < nums.length; i++) 
			res = Math.max(res, nums[i]);
		return res;
	}

	// 建立k个元素的小顶堆 i 2*i+1 2*i+2 (i+1)/2
	// 自下而上堆化
	heapify(nums, k);
	// 将nums中剩余元素插入堆中
	for (int i = k; i < nums.length; i++) {
		// 自上而下堆化
		if (nums[i] > nums[0]) {
			nums[0] = nums[i];
			heapify(nums, k);
		}
	}

	// 返回堆顶元素
	return nums[0];
}

private void heapify(int[] nums, int k) {
	for (int i = k / 2 - 1; i >= 0; i--) {
		int smaller = -1;
		if (2 * i + 1 == k - 1) { // 只有左子树的情况
			smaller = 2 * i + 1;
		} else {
			smaller = nums[2 * i + 1] < nums[2 * i + 2] ? 2 * i + 1 : 2 * i + 2;
		}
		if (nums[i] > nums[smaller])
			swap(nums, i, smaller);
		// System.out.println(i + " " + Arrays.toString(nums));
	}
}


private void swap(int[] nums, int a, int b) {
	int tmp = nums[a];
	nums[a] = nums[b];
	nums[b] = tmp;
}
```

# 快排partition
[[A-sorting#快速排序]]
