
# tree.trie 字典树
[[D-tree.trie]]
## tree.trie 第一版 
一开始使用 tree.trie 写了一个解法，基本上就是手写了 tree.trie 的 insert 以及适应本题题意的 find。代码如下，如果你懂 tree.trie 的基本知识应该并不难读懂：
```java
public boolean wordBreak(String s, List<String> wordDict) {
	TrieNode root = new TrieNode('/');
	for (String word : wordDict) {
		TrieNode tmp = root;
		for (char c : word.toCharArray()) {
			int index = c - 'a';
			if (tmp.children[index] == null)
				tmp.children[index] = new TrieNode(c);
			tmp = tmp.children[index];
		}
		tmp.isEndingChar = true;
	}

	int index = 0;
	TrieNode p = root;
	while (index < s.length()) {
		p = root;
		while (true) {
			int i = s.charAt(index) - 'a';
			if (p.children[i] == null) {
				return false;
			}
			p = p.children[i];
			index++;
			if (p.isEndingChar)
				break;
			if (index == s.length())
				return false;
		}
	}
	return true;
}

class TrieNode {
	public char data;
	public TrieNode[] children = new TrieNode[26]; // 使用数组保存结果
	public boolean isEndingChar = false;

	public TrieNode(char data) {
		this.data = data;
	}
}
```

提交之后发现报错，下面的测试用例没有过
```less
"aaaaaaa" ["aaaa","aaa"]
```

这时才意识到了问题，由于不像典型 tree.trie 在find时已经有了分词，一直在循环遍历如果一个词的部分前缀包含在词组里面，即在前缀词处中断了，则后面的永远访问不到。
```java
if (p.isEndingChar) // aaaa 在 aaa处已经被break屌
	break;
```
意识到了线性的搜索是不够的，得利用 dfs 的方式去找。

## tree.trie 第二版，结合回溯
这一版引入了 tree.trie 的结构，代替了原本仅使用一个 trienode的方式，在需要递归查询的情况下这样更加方面。
同时 find 方法做了改进，引入了起始位置 i 的设计，每次遇到 isEndingChar 时，若 find(s, i + 1) 返回结果为true，则说明余下的字符串成功匹配，直接返回true。否则继续匹配剩下的字符。

```java
// TrieNode 结构一样，略去

public boolean wordBreak(String s, List<String> wordDict) {
	Trie tree.trie = new Trie();

	for (String word : wordDict)
		tree.trie.insert(word.toCharArray());
	return tree.trie.find(s, 0);
}

class Trie {
	private TrieNode root = new TrieNode('/');
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

	public boolean find(String s, int i) {
		if (i >= s.length())
			return true;
		TrieNode p = root;
		for (; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (p.children[index] == null) {
				return false;
			}
			p = p.children[index];
			if (p.isEndingChar) {
				if (find(s, i + 1))
					return true;
			}
		}
		return false;
	}
}
```


这个解法结果还是不行，提交之后遇到了TLE，测试案例:
```less
wordBreak.wordBreak1("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",  
 Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");
```
此案例显然是“故意设计”出来的，为的就是显著劣化 tree.trie 的效果，这时我们的算法直接退回到纯暴力搜索，难受。

虽然效率劣化了，但是还不至于TLE，我们继续借助起始位置 i 的设计引入记忆结构，避免掉很多重复计算试一试。

## tree.trie 第三版，回溯结合记忆

`wordBreak` 和`TireNode`  包括`find()`都不需要动，我们在 tree.trie 结构及 find 方法中引入记忆机构即可。

```java
class Trie {
	private TrieNode root = new TrieNode('/');
	boolean[] failed = new boolean[301]; // s.length <= 300

	public boolean find(String s, int i) {
		if (failed[i])
			return false;
		if (i >= s.length())
			return true;
		TrieNode p = root;
		for (; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (p.children[index] == null) {
				return false;
			}
			p = p.children[index];
			if (p.isEndingChar) {
				if (find(s, i + 1))
					return true;
				failed[i + 1] = true; // 记忆计算结果
			}
		}
		return false;
	}
}
```

其实就多了三四行代码，最终版本代码（下面）效率超过了99%的答案，1ms就完成了！
```java
// 1ms
public boolean wordBreak(String s, List<String> wordDict) {
	Trie tree.trie = new Trie();

	for (String word : wordDict)
		tree.trie.insert(word.toCharArray());

	return tree.trie.find(s, 0);
}

class TrieNode {
	public char data;
	public TrieNode[] children = new TrieNode[26];
	public boolean isEndingChar = false;

	public TrieNode(char data) {
		this.data = data;
	}
}

class Trie {
	private TrieNode root = new TrieNode('/');
	boolean[] failed = new boolean[301]; // s.length <= 300

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

	public boolean find(String s, int i) {
		if (failed[i])
			return false;

		if (i >= s.length())
			return true;
		TrieNode p = root;
		for (; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (p.children[index] == null) {
				return false;
			}
			p = p.children[index];
			if (p.isEndingChar) {
				if (find(s, i + 1))
					return true;
				failed[i + 1] = true;
			}
		}
		return false;
	}
}
```
一般来讲任何题目，除非有特别取巧的解法，记忆化递归一般都属于最高效的解法之一。
# 动态规划
[[A-dynamicprogramming]]
写完上面的解法我们可能会意识到，这个题目其实压根**不需要 tree.trie**，直接记忆化递归 或者说 动态规划求解应该也行。

代码反而非常简单，直接来看：
```java
// 8 ms
public boolean wordBreak(String s, List<String> wordDict) {
	boolean[] dp = new boolean[s.length() + 1];
	dp[0] = true;
	for (int r = 1; r < s.length() + 1; r++) {
		for (int l = 0; l < r; l++) {
			if (dp[l] == true && wordDict.contains(s.substring(l, r))) {
				dp[r] = true;
			}
		}
	}
	return dp[s.length()];
}
```

可以理解为使用`wordDict.contains(s.substring(l, r))`代替了 tree.trie 的搜索，
`l`和`r`分别表示一个词的左右两端，注意`substring`方法对r的要求
- jdk 中的例子 "smiles".substring(1, 5) returns "mile"

动态规划虽然代码简洁，其实效率并不算高。

以上就是这道题的所有解法，希望对你有所帮助。


