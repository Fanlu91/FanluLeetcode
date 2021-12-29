#detail

# 前提分析
[[TwoSum]] 的进阶

大于两个数，可以意识到用哈希表去重会非常困难

三个数或者多个数的凑一个数值，应该考虑到需要先**排序**
- 排序之后比较容易划定范围，剪枝
- 排序的时间复杂度时O(logn)，在这里是值得使用的（一般来说题目本身的复杂度不会比这个更低了）


# 双指针解法 
[[A-twopointers]]
这里使用双指针思想，详细讲一种写法：

## 主干思想
本质上来说还是3个变量的遍历，凑数。
1. 通过排序划分出了边界 `0 | a | b | c | length-1` 
2. 每次固定其中的n - 1个条件，即2个条件，每次变某一个条件去试，在这里试的(id)范围是
```less
 a  [0 - length-3]
 b  [a+1 - length-2]
 c  [b+1 - length-1] 
```

得到这样的伪码
```java
 for (int a = 0; a < nums.length - 2; a++) {
 	...
	for (int b = a + 1; b < nums.length-1; b++) {
		...
		while (b < c && nums[b] + nums[c] > target) {
			c--;
		...
		
```
	

## 基础框架
```java
public List<List<Integer>> threeSum(int[] nums) {
	if (nums.length < 3)
		return new LinkedList<>();
	List<List<Integer>> ans = new LinkedList<>();

	...

	return ans;
}
```


## 一些次要条件
其实有了上面的框架你应该已经可以大致的写出这道题了，当然还有一些细节需要/可以去处理

- 排序之后，nums[a] 大于sum，说明不可能有其他结果了，可以直接结束
- nums[a] == nums[a - 1] 以及 nums[b] == nums[b - 1]的时候，可能会得到重复解，这道题目要求去重，因此需要跳过
- `Arrays.sort(nums)`，数组排序
- `Arrays.asList(nums[a], nums[b], nums[c])`直接构建list


## 最终代码

```java
public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new LinkedList<>();
        Arrays.sort(nums);
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        // 若修改题目，sum不为0，此方法通用
        int sum = 0;

        for (int a = 0; a < nums.length - 2; a++) {
            // nums[a] 大于sum，结束
            if (nums[a] > sum)
                return ans;
            // a需要和上一次枚举的数不相同
            if (a != 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            int target = sum - nums[a];

            // 枚举b和c
            // c对应的指针初始指向数组的最右端
            int c = nums.length - 1;
            for (int b = a + 1; b < nums.length-1; b++) {
                // 需要和上一次枚举的数不相同
                if (b != a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (b < c && nums[b] + nums[c] > target) {
                    c--;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (b == c) {
                    break;
                }
                if (nums[b] + nums[c] == target) {
                    ans.add(Arrays.asList(nums[a], nums[b], nums[c]));
                }
            }
        }
        return ans;
    }
```

# 一些思考点

[[JavaDetails#使用范围尽量准确 a b 与 a b]]

指针遍历所谓结果去重？在每次循环中，两指针使用id所枚举的元素的值不能和上一次的元素的值相同

