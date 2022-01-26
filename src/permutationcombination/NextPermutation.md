[[T-permutaioncombination]]
#todo

```java
public void nextPermutation(int[] nums) {
    int i = nums.length - 2;
    int j = nums.length - 1;
    while (i >= 0 && nums[i] >= nums[i + 1]) { // 寻找后面非升序序列位置
        i--;
    }
    if (i >= 0) { // 说明序列并不是最大序列
        while (j > i && nums[j] <= nums[i]) { // 寻找后面升序序列中比非升序元素大的最小的元素
            j--;
        }
        swap(nums, i, j);
    }
    reverse(nums, i + 1); // 将升序序列翻转
}

private void reverse(int[] nums, int start) {
    int end = nums.length - 1;
    while (end > start) {
        swap(nums, start, end);
        end--;
        start++;
    }
}

public void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
}
```