下面的代码 `[[1,4],[2,3]]` 这个用例报错，没有考虑到 前面的结束大于后面结束的情况。

```java
public int[][] merge(int[][] intervals) {
	int n = intervals.length;
	if (n == 1)
		return intervals;
	Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
	for (int i = 0; i < intervals.length - 1; i++) {
		if (intervals[i][1] >= intervals[i + 1][0]) {
			n--;
		}
	}

	int[][] res = new int[n][2];
	int j = 0;
	for (int i = 0; i < intervals.length; i++) {
		res[j][0] = intervals[i][0];
		while (i < intervals.length - 1 && intervals[i][1] >= intervals[i + 1][0]) {
			i++;
		}
		res[j][1] = intervals[i][1];
		j++;
	}
	return res;
}
```

解决问题
```java
public int[][] merge(int[][] intervals) {
	int n = intervals.length;
	if(n==1)
		return intervals;
	Arrays.sort(intervals,(a,b)-> a[0]-b[0]);
	for(int i=0;i<intervals.length-1;i++){
		if(intervals[i][1]>=intervals[i+1][0]){
			// added
			if(intervals[i][1]>intervals[i+1][1])
				intervals[i+1][1] = intervals[i][1];
			n--;
		}
	}
	
	int[][] res = new int[n][2];
	int j=0;
	for(int i=0;i<intervals.length;i++){
		res[j][0]=intervals[i][0];
		while(i<intervals.length-1&&intervals[i][1]>=intervals[i+1][0]){
			i++;
		}
		res[j][1] = intervals[i][1];
		j++;
	}
	return res;
}
```