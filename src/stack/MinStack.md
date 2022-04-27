不同于 [[SortOfStacksLCCI]]，这次题目要求不是说实现排序的栈，而是要一个插入顺序的栈，但是要能够 O(n) 检索到最小数值。

这道题目最巧妙的解法是，用一个辅助栈存储当前最小值，每次压入新数据时，都同时将当前最小值压入辅助栈。

实际可以算作一道找规律题，最重要的这个规律或者说技巧