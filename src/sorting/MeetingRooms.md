这题的重点是 2d array 的排序
```java
//        Arrays.sort(intervals);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
//         Arrays.sort(intervals, Comparator.comparingDouble(o -> o[0]));
```