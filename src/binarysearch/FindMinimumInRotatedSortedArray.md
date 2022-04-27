#must
[[A-binarysearch]]

巩固对二分查找的理解。左右指针的移动很讲究。

```java
public int findMin(int[] nums) {
    int l = 0, r = nums.length - 1;

    while (l < r) {
        int m = l + (r - l) / 2;
        if (nums[m] > nums[r]) {
            l = m + 1;
        } else {
            r = m;
        }
    }
    return nums[l];
}
```

后续再次练习时采取了新的思路，参考[[SearchInRotatedSortedArray]]

```java
public int findMin(int[] nums) {
	int l = 0, r = nums.length - 1, mid = -1;
	while (l < r) {
		mid = l + (r - l) / 2;
		if (nums[mid] >= nums[l]) {// left side sorted 
			if (nums[l] < nums[r])
				return nums[l];
			else
				l = mid + 1;
		} else {// right side sorted
			r = mid;
		}
		// System.out.println(l+" "+r);
	}
	return nums[l];
}
```

注意上面的`nums[mid] >= nums[l]` 中的=。