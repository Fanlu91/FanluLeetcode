线性排序虽好，但是使用场景特殊有限，不能作为通用排序算法。 
为了兼顾任意类型及规模数据的排序，一般都会首选时间复杂度是O(nlogn) 的排序算法来实现排序函数。（归并/快速/堆） 
Java 语言采用堆排序实现排序函数，C 语言使用快速排序实现排序函数。 
归并排序因为不是原地排序，占用额外空间，因此使用也不多。

快速排序最坏时间复杂度可能是平方级，尤其是接近有序或有序、选择的分区点又偏大或偏小的话，时间复杂度很差。 
选择合理的分区点非常重要，最理想的点是 **被分区点分开后的两个分区，数据的数量差不多**。 
可以采用一些策略，如：

- 三数取中法
- 随机法

## Glibc中的qsort（）

qsort（）会首先使用并归排序来排序输入数据。 
要排序的数据量比较大的时候，它会该用采用三数取中法实现的快速排序。 
qsort（）自己实现一个堆上的栈，手动模拟递归来避免堆栈溢出。 
qsort其实还应用了使用哨兵的插入排序；快速排序过程中当要排序的区间中元素的个数小于等于4时，qsort就退化为插入排序。 小规模数据面前 O（n2）时间复杂度的算法不一定比O（nlogn）的算法执行时间长（这时被忽略的低阶/系数/常数都可能影响）。原因在于复杂度分析是比较偏理论的，深究的话时间复杂度并不等于代码执行时间。