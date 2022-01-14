# 沿用3sum的思路

[[ThreeSum]]的变体，能够写出前者对于后者基本就是思考一些剪枝优化的点。

沿用思路应该能写出下面的代码。执行平均时间是18ms。
```java
public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        for (int a = 0; a < nums.length - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1])
                continue;
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1])
                    continue;
                int tmp = target - nums[a] - nums[b];
                int d = nums.length - 1;
                for (int c = b + 1; c < nums.length - 1; c++) {
                    if (c > b + 1 && nums[c] == nums[c - 1])
                        continue;
                    while (c < d && nums[c] + nums[d] > tmp) {
                        d--;
                    }
                    if (c < d && nums[c] + nums[d] == tmp) {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    }
                }
            }
        }
        return ans;
    }
```



# 剪枝优化
优化点
```java
// 如果这四数之和大于target, 往后算也必大于target  
if ((long) nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target)  
    return ans;
	
// 下面这四数之和小于target, 则nums[i]小了  
if ((long) nums[a] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)   
    continue;	
```

优化后的平均时间是4 ms
```java
public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4)
            return new LinkedList<>();
        Arrays.sort(nums);

        List<List<Integer>> ans = new LinkedList<>();
        for (int a = 0; a < nums.length - 3; a++) {
            // 如果这四数之和大于target, 往后算也必大于target
            if ((long) nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3] > target)
                return ans;
            // 下面这四数之和小于target, 则nums[i]小了
            if ((long) nums[a] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target)
                continue;

            if (a > 0 && nums[a] == nums[a - 1])
                continue;
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1])
                    continue;
                int tmp = target - nums[a] - nums[b];
                int d = nums.length - 1;
                for (int c = b + 1; c < nums.length - 1; c++) {
                    if (c > b + 1 && nums[c] == nums[c - 1])
                        continue;
                    while (c < d && nums[c] + nums[d] > tmp) {
                        d--;
                    }
                    if (c < d && nums[c] + nums[d] == tmp) {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    }
                }
            }
        }
        return ans;
    }


```
