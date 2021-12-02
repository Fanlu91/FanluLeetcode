## 定义

**链表**不需要连续的内存空间，通过指针将一组零散的内存串联起来使用。内存块称为链表的**结点**，为了将结点串起来，结点除了存储数据data外，还需要记录下一个结点的地址，这个记录下一个结点地址的指针叫做**后继指针next**。

- 结点由数据域和指针域构成

第一个和最后一个结点是比较特殊的，分别是头结点和尾结点。头结点用来记录链表的基地址；尾结点的后继指针指向空地址null。

```java
cur.next = node; // 建立指向 
cur = node; // 移动指针
```
考察对引用（指针）的理解。



## Singly/Doubly/Circular

单链表只有一个方向，尾结点指向空地址。循环链表是一种特殊的单链表，即尾结点的后继指针指向头结点。

双向链表比单链表多一个前驱指针pre，指向前面结点的地址。支持双向遍历。

当然相应的也有双向循环链表。

java中的LinkedHashMap就用到了双向链表。

虽然双向链表占用多一点内存空间，但是空间换时间很多情况下是值得的。
双向链表的相对优势：一些情况下插入和删除都更高效，因为它支持以O(1)找到前驱结点。

## Delete/Add

删除操作 ：

1. 删除结点中值等于指定值的结点 
   不管是单项还是双向，都需要从头遍历 O（n）
2. 删除已指向的结点 
   删除后需要知道被删除结点的前驱结点是谁，修改它的后继指针，此时单向链表无法直接知道，需要耗费O(n)去找，双向就不用，O(1)即可。

插入操作 :

1. 插入指定排序的位置
   需要遍历
2. 插入已知结点后
   直接插入
3. 插入已知结点前
   双向链表没有问题，单项链表存在不知道前驱是谁的问题，需要从头遍历

> 除删除和插入，对于有序链表，双向链表在查找时更具优势，可以选择向前还是向后找。

## Sentinel Node 哨兵结点

A sentinel is a dummy object that allows us to simplify boundary conditions.

A sentinel node is a dummy node that goes at the front of a list. 
In a doubly-linked list, the sentinel node points to the first and last elements of the list. 

单链表在插入和删除时，需要修改**前驱结点的后继指针**，这就形成了“邻居依赖”，链表中第一个元素没有前驱结点，如果没有特殊处理在插入和删除第一个结点时，就会出错。

链表 第一个结点及最后一个结点的插入和删除操作都要做特殊处理

### 引入哨兵解决边界问题

引入哨兵结点，不管链表是否为空，head指针都一直指向这个结点 这种带哨兵的链表称为**带头链表**。哨兵结点是不存储数据的。

利用哨兵来简化编程难度是一种常见的技巧，在插入排序/并归排序/动态规划等都可能用到。

哨兵可以减少循环中（大部分情况下都用不到的）的判断语句，提高效率。




## 数组与链表的取舍

实际开发当中，不能简单的用复杂度分析来决定使用哪种数据结构。

如数组使用的是连续内存，可以CPU缓存机制可以预读数组中的数据，访问效率更高，但是链表的离散存储对CPU的缓存机制不是很友好，无法高效预读。（CPU在从内存读取数据的时候，会先把读取到的数据加载到CPU的缓存中。而CPU每次从内存读取数据并不是只读取那个特定要访问的地址，而是读取一个数据块，并保存到CPU缓存中，然后下次访问内存数据的时候就会先从CPU缓存开始查找，如果找到就不需要再从内存中取。这样就实现了比内存访问速度更快的机制）

数组的缺陷是大小固定，申请太大不一定有，申请太小扩容很麻烦。
链表本身没有大小限制。

对内存的使用要求苛刻应多使用数组，链表除了需要额外的存储空间去存放指针，频繁的插入删除还会导致频繁的内存申请和释放，造成内存随便，触发gc。



## 一些技巧

1. 理解指针或引用的含义
   含义：将某个变量（对象）赋值给指针（引用），实际上就是就是将这个变量（对象）的地址赋值给指针（引用）。
   示例：
   p—>next = q; 表示p节点的后继指针存储了q节点的内存地址。
   p—>next = p—>next—>next; 表示p节点的后继指针存储了p节点的下下个节点的内存地址。

2. 警惕丢失指针和内存泄露
   插入第一个/删除最后一个
3. 利用哨兵简化问题
   减少特殊情况及相应的判断
4. 重点留意边界条件处理
   - **链表为空**
   - **只包含一个结点**
   - **只包含两个结点**
   - **处理头/尾结点，别在插入、删除后丢掉了头/尾**

5. 画图举例，辅助思考
6. 多写多练，没有捷径
   孰能生巧，不管是什么算法，只有经过反复的练习，才能信手拈来。



### 链表翻转

#### 基本翻转

首先把握好链表数据结构的三个关键指针 pre cur next。

两个Node翻转这件事本身很简单，不要想复杂了，把要反转的节点`cur`指向其前一个节点`pre`就行，是很简单的操作（下2）。至此**翻转已经完成**。

问题在于这样操作后，节点cure原本的指向cur.next会被覆盖，如果不存下来无法继续遍历链表，所以需要在操作前保存后一个原本的指针到next（下1），保存之后才能让指针继续前进并继续翻转（下3）。



```java
public ListNode reverseList(ListNode head) {
  if (head == null || head.next == null)
    return head;
  ListNode pre = null, cur = head, next;

  while (cur != null) {
    next = cur.next;    // 1. save next pointer
    cur.next = pre;     // 2. reverse current node
    // 3. advance current and prev
    pre = cur;
    cur = next;

  }
  // from the last iteration, pre pointed to current node which does not have a next,
  // it means pre is the new head
  return pre;
}
```



#### 递归解

递归到链表的结尾处开始翻转。开始翻转时，可以认为当前节点cur之后的节点已经完成了翻转，需要做的是把cur.next节点的next指向cur，并把cur.next置空。

```java
public ListNode reverseListRecursive(ListNode cur) {
  if (cur == null || cur.next == null)
    return cur;

  ListNode newHead = reverseListRecursive(cur.next);
  //reverse current cur
  cur.next.next = cur;
  cur.next = null;
  return newHead;
}
```





java 反转链表的操作：

```
Collections.reverse(list);
```





## 必须掌握的链表问题清单

单链表翻转  206 92

转链表中环的检测 （双指针）

两个有序的链表合并

删除链表倒数第 n 个结点

求链表的中间结点