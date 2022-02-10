# 栈
后进先出LIFO，操作受限只允许在顶端（栈顶）插入和删除数据。
stack  `[1, 2, 3]`, `1`-bottom,`3`-top;
- `push(v)`: Put `v` onto the stack. This changes the list `[1, 2, 3] `to `[1, 2, 3，v]`.
- `pop()`: changes the list `[1, 2, 3]` to `[1，2]` and returns `3`
- `peek()`, `peekFirst()`，返回 `3` 并不弹出。

栈两种实现方式，数组栈和链表栈。
- 当数据量达到一定程度的时候，链表的性能是远远低于动态数组的。
## 顺序栈
数组栈即顺序栈，数组最大下标是`top`
以 int 元素为例
```java
public class ArrayStack {
	private int[] item;
	private int count; // 当前栈中元素个数
	private int n; // 栈大小

	public ArrayStack(int n) {
		item = new int[n];
		this.n = n;
		this.count = 0;
	}

	public boolean push(int x) {
		if (count == n)
			return false;
		item[count++] = x;
		return true;
	}

	public int pop() {
		if (isEmpty())
			return -1;
		int val = item[count - 1];
		count--;
		return val;

	}

	public int peek() {
		if (isEmpty())
			return -1;
		return item[count - 1];
	}

	public boolean isEmpty() {
		return count == 0 ? true : false;
	}
}

```
类似的题目 [[ThreeInOneLCCI]]

## 链式栈
也可以使用链表来实现栈。链表有头尾两边，栈限制了只能在一边操作，在尾部删除操作不太好实现，因此用链表的头来表示栈顶实现链式栈。
```java
public class LinkedListStack{
	private ListNode head = null;
	
	public void push(int x) {
		ListNode newHead = new ListNode(x);
		newHead.next = head;
		head = newHead;
		}		
	}

	public int pop() {
		if (isEmpty())
			return -1;
		int val = head.val;
		head = head.next;
		return val;

	}

	public int peek() {
		if (isEmpty())
			return -1;
		return head.val;
	}

	public boolean isEmpty() {
		return head == null ? true : false;
	}	
}
```



# 队列
限制操作，只能在头部取数据，尾部插入数据。或者数一个队列，进入enqueue的叫尾部，出dequeue的叫头部。
queue, `[1,2,3]`  `1`- front; `3`-back; 
- `offer/add(v)`, Append element `v` to the **back** of the list. `[1,2,3,v]`
- `poll/remove()`, changes the list `[1, 2, 3]` to `[2, 3]` and returns (**front**) `1`




队列也有两种实现方式，数组和链表
## 循环队列
基于数组一般实现为循环队列。head指针，tail指针。
```java
public class CircularQueue{
	private int[] item;
	private int n;
	private int head = 0, tail = 0;
	public CircularQueue(int n){
		this.n = n;
		item = new int[n];
	}

	public boolean enqueue(int x){
		if((tail+1)%n==head)
			return false;
		item[tail] = x;
		tail = (tail + 1)/n;
		return truel;
	}

	public int dequeue(int x){
		if(head == tail)
			return -1;
		int val = item[head];
		head = (head + 1)% n;
		return val;
	}
}
```
## 链式队列
基于链表实现队列。 依然使用head指针和tail 指针。
注意处理空链表


## java Queue 
不同于`Stack`是一个类，`Queue`是一个接口。
`public interface queue<E> extends Collection<E>;`
具体实现有LinkedList、PriorityQueue以及一些多线程使用的BlokcingQueue




# Java Deque Interface

 A deque is a list that supports insertion and removal at **both ends**. Thus, a deque can be used as a queue or as a stack.Java has interface `Deque`. It is implemented by classes `ArrayDeque` (which implements a list in an expandable array) and `LinkedList`.

So these two classes can be used for a queue and for a stack. Both ArrayDeque and LinkedList also implement interface `Queue`.

通常使用Queue 和 Stack 来约束使用
`Queue q= new LinkedList<>();`
use Queue for the LinkedList and operations are **restricted to queue operations**.

There is no suitable way to restrict the operations of an ArrayDeque to stack operations, so we prefer to use class `Stack<E>`.

| Stack方法 | **等效 `Deque` 方法** |
| --------- | --------------------- |
| push      | addFirst              |
| Pop       | removeFirst           |
| peek      | peekFirst             |

| `Queue` 方法  | 等效 `Deque` 方法 | 备注                                     |
| ------ | ----------- | ------------------------------------------------------------ |
| add    | addLast     | throw an `IllegalStateException` if no space is currently available (and otherwise return `true`) |
| **offer**  | offerLast   | `offer()` will return `false` if the element couldn't be inserted due to capacity restrictions. |
|        |             |                                                |
| remove | removeFirst | If the **queue** is empty, **element** throws `NoSuchElementException` |
| **poll**   | pollFirst   | return null without exception                                |
|        |             |                                                      |
| element| getFirst    | If the **queue** is empty, **element** throws `NoSuchElementException` |
| **peek**   | peekFirst   | return null without exception                                |

再来理解一下FIFO 和LIFO
stack 和 queue的 peek及pop/poll方法是一致的，都是FO
但是stack.push 是addFirst，queue.offer 是addLast。FI 和 LI的区别。


## 可以使用deque的情况下，不建议使用stack
或者说时不推荐使用vector。

Java Stack 是继承自Vector 基于动态数组实现的线程安全栈
- **Vector 与 ArrayList 基本是一致的，唯一不同的是 Vector 是线程安全的，会在可能出现线程安全的方法前面加上 synchronized 关键字**
- 因为 Vector 实现并发安全的原理是在每个操作方法上加锁，这些锁并不是必须要的，**在实际开发中一般都是通过锁一系列的操作来实现线程安全，也就是说将需要同步的资源放一起加锁来保证线程安全，如果多个 Thread 并发执行一个已经加锁的方法，但是在该方法中又有 Vector 的存在，Vector 本身实现中已经加锁了，双重锁会造成额外的开销**


因此 stack 的性能不如 deque，而deque的功能更强大，没有理由不用。

## Differences in python
注意python 中
- deque的pop去掉的是Last
- deque(0)是头部 deque(-1)是尾部

# 算法考察
实际算法考察中，栈被考察的频率大大高于队列。
- 优先级队列pq 实际上是堆

细分的题目三类
## 直接以栈为背景

### 栈和队列的实现


[[CQueue]]

[[MyStack]]

### 栈排序
[[SortOfStacksLCCI]]

检索最小栈


## 连连消题目
### 字符串连连消
[[RemoveAllAdjacentDuplicatesInString]]

题目：字符串删掉连续3个重复的字符，比如abbbaac返回 c。
解法：两个栈，一个栈里面记录元素，另一个栈里记录对应的计数，达到3，pop。

[[ValidateStackSequences]]


### 求表达式
[[BasicCalculatorII]] 基础，第一次做作好填坑的准备
进阶：
[[BasicCalculator]]
[[BasicCalculatorIII]] 通用详解在这里
[[BasicCalculatorIV]] 很难

[[LongestValidParentheses]]
[[ValidParentheses]]

## 单调栈 mono stack
栈延伸出的一种新的数据结构，比较难

在一维数组中找第一个满足某种条件的数 的场景就是典型的单调栈应用场景

[[DailyTemperatures]] 暴力，单调栈


