这道题目的关键在于要善于在遍历过程中进行标记。

我们把问题拆分一下，先考虑完全猜中，这部分其实相当简单。关键在于要标记已经匹配的字符，后续不要再使用。

剩下的就是处理伪猜中。
处理伪猜中同样要做循环遍历匹配，像处理猜中字符时一样，已经匹配上的，要标记掉后续不再重复使用。需要注意的是
- 这里没有标记s是因为外层循环不会重复，标记不标记意义不大
- 两次要使用不同的标记，防止自己的标记相互匹配

```java
public int[] masterMind(String solution, String guess) {
    int[] res = new int[2];
    char[] s = solution.toCharArray(), g = guess.toCharArray();
    for (int i = 0; i < s.length; i++) {
        if (s[i] == g[i]) {
            res[0]++;
            // 已经算作猜中，则不能再后续伪踩中过程中使用，用x标记
            s[i] = 'x';
            g[i] = 'x';
        }
    }
    for (int i = 0; i < s.length; i++) {
        if (s[i] == 'x')
            continue;
        for (int j = 0; j < g.length; j++) {
            if (g[j] == s[i]) {
                res[1]++;
                g[j] = '0'; // guess中的结果已经被算作伪猜中，不能重复被计算，用0标记
                break;
            }
        }
    }
    return res;
}
```