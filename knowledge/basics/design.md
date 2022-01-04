### Queue / Stack / Deque in Java Collections

queue, a FIFO list, `[y,c,x]`  ` y`- front; `x`-back; **deletion in the front and insertion at the end**

- `add(v)`, Append element `v` to the **back** of the list. `[y,c,x,v]`
- `remove()`, changes the list `[y, c, x]` to `[c, x]` and returns (**front**) `y`

- peek, peekFirst

stack, a LIFO list, `[x, c, y]`, `y`-top,`x`-bottom;

- `push(v)`: Put `v` onto the stack. This changes the list `[x, c, y] `to `[x, c, y, v]`.
- `pop()`: changes the list `[x, c, y]` to `[x, c]` and returns `y`
- peek, peekFirst



 A deque is a list that supports insertion and removal at **both ends**. Thus, a deque can be used as a queue or as a stack.

#### queue 方法

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



#### stack 方法

| Stack方法 | **等效 `Deque` 方法** |
| --------- | --------------------- |
| push      | addFirst              |
| Pop       | removeFirst           |
| peek      | peekFirst             |



#### Deque / Queue / Stack Interface

Java has interface `Deque`. It is implemented by classes `ArrayDeque` (which implements a list in an expandable array) and `LinkedList`, so these two classes can be used for a queue and for a stack. Both ArrayDeque and LinkedList also implement interface `Queue`.

Thus,  use only q for the LinkedList and operations are **restricted to queue operations**.

`Queue q= new LinkedList<>();`

The problem is that there is no suitable way to restrict the operations of an ArrayDeque to stack operations, so we prefer to use class `Stack<E>`.



#### Differences in python

注意python 中deque的pop去掉的是Last。

Python中deque(0)是头部 deque(-1)是尾部。
