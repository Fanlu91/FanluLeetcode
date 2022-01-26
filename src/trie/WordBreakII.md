做这道题之前，强烈建议先做[[WordBreak]]。 思路相对一致，只不过细节稍微多一些，相比word break的dfs搜索返回，这里需要枚举结果。

# trie 字典树+ 回溯
[[D-trie]] [[A-backtracking(dfs)]]
```java
// 1ms
public List<String> wordBreak(String s, List<String> wordDict) {
	List<String> res = new LinkedList<>();
	Trie trie = new Trie(res);
	for (String word : wordDict)
		trie.insert(word);
	trie.findAll(s, 0, new StringBuilder());
	return res;
}

class TrieNode {
	public String data;
	public TrieNode[] children = new TrieNode[26];
	public boolean isEndingChar = false;

	public TrieNode(String data) {
		this.data = data;
	}
}

class Trie {
	private TrieNode root = new TrieNode("/");
	List<String> res;

	public Trie(List<String> res) {
		this.res = res;
	}

	public void insert(String word) {
		char[] text = word.toCharArray();
		TrieNode p = root;
		for (int i = 0; i < text.length; ++i) {
			int index = text[i] - 'a';
			if (p.children[index] == null) {
				TrieNode newNode = new TrieNode(word);
				p.children[index] = newNode;
			}
			p = p.children[index];
		}
		p.isEndingChar = true;
		p.data = word;
	}

	public void findAll(String s, int i, StringBuilder stringBuilder) {
		if (i == s.length()) {
			res.add(stringBuilder.toString().trim());
			return;
		}

		TrieNode p = root;
		for (; i < s.length(); i++) {
			int index = s.charAt(i) - 'a';
			if (p.children[index] == null) 
				return;
			p = p.children[index];
			if (p.isEndingChar) {
				String word = p.data + " ";
				stringBuilder.append(word);
				findAll(s, i + 1, stringBuilder);
				stringBuilder.delete(stringBuilder.length() - word.length(), stringBuilder.length());
			}
		}
		return;
	}
}
```


# 纯回溯

```java
// 0ms
public List<String> wordBreak(String s, List<String> wordDict) {
	List<String> res = new LinkedList<>();
	Set<String> set = new HashSet<>(wordDict);
	dfs(new StringBuilder(), 0, set, s, res);
	return res;
}

private void dfs(StringBuilder sb, int start, Set<String> set, String s, List<String> res) {
	if (start == s.length()) {
		res.add(sb.toString().trim());
		return;
	}
	for (int i = start; i < s.length(); i++) {
		String sub = s.substring(start, i + 1);
		if (set.contains(sub)) {
			int l = sb.length();
			sb.append(sub).append(" ");
			dfs(sb, i + 1, set, s, res);
			sb.setLength(l);
		}
	}

}
```