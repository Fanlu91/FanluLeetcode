[[A-greedy]]

这道题目的难点有两个，一是理解题意，二是在此基础上实现算法。我们来仔细看一下


# 1. 题意解读
  
## 1.1 什么是CIDR

CIDR，Classless Inter-Domain Routing 即无类别域间路由，也叫无分类编址。如果计算机网络知识深厚的朋友可能看了标题就知道是什么意思，不懂的我们就只能跟着题目给出的例子使劲研究一下，此为难点之一。

简单来讲就是使用一个**前缀**（可以理解是一个起点ip）加上可变长的**子网掩码**（即mask）来表示ip段的东西。

举个例子，CIDR 255.0.0.8/29 包含如下8个地址

```

255.0.0.8 -> 11111111 00000000 00000000 00001000

255.0.0.9 -> 11111111 00000000 00000000 00001001

...

255.0.0.15 -> 11111111 00000000 00000000 00001111

```

为什么是8个地址？ 这是因为mask有29位，没有被遮掩的地址是`2^(32-29) == 8`个。再比如若mask是32，那只包含1个地址了。


## 1.2 题目到底想让我干嘛？

题目要求的“返回用列表（最小可能的长度）表示的 CIDR块的范围” 这句不太读得懂的中文的实际意思就是，请你**把起始 IP 以及后面的 n 个地址用CIDR段表示，同时用尽量少的CIDR段**。

  
尽量少这个要求比较好理解，因为尽量多的话，你只需要返回一个个的`ip/32`的组合，可以实质上无视mask/CIDR。

  
所以题目是想让我们把 start 及其后面的 n 个ip给变成CIDR段的形式表示。注意里面不能包含 n 之外的ip。


# 2 解法

## 2.1 找CIDR就是找 start 和 mask

官方题解的思路是没有问题的，奈何描述稍微有些模糊。

结合一些子网掩码mask的工作原理知识（mask与运算，如果不明白的同学可以自行了解一下），题目中隐含的最关键的一个点在于，**CIDR段的起始ip的末尾必须是由 （32 - mask） 个 0 组成**，来看 `CIDR 255.0.0.8/29 `：

```

255.0.0.8 -> 11111111 00000000 00000000 00001000

```

  

若必须以`255.0.0.9`这样的地址作为**起点地址**

```

255.0.0.9 -> 11111111 00000000 00000000 00001001

```

你只能使用 `255.0.0.9/32` 来表示它一个，没有其他能以它**开头**的段。



## 2.2 贪心解法

明白了上面的规则，我们才能真正开始将一堆连续ip 转化为 CIDR段的贪心寻找：


1. 拿当前的起点ip做`start`

2. 想要用的段数少，那我们必须用尽量大的`mask`

- 通过start末尾0的数量来判断可以使用的mask长度上限

- mask长度上限同时还受剩余ip数 n 的影响，因为我们不能包含多余的ip

3. 用它们组成可以组成的最大 CIDR

4. 根据组成的CIDR段中的ip数量将n缩小，把start改为CIDR之后的第一个ip

5. 如果n还不为0，重复上述过程。

  

这样我们就可以较为直接的穷举出题目的解。相信有了上面的分析，下面的代码应该不难理解。

btw这题应该是中等难度，新手看不懂也不需要气馁。

  

```java
    public List<String> ipToCIDR1(String ip, int n) {
        public List<String> ipToCIDR (String ip,int n){
        int start = toInt(ip);//将ip转化为整数
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            int trailingZeros = Integer.numberOfTrailingZeros(start);
            System.out.println(trailingZeros);
            int mask = 0, bitsInCIDR = 1;
            //计算mask
            while (bitsInCIDR < n && mask < trailingZeros) {
                bitsInCIDR <<= 1;
                mask++;
            }
            if (bitsInCIDR > n) {
                bitsInCIDR >>= 1;
                mask--;
            }

            ans.add(toString(start, 32 - mask));
            n -= bitsInCIDR;
            start += (bitsInCIDR);
        }
        return ans;
    }

    private String toString(int number, int range) {
        final int WORD_SIZE = 8;
        StringBuilder buffer = new StringBuilder();
        for (int i = 3; i >= 0; --i) {
            buffer.append(((number >> (i * WORD_SIZE)) & 255));
            buffer.append(".");
        }
        buffer.setLength(buffer.length() - 1);
        buffer.append("/").append(range);
        return buffer.toString();
    }

    private int toInt(String ip) {
        String[] strs = ip.split("\\.");
        int sum = 0;
        for (String str : strs) {
            sum *= 256;
            sum += Integer.parseInt(str);
        }
        return sum;
    }
```

题目描述中n 小于1000，所以使用32位的int表示地址即可，即使计算过程中高位溢出，也不影响低位的运算（0，1判断）。

# 3 延伸
## IP地址计算
java提供了Inet4Address 给我们使用。
ip本质上来讲是个32位的unsigned int，使用int存储高位是会越界，应当使用Long。


Ip2数字
```java
private int ipAddressToInt(String ip) throws UnknownHostException {
    Inet4Address inet4Address = (Inet4Address) Inet4Address.getByName(ip);
    int intRepresentation = ByteBuffer.wrap(inet4Address.getAddress()).getInt();
	return intRepresentation;
}
```

数字toIP
```java

private String intToIpAddress(int intRepresentation) throws UnknownHostException {
	InetAddress i = InetAddress.getByName(String.valueOf(intRepresentation));
	String ip = i.getHostAddress();
	return ip;
}

private String longToIP(long x) {  
    return String.format("%s.%s.%s.%s",  
 x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);  
}

private String toString(int number) {
	final int WORD_SIZE = 8;
	StringBuilder buffer = new StringBuilder();
	for (int i = 3; i >= 0; --i) {
		// 取出每一个字节
		buffer.append(((number >> (i * WORD_SIZE)) & 255));
		buffer.append(".");
	}
	buffer.setLength(buffer.length() - 1);
	return buffer.toString();
}
```