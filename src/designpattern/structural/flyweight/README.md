# 享元模式 

当一个系统中存在大量**重复对象**的时候，如果这些重复的对象是不可变对象，我们就可以利用享元模式将对象设计成享元，在内存中只保留一份实例，供多处代码引用

对于**相似对象**，我们也可以将这些对象中相同的部分（字段）提取出来，设计成享元，让这些大量相似对象引用这些享元。



“不可变对象”指的是，一旦通过构造函数初始化完成之后，它的状态（对象的成员变量或者属性）就不会再被修改了。不可变对象不能暴露任何 set() 等修改内部状态的方法。之所以要求享元是不可变对象，那是因为它会被多处代码共享使用，避免一处代码对享元进行了修改，影响到其他使用它的代码。



### Java Integer 中的享元

自动装箱（Autoboxing）和自动拆箱（Unboxing）

自动将基本数据类型转换为包装器类型。所谓的自动拆箱，也就是自动将包装器类型转化为基本数据类型。

```java
Integer i = 56; //自动装箱
int j = i; //自动拆箱
```

### 

通过“==”来判定两个对象是否相等的时候，实际上是在判断两个局部变量存储的地址是否相同，换句话说，是在判断两个局部变量是否指向相同的对象。

```java
Integer i1 = 56;
Integer i2 = 56;
Integer i3 = 129;
Integer i4 = 129;
System.out.println(i1 == i2); // true
System.out.println(i3 == i4); // false
```



当我们通过自动装箱，也就是调用 valueOf() 来创建 Integer 对象的时候，如果要创建的 Integer 对象的值在 -128 到 127 之间，会从 `IntegerCache` 类中直接返回，否则才调用 new 方法创建。看代码更加清晰一些，Integer 类的 valueOf() 函数的具体代码如下所示：

```java

public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```

这里的 IntegerCache 相当于生成享元对象的工厂类. Cache to support the object identity semantics of autoboxing for values between * -128 and 127 (inclusive) as required by JLS.



除了 Integer 类型之外，其他包装器类型，比如 Long、Short、Byte 等，也都利用了享元模式来缓存 -128 到 127 之间的数据。



### 享元模式在 Java String 中的应用



```java
String s1 = "小争哥";
String s2 = "小争哥";
String s3 = new String("小争哥");

System.out.println(s1 == s2); // true
System.out.println(s1 == s3); // false
```

JVM 会专门开辟一块存储区来存储字符串常量，这块存储区叫作“字符串常量池”。

Integer 类中要共享的对象，是在类加载的时候，就集中一次性创建好的。但是，对于字符串来说，我们没法事先知道要共享哪些字符串常量，所以没办法事先创建好，只能在某个字符串常量第一次被用到的时候，存储到常量池中，当之后再用到的时候，直接引用常量池中已经存在的即可，就不需要再重新创建了。



### 缺点

享元工厂类一直保存了对享元对象的引用，这就导致享元对象在没有任何代码使用的情况下，也并不会被 JVM 垃圾回收机制自动回收掉。因此，在某些情况下，如果对象的生命周期很短，也不会被密集使用，利用享元模式反倒可能会浪费更多的内存。



享元池用weak reference持有享元对象可以解决上述问题，在某个对象没有任何代码使用的时候，能被 JVM 垃圾回收机制回收掉。