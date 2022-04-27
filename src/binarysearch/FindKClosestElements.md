这道题目作为二分的变体比较有难度

需找一个不定长的区间，可能是1也可能是100，按照基础的二分思路即使找到了准确的mid点，问题等于才刚刚开始，后面处理起来很麻烦。

关键点在于不找准确的mid，而是寻找区间的端点。

```java
public List<Integer> findClosestElements(int[] arr, int k, int x) {
	int l = 0, r = arr.length - k,mid=0;
	// 定位左区间端点的边界值
	while (l <= r) {
		mid = l + (r - l) / 2;
		if(mid + k > arr.length -1){
			break;
		}

		if(x - arr[mid]== arr[mid + k] - x ){
			break;
		}
		if (x - arr[mid] > arr[mid + k] - x) {
			l = mid + 1;
		} else {
			r = mid - 1; 
		}
	}
	System.out.println(l+" "+mid+ " "+r);
	List<Integer> res = new ArrayList<>();
	for (int i = mid; i < mid + k; i++) {
		res.add(arr[i]);
	}
	return res;
}
```
以上解法存在问题，`x - arr[mid]== arr[mid + k] - x ` 时，返回的mid值不一定符合要求，可能有相等的值在附近

```java
public List<Integer> findClosestElements(int[] arr, int k, int x) {
	int l = 0, r = arr.length - k;

	while (l < r) {
		int mid = l + (r - l) / 2;
		// 定位左区间端点的边界值
		if (x - arr[mid] > arr[mid + k] - x) { 
			l = mid + 1;
		} else { // 相等时移动l
			r = mid; 
		}
	}
	System.out.println(l+" "+r);
	List<Integer> res = new ArrayList<>();
	for (int i = l; i < l + k; i++) {
		res.add(arr[i]);
	}
	return res;
}
```
寻找的是区间的左侧
`x - arr[mid] > arr[mid + k] - x`  mid的位置不可能是左边界，有点靠左了，l = mid + 1 右移

`x - arr[mid] <= arr[mid + k] - x`  mid右边的位置不可能是左边界（注意arr是有序的），mid位置本身有可能

最后逼近得到l。