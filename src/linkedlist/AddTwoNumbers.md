#must
[[D-linkedlist]]

难点在于识别和处理corner case

合并链表时的一些经典问题
- 考虑链表的头尾
- 考虑节点为null的情况


做加法时可能出现的 carry 进位让问题变得更复杂
- 若有下一个节点，节点的值+1
	- 节点值增加后可能又出现进位
- 若无下一个节点，要新建一个值为1的节点，并关联上。这时考虑可能需要保留pre节点用以链接。



