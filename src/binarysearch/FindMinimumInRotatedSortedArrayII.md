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