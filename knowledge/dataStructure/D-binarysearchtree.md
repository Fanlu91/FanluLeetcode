# 二叉搜索树

想有序地存储数据或者需要同时执行搜索、插入、删除等多步操作，二叉搜索树这个数据结构是一个很好的选择，即便在最坏的情况下，也允许你在O(h)的时间复杂度内执行所有操作。

## 中续遍历
二叉搜索树的中序遍历刚好可以输出一个升序数组，它可以作为验证二叉树是否是搜索二叉树的充要条件。

因为上述特性，二叉搜索树的遍历多用中续遍历。

遍历过程中，可能结合其他数据结构-数组、队列、栈 来帮助实现特殊场景。
对递归、数据结构的应用都是很大的考验。

## 基本操作

二叉搜索树存，主要为了支持三个操作：搜索、插入和删除

### 搜索
- 如果目标值等于节点的值，则返回节点;
- 如果目标值小于节点的值，则继续在左子树中搜索;
- 如果目标值大于节点的值，则继续在右子树中搜索。


### 插入
1. 根据节点值与目标节点值的关系，搜索左子树或右子树；
2. 重复步骤 1 直到到达外部节点；
3. 根据节点的值与目标节点的值的关系，将新节点添加为其左侧或右侧的子节点。

这样，我们就可以添加一个新的节点并依旧维持二叉搜索树的性质。

## 删除
删除要比前面两种操作复杂许多。

有许多不同的删除节点的方法，可以通过标记但是不实际删除的方式。

在不引入标记的情况下，下面讨论一种使整体操作变化最小的方法。
我们的方案是用一个合适的子节点来替换要删除的目标节点，根据其子节点的个数，我们需考虑以下三种情况：

1. 如果目标节点没有子节点，我们可以直接移除该目标节点。
2. 如果目标节只有一个子节点，我们可以用其子节点作为替换。
3. 如果目标节点有两个子节点，我们需要用其中序后继节点或者前驱节点来替换，再删除该目标节点。
 
**中序前驱/后继节点（in-order predecessor/successor）**
predecessor ： the node with the greatest key smaller than the key of the input node.
successor ：the node with the smallest key greater than the key of the input node.

```
    // One step left and then always right
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    // One step right and then always left
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) 
            root = root.left;
        return root.val;
    }
    
    
```
回顾 `DeleteNodeInABST`

## 其他

递归的时候，应当尽量注意把子问题包含进去，否则逻辑会变得复杂并很可能无法收敛




