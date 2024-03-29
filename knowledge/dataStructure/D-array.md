# 特点
一种线性表（Linear List），一组连续的内存空间，来存储一组相同类型的数据。
- 这样的好处是能够随机访问，带来的问题使得部分操纵需要搬运数据


- 链表、队列、栈等也是线性表结构。
- 二叉树、堆、图 是非线性结构，数据之间并不是简单的前后关系


# java技巧
构建数组并且放入初始化的内容
```java
int[] a = new int[]{1,2};

// 截取
Arrays.copyOf(a, 1);


// simplest way to print array
System.out.println(Arrays.toString(a));


```

## 二维数组
java 二维数组 先行后列

```java
int a[][] = new int[3][4]; //3行 4列
int lenY = a.length; // 3
```

二维数组的排序 

```java
//        Arrays.sort(intervals);
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
Arrays.sort(intervals, Comparator.comparingDouble(o -> o[0]));
```
[[MeetingRooms]]


## 数组和ArrayList

ArrayList 最大的优势就是可以将很多数组操作的细节封装起来。还有一个优势，就是支持动态扩容，当然最好在创建 ArrayList 的时候事先指定数据大小。

相对来说更应使用数组而不是ArrayList的场景：

1. ArrayList不能存储基本类型，比如 int、long，需要封装为 Integer、Long类，Autoboxing、Unboxing 会有一定的性能消耗。追求性能应使用数组
2. 如果事先已知数据的大小，使用数组十分简便，arrayList的大部分方法并不会用到。
3. 表示多维数据时，数组更加直观
4. 业务开发使用容器省时省力使用ArrayList就足够了。如果是做底层，则要考虑使用数组而不是容器。


### 使用数组初始化 ArrayList 
```java
//int[] tmp = new int[len];
Integer[] tmp = new Integer[len];

booked = new HashSet<>();

booked.addAll(Arrays.asList(tmp));
// use Integer[]  instead of int[]
// as Arrays.asList(int[]) will internally consider int[] as a single element.
```


# 典型问题

## 为什么大多数语言数组下标要从0开始？

从内存模型可知，下标的实际含义是偏移（offset）。

a来表示数组的首地址，a[0]则表示偏移为0，计算a[k]的地址根据偏移寻址时使用如下公式

```java
a[k]_address = base_address + k * type_size
```

从上述公式的type_size可以了解为什么数组只能存放同一种数据类型

若使用1做地址，则要多做一次-1 减法。

当然，还有就是历史原因，C是以0开始的。上述理由并不是绝对，尤其不同编程语言，比如python还支持负数下标。

btw二维数组寻址对于m*n的数组 a [ i ][ j ] (i < m,j < n)

```java
address = base_address + ( i * n + j) * type_size
```

## 数组和链表

**链表**通过指针将一组零散的内存串联起来使用。内存块称为链表的**结点**，为了将结点串起来，结点除了存储数据data外，还需要记录下一个结点的地址，这个记录下一个结点地址的指针叫做**后继指针**next。（即结点由数据域和指针域构成）

第一个和最后一个结点是比较特殊的，分别是**头结点**和**尾结点**。头结点用来记录**链表的基地址**；尾结点的后继指针指向空地址null。


单链表只有一个方向。循环链表是一种特殊的单链表，即尾结点的后继指针指向头结点。



双向链表比单链表多一个前驱指针prev，指向前面结点的地址。支持双向遍历，当然存储同样多的数据双向链表占用的内存空间更大。

双向链表的适用场景： 某些情况下插入和删除都更高效，因为它支持以O(1)找到前驱结点。

删除操作 两种情况：1.删除指定值的结点 都是O(n) 2.删除已知指向的结点 删除后需要知道被删除结点的前驱结点是谁，修改它的后继指针，但单项链表无法直接知道，需要耗费O(n)去找，双向就不用，O(1)即可。

删除操作类似。

除删除和插入，对于有序链表，双向链表在查找时更具优势，可以选择向前还是向后找。

java中的LinkedHashMap就用到了双向链表。



虽然双向链表占用多一点内存空间，但是**空间换时间**很多情况下是值得的。

当然双向列表也有一种循环双向列表的特殊组合。

实际开发当中，不能简单的用复杂度分析来决定使用哪种数据结构。

如数组使用的是连续内存，可以CPU缓存机制可以预读数组中的数据，访问效率更高，但是链表的离散存储对CPU的缓存机制就不是很友好，无法高效预读。（CPU在从内存读取数据的时候，会先把读取到的数据加载到CPU的缓存中。而CPU每次从内存读取数据并不是只读取那个特定要访问的地址，而是读取一个数据块）

数组的缺陷是大小固定，申请太大不一定有，申请太小扩容很麻烦。链表本身没有大小限制。



对内存的使用要求苛刻应多使用数组，链表除了需要额外的存储空间去存放指针，频繁的插入删除还会导致频繁的内存申请和释放，造成内存随便，触发gc。
