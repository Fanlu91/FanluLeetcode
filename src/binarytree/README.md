# 二叉树

## 三种深度优先遍历。

### what is the "order" in preorder inorder postorder?

pre 前 / in 中 / post 后 corresponds to the order of root visiting.

Preorder (Root, Left, Right) 
Inorder (Left, Root, Right)

- 对于二叉搜索树，我们可以通过中序遍历得到一个递增的有序序列。
Postorder (Left, Right, Root) 
- 当你删除树中的节点时，删除过程将按照后序遍历的顺序进行。
- 后序在数学表达中被广泛使用，编写程序来解析后缀表示法更为容易：使用栈来处理表达式，每遇到一个操作符，就可以从栈中弹出栈顶的两个元素，计算并将结果返回到栈中。


## 递归
递归是树的一大特性。
需要掌握并灵活运用自顶而下/自底向上的递归
https://leetcode-cn.com/explore/learn/card/data-structure-binary-tree/3/solve-problems-recursively/11/

当遇到树问题时，请先思考一下两个问题：

你能确定一些参数，从该节点自身解决出发寻找答案吗？
你可以使用这些参数和节点本身的值来决定什么应该是传递给它子节点的参数吗？
如果答案都是肯定的，那么请尝试使用 “自顶向下” 的递归来解决此问题。

或者你可以这样思考：对于树中的任意一个节点，如果你知道它子节点的答案，你能计算出该节点的答案吗？ 
如果答案是肯定的，那么 “自底向上” 的递归可能是一个不错的解决方法。

## 二叉树与递归
二叉树算法的设计的总路线：明确一个节点要做的事情，然后剩下的事抛给框架。
