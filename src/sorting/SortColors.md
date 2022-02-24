# 双指针
[[A-twopointers]]
## 简化问题解法
- 把一种颜色和非一种颜色 作为两种类型，通过双指针交换，这时指定的颜色就在固定位置了
- 剩下的颜色重复上述过程

注意一个潜在问题在于，选取的颜色可能不存在，这时边界需要考虑清楚。
```java
public void sortColors(int[] nums) {
	int n = nums.length;
	int l = 0, r = n - 1;
	while (l < r) {
		if (nums[l] != 2) {
			l++;
			continue;
		}
		if (nums[r] == 2) {
			r--;
			continue;
		}
		swap(nums, l, r);
		l++;
		r--;
	}

	l = 0;
	// 处理 r 位于 2 的区间未移动的情况，此种情况一般是l一直移动到了r的位置，因此 r-- 即可解决
	if (nums[r] == 2) 
		r--;

	while (l < r) {
		if (nums[l] == 0) {
			l++;
			continue;
		}

		if (nums[r] == 1) {
			r--;
			continue;
		}

		swap(nums, l, r);
		l++;
		r--;
	}
}
```
## 循环不变量解法
循环不变式（loop invariant，或循环不变量、循环不变条件，也有译作循环不变性），是一组在循环体内、每次迭代均保持为真的性质（表达式）
简单说来，“循环不变式”是指在循环开始和循环中，每一次迭代时为真的性质。这意味着，一个正确的循环体，在循环结束时“循环不变式”和“循环终止条件”必须同时成立。

所谓不变量是人为根据需要解决的任务定义的。
- 变量以及区间的定义
- 变量的值是变化的，但是保持不变的性质
- 定义决定了初始化时变量的取值、循环的过程中操作的先后顺序、循环结束的条件。

```java
// 循环不变量是 r w b 对区间的定义
public void sortColors(int[] nums) {
	// 初始定义
	int r = 0,w = 0,b = nums.length - 1;
	while (w <= b) { // 循环结束条件
		if (nums[w] == 0) {
			swap(nums, r, w);
			r++;
			if (r >= w) {
				w++;
			}
		} else if (nums[w] == 1) {
			w++;
		} else if (nums[w] == 2) {
			swap(nums, b, w);
			b--;
		}
	}
}
```
