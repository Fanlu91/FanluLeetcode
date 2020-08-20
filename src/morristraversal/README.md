## Morris 遍历
函数的递归调用，递归的深度等于二叉树的高度，也就是说递归导致的调用堆栈的高度等于二叉树的高度
如果二叉树的高度很大，例如搜索引擎把几十亿张网页按照权重来组成二叉树的话，那么二叉树的高度也有几十万，按照传统的中序遍历，需要消耗大量的内存。

Morris遍历法，能以O(1)的空间复杂度实现二叉树的中序遍历。


二叉搜索树中，如果一个结点有前驱结点，那么前驱结点的右指针只有两种情况
- 是空的
- 是这个结点本身（即前驱是它的父结点）
所以我们可以把前驱结点的右指针这一特性利用起来，从而降低空间复杂度

中续遍历如何查找一个节点的前序节点呢？
- 当前节点没有左孩子： 
    - 它是其父节点的右孩子，那么它的前序节点就是它的父节点
    - 它是父节点的左孩子，那么它没有前序节点，自己就是首节点
- 如果该节点有左孩子，从左孩子开始，沿着右孩子指针一直向右到底，得到的节点就是它的前序节点（前序节点的右指针一定是空的）

Morris遍历算法的步骤如下：
1，找到当前节点前序节点，如果前序节点的右孩子是空，那么把前序节点的右孩子指向当前节点，然后进入当前节点的左孩子。
2，如果当前节点的左孩子为空，打印当前节点，然后进入右孩子。
3，如果当前节点的前序节点其右孩子指向了它本身，那么把前序节点的右孩子设置为空，打印当前节点，然后进入右孩子。

在遍历过程中，每个节点最多会被访问两次，一次是从父节点到当前节点，第二次是从前缀节点的右孩子指针返回当前节点，所以Morris遍历算法的复杂度是O(n)。
在遍历过程中，没有申请新内存，因此算法的空间复杂度是O(1).

Morris遍历，由于要把前缀节点的右指针指向自己，所以暂时会改变二叉树的结构
但在从前缀节点返回到自身时，算法会把前缀节点的右指针重新设置为空，所以二叉树在结构改变后，又会更改回来。

整个过程我们就多做一步：将当前节点左子树中最右边的节点指向它，这样在左子树遍历完成后我们通过这个指向走回了 xx，且能再通过这个知晓我们已经遍历完成了左子树，而不用再通过栈来维护，省去了栈的空间复杂度。


可以看 114. Flatten Binary Tree to Linked List ，同样追求原地操作。
501. Find Mode in Binary Search Tree 原地中续遍历的应用。
99


```java
public class MorrisTraval {
    private TreeNode root = null;
    public MorrisTraval(TreeNode r) {
        this.root = r;
    }
    
    public void travel() {
        TreeNode n = this.root;
        
        while (n != null) {
            if (n.left == null) {
                System.out.print(n.vaule + " ");
                n = n.right;
            } else {
                TreeNode pre = getPredecessor(n);
                if (pre.right == null) {
                    pre.right = n;
                    n = n.left;
                }else if (pre.right == n) {
                    pre.right = null;
                    System.out.print(n.vaule + " ");
                    n = n.right;
                }
            }
        }
    }
    
    private TreeNode getPredecessor(TreeNode n) {
        TreeNode pre = n;
        if (n.left != null) {
            pre = pre.left;
            while (pre.right != null && pre.right != n) {
                pre = pre.right;
            }
        }
        
        return pre;
    }   
}
```
