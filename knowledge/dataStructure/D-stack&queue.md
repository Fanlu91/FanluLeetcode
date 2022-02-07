

# 栈
后进先出LIFO，操作受限只允许在顶端（栈顶）插入和删除数据。
stack  `[1, 2, 3]`, `1`-bottom,`3`-top;
- `push(v)`: Put `v` onto the stack. This changes the list `[1, 2, 3] `to `[1, 2, 3，v]`.
- `pop()`: changes the list `[1, 2, 3]` to `[1，2]` and returns `3`
- `peek()`, `peekFirst()`，返回 `3` 并不弹出。

栈两种实现方式，数组栈和链表栈。
## 顺序栈
数组栈即顺序栈，数组最大下标是`top`
以 int 元素为例
```java
public class ArrayStack{
	private int[] item;
	private int count; // 当前栈中元素个数
	private int n; // 栈大小

	public ArrayStack(int n){
		item = new int[n];
		this.n = n;
		this.count = 0;
	}

}

```
##  链式栈

# 队列
先进先出FIFO，
循环队列，链式队列，Java Queue

队列也有两种实现方式，数组栈和链表栈。



# Java stack & Queue

## Queue 

queue, `[y,c,x]`  ` y`- front; `x`-back; 
- FIFO list
-  插入在最后，删除从最前
	- `add(v)`, Append element `v` to the **back** of the list. `[y,c,x,v]`
	- `remove()`, changes the list `[y, c, x]` to `[c, x]` and returns (**front**) `y`
	- peek, peekFirst

| **`Queue` 方法 ** | **等效 `Deque` 方法** | 备注                                                         |
| ----------------- | --------------------- | ------------------------------------------------------------ |
| add               | addLast               | throw an `IllegalStateException` if no space is currently available (and otherwise return `true`) |
| offer             | offerLast             | `offer()` will return `false` if the element couldn't be inserted due to capacity restrictions. |
|                   |                       |                                                              |
| remove            | removeFirst           | If the **queue** is empty, **element** throws `NoSuchElementException` |
| poll              | pollFirst             | return null without exception                                |
|                   |                       |                                                              |
| element           | getFirst              | If the **queue** is empty, **element** throws `NoSuchElementException` |
| peek              | peekFirst             | return null without exception                                |



## Stack



 A deque is a list that supports insertion and removal at **both ends**. Thus, a deque can be used as a queue or as a stack.

| Stack方法 | **等效 `Deque` 方法** |
| --------- | --------------------- |
| push      | addFirst              |
| Pop       | removeFirst           |
| peek      | peekFirst             |



## Deque / Queue / Stack Interface

Java has interface `Deque`. It is implemented by classes `ArrayDeque` (which implements a list in an expandable array) and `LinkedList`, so these two classes can be used for a queue and for a stack. Both ArrayDeque and LinkedList also implement interface `Queue`.

Thus,  use only q for the LinkedList and operations are **restricted to queue operations**.

`Queue q= new LinkedList<>();`

The problem is that there is no suitable way to restrict the operations of an ArrayDeque to stack operations, so we prefer to use class `Stack<E>`.



## Differences in python

注意python 中deque的pop去掉的是Last。
Python中deque(0)是头部 deque(-1)是尾部。


# 相互实现
队列基于链表构成，LinkedList可以两头操作，所以用一个队列就可以模拟栈，实际上如果想要用栈模拟队列，则必须用两个栈才可以。