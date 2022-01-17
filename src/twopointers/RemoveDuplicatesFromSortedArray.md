[[A-twopointers]]
```java
public int removeDuplicates(int[] nums) {
	if (nums.length == 0)
		return 0;
	int right = 1, left = 0;
	for (; right < nums.length; right++) {
		if (nums[left] != nums[right]) {
			nums[++left] = nums[right];
		}
	}
	return left + 1;
}
```