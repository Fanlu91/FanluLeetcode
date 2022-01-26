#practice 
[[A-twopointers]]

简单题目绝对不能掉以轻心。

```java
public int removeDuplicates(int[] nums) {
	int res = 0;
	int i = 0;

	while (i < nums.length) {
		if (nums[res] != nums[i]) {
			// 最关键的思路在下面两行，先增加res，再移动
			res++;
			nums[res] = nums[i];
		}
		i++;
	}
	return res + 1;
}
```