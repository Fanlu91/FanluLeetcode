逆序对个数=逆序度，排序的过程就是不断的减⼩逆序度的过程，我们只要在排序的 过程中，记录每步操作逆序度降低的个数，累加起来就能得到原始数据的逆序度。


bubble sort swap个数就是逆序度
```java
// TLE
public int reversePairs(int[] a) {
	int count = 0;
	for (int i = 0; i < a.length; i++) {
		boolean finished = true;
		for (int j = 0; j < a.length - 1 - i; j++) {
			if (a[j] > a[j + 1]) {
				count++;
				int tmp = a[j];
				a[j] = a[j + 1];
				a[j + 1] = tmp;
				finished = false;
			}
		}
		if (finished)
			break;
	}
	return count;
}
```

merger sort merge时跨越的元素个数是逆序度（相对没有那么直观）
```java
int count = 0;
public int reversePairs(int[] a) {
	mergeSort(a, 0, a.length - 1);
	return count;
}

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
			count += mid - i + 1; // a[j] 这个更小的数字 移动到了mid - i + 1 个更大的数字前
		}
	}

	while (i <= mid) {
		tmp[k++] = a[i++];
		count++;
	}
	while (j <= right) {
		tmp[k++] = a[j++];
	}
	for (i = 0; i <= right - left; i++) {
		a[i + left] = tmp[i];
	}
}
```