典型的回溯题目[[A-backtracking(dfs)]] ，看到题目后想到回溯的思路和实现模版是基本要求。
- combination类的题目基本都要运用回溯解决


## 可以使用数组尽量不要使用map
```java
Map<Character, String> phoneMap = new HashMap<Character, String>() {
	{
		put('2', "abc");
		put('3', "def");
		put('4', "ghi");
		put('5', "jkl");
		put('6', "mno");
		put('7', "pqrs");
		put('8', "tuv");
		put('9', "wxyz");
	}
};

String[] map = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
```

# 使用StringBuffer
[[JavaDetails#string StringBuilder StringBuffer]]