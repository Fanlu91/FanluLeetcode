#done
两种解法，双重循环遍历 和 [[D-hashtable]] 都不困难，顺着逻辑就能写出来。

```java
public int[] twoSum1(int[] nums, int target) {  
    for (int i = 0; i < nums.length; i++) {  
        for (int j = i; j < nums.length; j++) {  
            if (nums[i] + nums[j] == target && j != i)  
                return new int[]{i, j};  
 		}  
    }  
    return null;  
}
```

```java
public int[] twoSum(int[] nums, int target) {  
     Map<Integer, Integer> map = new HashMap<>();  
	 for (int i = 0; i < nums.length; i++) {  
		if (map.containsKey(target - nums[i])) {  
			return new int[]{map.get(target - nums[i]), i};  
		}  
		map.put(nums[i], i);  
	 }  
    return null;  
}

```

两种解法其实可以延伸出一些算法题的基本思想，我想这也是作为Leetcode 的第一道题目所期望带来的效果：
1. [[穷举遍历]]，计算机寻找一个问题的答案时穷举所有可能性，即试一遍比出来结果，是最基本也是最普遍的方法。很多算法以及数据结构的使用， 不过是为了降低穷举的难度，本质上来说还是让计算机去穷举。任何一个问题，想到穷举算法可能是必须的，即使这个算法实际不可行。
2. [[空间换时间]]，通过存储已知条件、计算结果或中间状态等来避免重复计算，从而提高执行效率又是另外一个特别重要的思想。