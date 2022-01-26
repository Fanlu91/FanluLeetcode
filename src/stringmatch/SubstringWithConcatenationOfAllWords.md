[[A-stringmatch]]

# 暴力法
## 使用set
```java
public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new LinkedList<>();
    int len = words[0].length() * words.length;
    for (int i = 0; i < s.length() - len; i++) {
        if (ifMatch(s, i, words[0].length(), new HashSet<>(Arrays.asList(words))))
            res.add(i);
    }
    return res;
}

private boolean ifMatch(String s, int start, int wordLen, Set<String> set) {
    if (set.isEmpty())
        return true;
    String word = s.substring(start, start + wordLen);
    if (set.contains(word)) {
        set.remove(word);
    } else
        return false;
    return ifMatch(s, start + wordLen, wordLen, set);
}
```

没有处理好重复 word，一次性都去掉了
```less
"wordgoodgoodgoodbestword"
["word","good","best","good"]
```

## 使用map
使用 map 解决重复 word 问题，给word增加一个计数，当计数为0时才像 set 一样去掉。
这样我们就得到了一个可用的解法。

这里要注意 map 反复使用应深拷贝 deep cooy
```java
// 108 ms
public List<Integer> findSubstring(String s, String[] words) {
	List<Integer> res = new LinkedList<>();
	Map<String, Integer> map = new HashMap<>();
	for (String word : words)
		map.put(word, map.getOrDefault(word, 0) + 1);
	for (int i = 0; i < s.length() - words[0].length() * words.length + 1; i++) {
		Map<String, Integer> tmp = new HashMap<>();
		tmp.putAll(map);
		if (ifMatch(s, i, words[0].length(), tmp))
			res.add(i);
	}
	return res;
}

private boolean ifMatch(String s, int start, int wordLen, Map<String, Integer> map) {
	if (map.isEmpty())
		return true;
	String word = s.substring(start, start + wordLen);
	if (map.containsKey(word)) {
		int count = map.get(word);
		if (count == 1)
			map.remove(word);
		else
			map.put(word, --count);
	} else
		return false;
	return ifMatch(s, start + wordLen, wordLen, map);
}
```


#todo 
滑动窗口