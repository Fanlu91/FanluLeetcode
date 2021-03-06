Skip list. 是一种各方面都很优秀的动态数据结构。可以支持快速的插入、删除、查找操作。 
Redis中有序结合 Sorted Set就是用跳表实现的（准确的说由一个双hashmap构成的字典和跳跃表实现）。

对单链表来说，即使数据是有序的，如果想要查找某个数据，只能从头到尾遍历，查询效率很低。 
为了提高查询效率对链表建立一级“索引”，每两个结点提取一个到上一级，我们把抽出来的那一层叫做索引，或索引层。down指针指向下一个结点。 
在第一级索引之上建立第二季索引，依次类推。 
这种链表加多级索引的结构就是跳表。

跳表的查询时间复杂度是 O（mlogn） m值一般很少，所以是O（logn）。跳表基本上就是基于链表实现二分查找 
一般链表存储的是很大的数据，data占大头，索引仅需保存关键值和几个指针，因此占用空间也并不大。

跳表的插入、删除操作时间复杂度也是O（logn）。

## 索引动态更新

当不断插入数据时，如果不更新索引，可能出现两个索引结点间数据过多的情况，甚至退化为单链表。作为一种动态数据结构，我们需要某种手段来维护索引与原始链表大小之间的平衡。跳表通过随机函数来维护平衡性。 
当忘跳表中插入数据时，同时使用随机函数选择将数据插入部分索引层中。比如随机函数选择了k，则我们将数据插入第1至k级的索引中。 
随机函数很有讲究，可以参考redis的实现。

## 优点

跳表的代码比较容易实现（相对红黑树），也意味着可读性更强，不容易出错。更灵活，可以通过改变索引策略来平和执行效率和内存消耗。

