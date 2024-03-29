# 中位数
中位数的定义，有两种思路：
1. 统计中，中位数被用来将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。使用二分查找确定两个有序数组的「分割线」，中位数就由**分割线左右**两侧的元素决定。
     - 分割线满足这样的性质：左右两边元素个数相等（这里忽略两个数组长度之和奇偶性的差异）；分割线左边所有元素小于等于分割线右边所有元素

2. 当 m+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2 个元素，当 m+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。因此，这道题可以转化成**寻找两个有序数组中的第 k 小的数**，其中 k 为 (m+n)/2 或 (m+n)/2+1
[[MedianOfTwoSortedArrays]]