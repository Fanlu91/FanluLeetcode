[[T-primitivetype]]

#practice 

这道题目的难点在于细节较多，尤其是各种异常和溢出情况的判定。

# 数学解法
## 判断溢出
运算过程是逐位左移的，溢出也就发生在某一次左移，我们着重关注这一过程。
假设最右位即本次循环我们从 string 获取的值为 value ，对比移位值 res，我们可以得到结论
```java
res > （Integer.MAX_VALUE - value）/ 10 
```
这里甚至使用整除就可以，因为假设溢出的值大于 7 （其实我们可以知道造成溢出的值大于7，因为MAX int 是2147483647；但这里 7 并不关键，不知道也无所谓）
一类情况时 res 比 （Integer.MAX_VALUE - value）/ 10  多一位，即大差不多10倍，
一类情况 res 和（Integer.MAX_VALUE - value）/ 10  位数一样多，则关键在于比value 
- 如果value小于等于 7，这时并不溢出，res =（Integer.MAX_VALUE - value）/ 10 
- 如果value大于7，这时会溢出：Integer.MAX_VALUE - value 个位不够减，会去十位借位，res 会比（Integer.MAX_VALUE - value）/ 10 大，**且就是大1**。

## 实现
```java
public int myAtoi(String s) {
	// 处理空格
	s = s.trim();
	if (s.length() == 0)
		return 0;
	boolean negative = false;
	// 处理开头的符号/字符
	if (!Character.isDigit(s.charAt(0))) {
		if (s.charAt(0) == '-')
			negative = true;
		else if (s.charAt(0) == '+')
			;
		else
			return 0;
		s = s.substring(1);
	}

	//经过上述处理，此时的第一位已经是数字了，还剩下一个问题就是数字的溢出
	int index = 0, res = 0;
	while (index < s.length() && Character.isDigit(s.charAt(index))) {
		int value = Character.getNumericValue(s.charAt(index));
		// 处理溢出问题
		if (res > (Integer.MAX_VALUE - value) / 10) {
			return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		}
		int tmp = res * 10 + value;

		res = tmp;
		index++;
	}

	return negative ? -res : res;
}
```

# dfa 确定有限状态机解法

[[A-dfa]]

遇到的每一个字符，穷举判断它可能对应的状态，然后根据有限的确定的状态向下遍历即可。
- 有的状态可以自旋，比如start状态遇到空格，数字状态遇到数字
- 有的状态只能朝向固定的方向，比如signed之后只能是数字或者结束，而到达结束状态之后就没有其他状态了。






![[Pasted image 20220106104850.png]]
  

```java
class Solution {
    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

}

class Automaton {
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ?
                    Math.min(ans, (long) Integer.MAX_VALUE) :
                    Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ')
            return 0;
        if (c == '+' || c == '-')
            return 1;
        if (Character.isDigit(c))
            return 2;
        return 3;
    }
}


```

# corner case 
[[T-primitivetype#int取值范围 -2147483648 到 2147483647]]

