# 定义

Trie树的本质，是利用字符串之间的公共前缀，将重复的前缀组合在一起。其中根结点不包含任何信息，每个节点表示字符串中的字符，从根节点到红色节点表示一个字符串（红色节点不都是叶子节点）。 
 ![img](88102406-c061-4156-badb-f42a33f3ab7e.jpg) 
 主要包含两个操作：

1. 将字符串集合构成Trie树，即将字符串插入Trie树的过程
2. 在Trie树中查询一个字符串

# 存储

Trie树是一个多叉树 

## 使用散列思想

经典存储方式，通过一个下标与字符一一映射的数组来存储子节点的指针。

 

```
public class Trie {
    private TrieNode root = new TrieNode('/'); // 存储无意义字符
    // 往 Trie 树中插入一个字符串
    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }
    // 在 Trie 树中查找一个字符串
    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; ++i) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false; // 不存在 pattern
            }
            p = p.children[index];
        }
        if (p.isEndingChar == false)
            return false; // 不能完全匹配，只是前缀
        else
            return true; // 找到 pattern
    }
    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public TrieNode(char data) {
            this.data = data;
        }
    }
}
```

## 优化

这样的做法虽然高效 时间复杂度是 O(k)，k是字符串长度，但是十分消耗内存，因为不管子节点有多少个，都要用26位的数组，这还是仅有小写字母的情况。 
 可以用其他数据类型替换，稍微牺牲一些效率，如有序数组、跳表、散列表、红黑树等。 
 或者可以进行**缩点优化**，对只有一个子节点且此节点不是一个串的结束节点，可以将此节点与子节点合并。

# 适用场景

在一组字符串里查找字符串，Trie树并不一定能表现很好，它对要处理的数据有严格的要求。

1. 串包含的字符集不能太大
2. 字符串的前缀重合比较多
3. 从0开始实现无bug的trie树，很可能将简单问题复杂化，除非必须不建议这样做
4. 通过指针串起来的数据块是不连续的，对缓存不友好，性能上可能打折扣

Trie树其实不适合精准匹配，更适合散列表和红黑树，Trie树比较适合查找前缀匹配的字符串。如搜索关键字提示、输入自动补全、代码补全等