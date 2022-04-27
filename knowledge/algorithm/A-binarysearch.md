
# 依赖排序
If there's a SORTED array, binary-search should come to your mind immediately

二分查找针对的是**有序数据**。 
- 进一步阐述应当是依赖**顺序表结构**（数组），即需要对指定下标的随机存取O（1），因此有序链表也不行。

二分查找可以解决的问题一般也可以借助哈希表、二叉树解决。当然散列表和二叉树都需要比较多的存储空间，二分查找依靠数组存储数据实际上是最**节省空间**的。

# 数据局限性
其如果数据量比较小，比如10个数的数组，直接遍历查找就行了。 
如果数据量太大也不适合，因为依赖顺序存储这种特性，需要大量的连续内存空间。
 
对于动态数据，如果数据集有频繁的插入，则需要考虑每次有序插入 或者二分前重新排序，维护成本很高。
- 针对**动态数据**集合，应当使用**二叉树**。 

值等于定值时，其实二分查找不会怎么被用到，它更适合“近似”查找，这类问题上优势明显。


```java
//注意点： 终止条件/区间上下界更新方法/返回值选择
    public int bsearchFirstBiggerOrEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) return mid;
                else high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
    public int bsearchLastSmallerOrEqual(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == n - 1) || (a[mid + 1] > value)) return mid;
                else low = mid + 1;
            }
        }
        return -1;
    }
```

# 编写二分查找代码的指引
二分的思路不难，难点在于细节，变种多，写出 bug free 代码其实有一定难度。
编写代码时可以遵循以下思路：
定义 `left right mid`，引入while循环
- 查找区间固定为 `[left,right]`，中间值`mid`
	- 返回值永远使用mid，不要使用left或right的取巧
	- 针对一些特殊体型，先处理mid命中的情况，再处理左半部分、右半部分的其他情况
- 循环条件 `left<=right`
	- left和right的更新方式，永远都只有两种`mid+1 or mid-1`
	- 根据需要在循环中补充处理诸如`left==right`之类的特殊情况
- 对于非确定性查找，使用前后探测法确定搜索区间

基础
[[BinarySearch]]

典型二分变种

# 二分的本质是二段性而非单调性
其实并不是严格有序才能使用二分
- 有序是一种非常好的二段性判定方式

不仅只有满足 01 特性（满足/不满足）的二段性可以使用二分
一定满足/不一定满足也可以二分，比如 [[SearchInRotatedSortedArray]]

二分缩小范围时，根据想要求得的结果，着力在将不符合的区域排除掉
- 理解l 和 r 的移动。

很多二分的变体是围绕这点展开的。

[[FindMinimumInRotatedSortedArray]]
[[FindPeakElement]]
[[MedianOfTwoSortedArrays]]