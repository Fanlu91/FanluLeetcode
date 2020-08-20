
```java
//int[] tmp = new int[len];
Integer[] tmp = new Integer[len];

booked = new HashSet<>();


booked.addAll(Arrays.asList(tmp));
// use Integer[]  instead of int[]
// as Arrays.asList(int[]) will internally consider int[] as a single element.
```
