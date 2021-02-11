对数时间复杂度。有时候甚至比常量级的算法还高效。 
 相反指数时间复杂度就很恐怖，可以理解为对大数据是无效的。

## 缺点

二分查找针对的是**有序数据**。对于动态数据，如果数据集有频繁的插入，则需要考虑每次有序插入 或者二分前重新排序，维护成本很高。针对动态数据集合，应当使用二叉数。 
 二分查找依赖顺序表结构（数组），即需要对指定下标的随机存取O（1），因此对链表无效。

其如果数据量比较小，比如10个数的数组，直接遍历查找就行了。 
 如果数据量太大也不适合，因为依赖顺序存储这种特性，需要大量的连续内存空间。

二分查找可以解决的问题一般也可以用散列表、二叉树解决。不过值得注意的是，散列表和二叉树都需要比较多的存储空间，而二分查找依靠数组存储数据实际上是最节省空间的。

## 优势

值等于定值 其实二分查找不会怎么被用到，它更适合“近似”查找，这类问题上优势明显。

 

```
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