#detail #todo

题目要求对数阶的时间复杂度，再加上是有序数据，因此必然要使用[[A-binarysearch]]。

根据中位数的定义，有两种思路：
1. 统计中，中位数被用来将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。使用二分查找确定两个有序数组的「分割线」，中位数就由**分割线左右**两侧的元素决定。
     - 分割线满足这样的性质：左右两边元素个数相等（这里忽略两个数组长度之和奇偶性的差异）；分割线左边所有元素小于等于分割线右边所有元素
2. 当 m+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2 个元素，当 m+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。因此，这道题可以转化成**寻找两个有序数组中的第 k 小的数**，其中 k 为 (m+n)/2 或 (m+n)/2+1

## 分割线思路
### 1 定义分割线

“中位数被用来将一个集合划分为两个长度相等的子集”，因此可以定义分割线，aMid 的左边和 bMid 的左边组合成「左半部分」，他们的右边分别组合成「右半部分」。
- 定义 `aMid = (0 + A.length) / 2`，奇数情况下aMid就是中位数，偶数时aMid 和 aMid-1构成中位数
- bMid 并不用二分计算，而是通过aMid的位置推导出来，`bMid = (A.length + B.length + 1) / 2 - aMid`。同样的，奇数情况下bMid就是中位数，偶数时bMid 和 bMid-1构成中位数
	- 为什么另一半是Mid -1而不是Mid + 1，主要因为运算时是先加1再整除


### 中位数定位
上述分割线定义简化了二分查找时的移动，通过二分移动aMid，bMid的位置也相应确定

移动时如何找到结束条件？相比一个数组时，二分查找时的条件判断会稍微复杂一些
```java 
B[bMid - 1] <= A[aMid] && A[aMid - 1] <= B[bMid]
```
通过上面的思路，我们已经可以定位到大概的位置了，剩下的就是具体的判断和一些特殊情况的处理。

先不考虑特殊情况，中位数的位置应该是
```java
left = Math.max(A[aMid - 1], B[bMid - 1]); // 两个中的较大值
right = Math.min(B[bMid], A[aMid]); // 两个中的较小值
```

### 移动到数组边缘的特殊情况
当分割移动到数组边缘时，会有有一些特殊情况：
1. `aMid==0`，表示 A 数组的值比较大，A分割左移到不能再左移，中位数的左侧应落在 B 数组中`left = B[bMid - 1];`
2. `bMid == 0` ，表示 B 数组的值比较大，B分割左移到不能再左移，中位数的左侧应落在 A 数组`left = A[aMid - 1];`
3. `aMid == A.length`，A分割右移到不能再右移，中位数的右侧应落在 B 数组中`right = B[bMid];
4. `bMid == B.length`，B分割右移到不能再右移，中位数的右侧应落在 A 数组中``right = A[bMid]`



```java
public double findMedianSortedArrays(int[] A, int[] B) {
	if (A.length > B.length) 
		return findMedianSortedArrays(B, A); // 保证 A.length <= B.length

	int aLeft = 0, aRight = A.length;
	while (aLeft <= aRight) {
		// aMid + bMid = (A.length + B.length + 1) / 2
		int aMid = (aLeft + aRight) / 2, bMid = (A.length + B.length + 1) / 2 - aMid;
		if (bMid != 0 && aMid != A.length && B[bMid - 1] > A[aMid]) { // aMid 需要增大，右移
			aLeft = aMid + 1;
		} else if (aMid != 0 && bMid != B.length && A[aMid - 1] > B[bMid]) { // aMid 需要减小，左移
			aRight = aMid - 1;
		} else { // 达到要求 B[bMid - 1] <= A[aMid] && A[aMid - 1] <= B[bMid]，将边界条件列出来单独考虑
			int left = 0;
			if (aMid == 0) {
				left = B[bMid - 1];
			} else if (bMid == 0) {
				left = A[aMid - 1];
			} else {
				left = Math.max(A[aMid - 1], B[bMid - 1]);
			}
			if ((A.length + B.length) % 2 == 1) {
				return left;
			} // 奇数的话不需要考虑右半部分

			int right = 0;
			if (aMid == A.length) {
				right = B[bMid];
			} else if (bMid == B.length) {
				right = A[aMid];
			} else {
				right = Math.min(B[bMid], A[aMid]);
			}

			return (left + right) / 2.0; //如果是偶数的话返回结果
		}
	}
	return 0.0;
}
```
