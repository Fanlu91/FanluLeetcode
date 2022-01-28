# 滑动窗口
[[A-twopointers.slidingwindow]]
## n个set
根据题意最直接的解法应该使用set去存字符
```java
// 146ms
public int lengthOfLongestSubstring(String s) {
	int res = 0;
	for (int left = 0; left < s.length(); left++) {
		Set<Character> set = new HashSet<>();
		int right = left;
		while (right < s.length()) {
			if (set.contains(s.charAt(right)))
				break;
			else {
				set.add(s.charAt(right));
				right++;
				res = Math.max(res, right - left + 1);
			}
		}
	}
	return res;
}
```
这样实现是比较直观的，但是显然也存在一些效率问题
- 每次都要使用一个新的set
- left遍历冗余太多



## 1个set
set可以复用，当出现重复，新的left被识别后，将left前的字符移出set。
left也随着向右移动

```java
// 10ms
public int lengthOfLongestSubstring(String s) {
	int res = 0, left = 0, right = 0;
	Set<Character> set = new HashSet<>();
	while (right < s.length()) {
		if (set.contains(s.charAt(right))) {
			while (left < right) {
				if (s.charAt(left) == s.charAt(right)) {
					set.remove(s.charAt(left));
					left++;
					break;
				}
				set.remove(s.charAt(left));
				left++;
			}
		}
		set.add(s.charAt(right));
		res = Math.max(res, right - left + 1);
		right++;
	}

	return res;
}
```

## map替代set
上面的解法已经显著提升了效率，当然还有进一步优化的空间。
left需要移动的位置其实不需要逐个遍历，当`s.charAt(right)`重复时，left的位置应该是`right + 1`。使用map记录下 char 和 index 的对应位置就能够直接使用。所以有
```java
Map<Character, Integer> map = new HashMap<>();

... 

if (map.containsKey(s.charAt(right)))
	left = Math.max(left, map.get(s.charAt(right)) + 1);
```
因为map并不清空，之前的结果也会被取到，通过和left进行比较来判断是否要移动left。

这样结合之后，得到了下面的解：

```java
// 6 ms
public int lengthOfLongestSubstring(String s) {
	int res = 0, left = 0, right = 0;
	Map<Character, Integer> map = new HashMap<>();
	while (right < s.length() && left < s.length() - res) {
		if (map.containsKey(s.charAt(right)))
			left = Math.max(left, map.get(s.charAt(right)) + 1);

		map.put(s.charAt(right), right);
		res = Math.max(res, right - left + 1);
		right++;
	}

	return res;
}
```

## map2
上述思路还可以有下面的实现。
```java
public int lengthOfLongestSubstring(String s) {
	int res = 0;
	if (s.length() == 0)
		return res;

	Map<Character, Integer> map = new HashMap<>();
	int left = 0;

	for (int i = 0; i < s.length(); i++) {
		if (map.containsKey(s.charAt(i))) {
			int tmp = map.get(s.charAt(i));
			if (tmp >= left)
				left = tmp + 1;
			else
				res = Math.max(res, i - left);
		} else {
			res = Math.max(res, i - left);
		}
		map.put(s.charAt(i), i);
	}

	return 1 + res;
}
```

## array替代map
因为本题设定的字符范围有限，可以使用定长的数组代替map，来追求更高的性能。

最巧妙的是`if (map[c] >= left)` 不仅替代了`map.containsKey`，也解决了 left 可能在前面出现过的问题（left没出现和在前面出现在这里一样）。

所以解法显得非常简洁。
```java
public int lengthOfLongestSubstring(String s) {
	if (s.length() == 0)
		return 0;
	int[] map = new int[128];
	Arrays.fill(map, -1);

	int res = 0, left = 0;

	for (int i = 0; i < s.length(); i++) {
		char c = s.charAt(i);
		if (map[c] >= left)
			left = map[s.charAt(i)] + 1;
		map[c] = i;
		res = Math.max(res, i - left);
	}

	return res + 1;
}
```