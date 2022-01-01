## Java集合类

Collection 接口是所有集合的根，然后扩展开提供了三大类集合，分别是：

- List，也就是我们前面介绍最多的有序集合，它提供了方便的访问、插入、删除等操作。
- Set，Set 是不允许重复元素的，这是和 List 最明显的区别，也就是不存在两个对象 equals 返回 true。我们在日常开发中有很多需要保证元素唯一性的场合。
- Queue/Deque，则是 Java 提供的标准队列结构的实现，除了集合的基本功能，它还支持类似先入先出（FIFO， First-in-First-Out）或者后入先出（LIFO，Last-In-First-Out）等特定行为。这里不包括 BlockingQueue，因为它通常是并发编程场合，所以被放置在并发包里。

### ArrayList、LinkedList、Vector

三者都是实现集合框架中的 List，也就是所谓的有序集合，都提供按照位置进行定位、添加或者删除的操作，都提供迭代器以遍历其内容等。它们在行为、性能、线程安全等方面，表现又有很大不同。

Vector、ArrayList、LinkedList均为线型的数据结构，但是从实现方式与应用场景中又存在差别。

#### 1 底层实现方式

ArrayList内部用**数组**来实现；

LinkedList内部采用**双向链表**实现；

Vector内部用数组实现。

#### 2 读写机制

ArrayList在执行插入元素是超过当前数组预定义的最大值时，数组需要扩容，扩容过程需要调用底层System.arraycopy()方法进行大量的数组复制操作；在删除元素时并不会减少数组的容量（如果需要缩小数组容量，可以调用trimToSize()方法）；在查找元素时要遍历数组，对于非null的元素采取equals的方式寻找。

LinkedList在插入元素时，须创建一个新的Entry对象，并更新相应元素的前后元素的引用；在查找元素时，需遍历链表；在删除元素时，要遍历链表，找到要删除的元素，然后从链表上将此元素删除即可。

Vector与ArrayList仅在插入元素时容量扩充机制不一致。对于Vector，默认创建一个大小为10的Object数组，并将capacityIncrement设置为0；当插入元素数组大小不够时，如果capacityIncrement大于0，则将Object数组的大小扩大为现有`size + capacityIncrement`；如果capacityIncrement<=0,则将Object数组的大小扩大为现有大小的2倍。



#### 3 读写效率

ArrayList对元素的增加和删除都会引起数组的内存分配空间动态发生变化。因此，对其进行插入和删除速度较慢，但检索速度很快。

LinkedList由于基于链表方式存放数据，增加和删除元素的速度较快，但是检索速度较慢。

#### 4 线程安全性

ArrayList、LinkedList为非线程安全；

Vector是基于synchronized实现的线程安全的ArrayList。

需要注意的是：单线程应尽量使用ArrayList，Vector因为同步会有性能损耗；即使在多线程环境下，我们可以利用Collections这个类中为我们提供的synchronizedList(List list)方法返回一个线程安全的同步列表对象。



#### 5 取舍

应用操作是偏向于插入、删除，还是随机访问较多，就可以针对性的进行选择。