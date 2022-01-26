#must 

双指针
```java
// 2ms
public String reverseWords(String s) {
	s = s.trim();
	StringBuilder sb = new StringBuilder();
	int l = s.length() - 1, r = l;
	while (l > 0) {
		if (s.charAt(l) == ' ') {
			if (l != r)
				sb.append(s.substring(l + 1, r + 1)).append(" ");
			l--;
			r = l;
		} else {
			if (s.charAt(r) == ' ')
				r = l;
			l--;
		}
	}
	sb.append(s.substring(0, r + 1));

	return sb.toString();
}
```

先split
```java
// 1ms
public String reverseWords(String s) {
	StringBuilder sb = new StringBuilder();
	String[] array = s.split(" ");
	for(int i = array.length - 1;i>=0;i--){
//		System.out.println(".."+array[i]+"..");
		if(array[i].length()!=0)
			sb.append(array[i]).append(" ");
	}
	return sb.toString().trim();
}

```

注意，两个空格split，会spilt出空字符串
```
"a good  example"

..example..
....
....
..good..
..a..

```