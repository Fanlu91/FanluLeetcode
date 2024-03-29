# 定义

散列表即哈希表 hash table 
散列表用的是数组支持**按照下标随机访问数据**的特性，所以散列表其实就是数组的一种扩展，由数组演化而来。 
散列思想，不直接使用对象的某个值作为数组下标，而是经过计算。这个值称为键Key或者关键字，把编号转化为数组下标的映射方法叫做散列函数，散列函数计算得到的值叫做散列值Hash。

散列表的两个核心问题是散列函数设计和散列冲突解决。


# 散列函数

hash（key）三点基本设计要求

1. 散列函数计算得到的散列值是一个非负整数
2. 如果key1=key2，则hash（key1）=hash（key2）
3. 如果key1！=key2，则hash（key1）！=hash（key2） 
   第三点考虑到计算成本和时间成本，是很难做到的，一旦出现就会形成**散列冲突**。

## 散列冲突

冲突无法避免，重点是如何解决。一般有两种冲突解决办法，开放寻址open addressing/链表法chain。

### 开放寻址

核心思想是，如果出现散列冲突就重新探测一个空闲位置，将其插入。 
重新探测的办法有多种： 
**线性探测linear probing** 如果当前位置被占用，就依次向后找，看是否有空闲位置，直到找到为止。插入操作如此，查询操作类似，通过散列函数找到元素键值对应的散列值，然后比较数组下标为散列值的元素和要找的元素，如果不相等就顺序依次向后找，如果遍历到空闲位置还没有，就说明不存在（这种遍历方法要求删除元素不能直接置空，而是标记为deleted，遇到deleted继续向下探测）。 
**二次探测 quadratic probing**，跟线性探测很像，只不过步长变为原来的二次方。 
**双重散列 double hashing**，不仅使用一个散列函数，而是使用一组散列函数，第一个计算得到的位置被占用，则用第二个，依此类推直到找到空闲。

不管哪种方式，当散列表中空闲位置不多的情况下，冲突的概率会大大提高。为了保证操作效率，要保证散列表中存在一定的空闲位置。 用**装载因子load factor**来表示空位的多少。 
散列表的装载因子 = 填入表中的元素个数/表长度

### 链表法

要简单一些。散列表中，每个位置（桶 bucket/槽slot）会对应一个链表，所有散列值相同的元素存放在相同槽位对应的链表中。 
incomplete

# 若可以用数组代替

使用hashmap前，其实应该先考虑使用数组是否可以解决问题。性能角度来看，hashmap并不应该是第一选择。
数组的优势还在于，其key可以是有序的，这在一些场景下能够提供额外的帮助。

# java 技巧
map初始化内容
```java
Map<Character, Character[]> map = new HashMap<Character, Character[]>() {  
	{  
		put('2', new Character[]{'a', 'b', 'c'});  
		put('3', new Character[]{'d', 'e', 'f'});  
		put('4', new Character[]{'g', 'h', 'i'});  
		put('5', new Character[]{'j', 'k', 'l'});  
		put('6', new Character[]{'m', 'n', 'o'});  
		put('7', new Character[]{'p', 'q', 'r', 's'});  
		put('8', new Character[]{'t', 'u', 'v'});  
		put('9', new Character[]{'w', 'x', 'y', 'z'});  
	}  
};

```
map更新key的方法，可以用merge、compute 代替put
```java

map1.put(a + b, map1.getOrDefault(a + b, 0) + 1);

// map.merge(key, msg, String::concat)
map1.merge(a + b, 1, Integer::sum);
map1.compute(a + b, (k, v) -> v == null ? 1 : v + 1);

```

