
```java
public int lengthOfLastWord(String s) {
    s = s.trim();
    int i = s.length() - 1;
    while (s.charAt(i) != ' ') {
        i--;
        if (i <= 0) //i 初始为0，i 可能等于 - 1
            return s.length();
    }
    return s.length() - i - 1;
}
```

