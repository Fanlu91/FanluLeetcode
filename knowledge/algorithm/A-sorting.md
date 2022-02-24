# 评价排序算法
时间复杂度
空间复杂度
**原地性**：排序过程是否需要申请新的空间
- 原地排序空间复杂度并不一定是O(1)，比如快速消耗函数调用栈
- O(1)的排序一定是原地排序

**稳定性**：排序后，相等元素原有的先后顺序不变
- 排序的可能是对象，对比的元素相同不代表对象相同
- 需要多次排序的场景，必须使用稳定排序，比如先按身高再按体重。


是否比较

比较排序
- 冒泡
- 插入
- 选择
- 并归
- 快速

非比较排序
- 桶排序，实用，海量数据处理
- 计数排序 ，不实用
- 基数排序


# O(n^2) 排序
## 冒泡
原地
稳定

效率和逆序度有较大的关系。
- 冒泡排序执行过程中，总的交换次数是确定的，即**逆序度**。 
```java
void bubbleSort(int[] a, int n){
	if(n < 2)
		return;
	for(int i=0; i<n; i++){
		boolean finished = true;
		for(int j=0; j<n-1-i; j++){
			if(a[j] > a[j+1] ){
				int tmp = a[j];
				a[j] = a[j+1];
				a[j+1] = tmp;
				finished = false;
			}
		}
		if(finished)
			break;
	}
}
```
[[SortList]]

## 插入排序

将数组分为有序区间，无序区间，初始化有序只有一个。
对于无序区间的每一个数，遍历有序区间放入应该插入的位置。
原地
稳定

```java
void insertionSort(int[] a, int n){
	if(n < 2)
		return;
	for(int i=1; i<n; i++){ // [0,i) 有序
		int val = a
		int j = i - 1;
		for(;j>=0;j--){
			if(a[j]>val){
				a[j+1] = a[j];
			}else{
				break;
			}
		}
		a[j+1] = val;
	}
}
```

基于链表的插入排序 [[InsertionSortList]]

## 选择排序
也是将数组分为有序区间，无序区间。在无序区间找最小的，与最前面位置的元素交换。

原地
非稳定

```java
void selectionSort(int[] a, int n){
	if(n < 2)
		return;
	for(int i=0; i<n-1; i++){ // [0,i) 有序
		int minPos = i;
		for(int j=i;j<n;j++){
			if(a[j]<a[minPos]){
				minPos=j;
			}
		}
		int tmp = a[i];
		a[i] = a[minPos];
		a[minPos] = tmp;
	}
}
```


# O(nlog(n)) 排序
## 并归排序
归并排序因为**不是原地排序**，占用额外空间，因此使用不多。

稳定

（不断的）把数组分成两部分，然后对两部分分别排序（分到不能再分自然有序），再将排序好的两部分合在一起。
`mergeSort(l,r) = merge(mergeSort(l,m) ,mergeSort(m+1,r))`
- `m=(l+r)/2`
- 终止条件 `l>=r`

```java
private void mergeSort(int[] a, int l, int r) {
	if (l >= r)
		return;
	int m = l + (r - l) / 2;
	mergeSort(a, l, m);
	mergeSort(a, m + 1, r);
	if (a[m] > a[m + 1]) // 可选剪枝
		merge(a, l, m, r);
}

private void merge(int[] a, int left, int mid, int right) {
	int i = left, j = mid + 1, k = 0;
	int[] tmp = new int[right - left + 1];
	while (i <= mid && j <= right) {
		if (a[i] <= a[j]) { // 这里的等于决定了merge是稳定的
			tmp[k++] = a[i++];
		} else {
			tmp[k++] = a[j++];
		}
	}

	while (i <= mid) {
		tmp[k++] = a[i++];
	}
	while (j <= right) {
		tmp[k++] = a[j++];
	}
	for (i = 0; i <= right - left; i++) {
		a[i + left] = tmp[i];
	}
}
```
[[SortList]]
[[ReversePairs]]


## 快速排序
不同于并归排序，递得过程很复杂，归的过程反而简单。

递推公式
`quickSort(p,r) = partition(p,r) + quickSort(p,q)+ quickSort(q+1,r)`
- 终止条件 q>=r

分区点 pivot ，分区一边都比它小，一边都比它大。
```java
void quickSort(int[] a, int n){
	quickSortR(a, 0, n-1);
}

private void quickSortR(int[] a, int p, int r){
	if(p>=r)
		return;
	int q = partition(a,p,r);
	quickSortR(a, p, q-1);
	quickSortR(a, q+1, r);
}
```

```java
// [p,i]比分区点小，初始把i放在p-1 因为这时p位置元素不一定小于分区点
// [i+i,j) 大于等于分区点
// [j,r-1]未处理
// r 是分区点
// 最后 i+1 与 r 互换一下
private int partition(int[] nums, int p, int r) {
	int l = p - 1;
	for (int i = p; i < r; i++) {
		if (nums[i] < nums[r]) { // 决定是升序还是降序
			l++;
			swap(nums, l, i);
		}
	}
	swap(nums, l + 1, r);
	return l + 1;
}
```

原地
非稳定


pivot 的特点其实除了排序，还可以最高效的求数组的第K大/小值。

## 分区与复杂度
快速排序最坏时间复杂度可能是平方级，尤其是接近有序或有序、选择的分区点又偏大或偏小的话，时间复杂度很差。 
- 越有序，快读排序越慢
- 空间复杂度最差是O(n)，递归栈的深度


选择合理的分区点非常重要，最理想的点是 **被分区点分开后的两个分区，数据的数量差不多**。 
可以采用一些策略，如：
- 三数取中法
- 随机法



## 堆排序



# O(n)排序
需要借助一定的外部条件，即特殊场景下才能使用，不能作为通用排序算法。 

不过可以使用时效率很高。

## 基数排序
手机号，先排第一位再排第二位





# Java sort
Java 语言采用**堆排序**实现排序函数，C 语言使用**快速排序**实现排序函数。 

# Glibc中的qsort（）

qsort（）会首先使用并归排序来排序输入数据。 
要排序的数据量比较大的时候，它会该用采用三数取中法实现的快速排序。 
qsort（）自己实现一个堆上的栈，手动模拟递归来避免堆栈溢出。 
qsort其实还应用了使用哨兵的插入排序；快速排序过程中当要排序的区间中元素的个数小于等于4时，qsort就退化为插入排序。 小规模数据面前 O（n2）时间复杂度的算法不一定比O（nlogn）的算法执行时间长（这时被忽略的低阶/系数/常数都可能影响）。原因在于复杂度分析是比较偏理论的，深究的话时间复杂度并不等于代码执行时间。


# 排序常考题型
1. 特殊排序，看似要让排序，但是又不允许使用通用排序
2. TopK
3. 链表上的排序
4. 排序预处理类题目，此类问题一般允许