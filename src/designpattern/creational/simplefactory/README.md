# 简单工厂设计模式

为了让类的职责更加单一、代码更加清晰，我们将 createParser() 函数剥离到一个独立的类中，让这个类只负责对象的创建。而这个类就是我们现在要讲的简单工厂模式类。

简单工厂模式还叫作静态工厂方法模式（Static Factory Method Pattern）。之所以叫静态工厂方法模式，是因为其中创建对象的方法是静态的。



两种简单工厂模式的实现方法（纯简单工厂、单例与简单工厂结合），如果我们要添加新的 parser，那势必要改动到 RuleConfigParserFactory 的代码，那这是不是违反开闭原则呢？实际上，如果不是需要频繁地添加新的 parser，只是偶尔修改一下 RuleConfigParserFactory 代码，稍微不符合开闭原则，也是完全可以接受的。





### 工厂方法命名

大部分工厂类都是以“Factory”这个单词结尾的，但也不是必须的，比如 Java 中的 DateFormat、Calender。除此之外，工厂类中**创建对象的方法**一般都是 create 开头，比如代码中的 createParser()
但有的也命名为 getInstance()、createInstance()、newInstance()，有的甚至命名为 valueOf()（比如 Java String 类的 valueOf() 函数）等等

在JDK中工厂方法的命名有些规范：

1. valueOf() 返回与入参相等的对象
   例如 Integer.valueOf()
2. getInstance() 返回单例对象
   例如 Calendar.getInstance()
3. newInstance() 每次调用时返回新的对象
   例如 HelloWorld.class.getConstructor().newInstance()
4. 在反射中的工厂方法
   例如 XXX.class.getField(String name) 返回成员

静态工厂方法的优点：

1. 静态工厂方法子类可以继承，但不能重写，这样返回类型就是确定的。可以返回对象类型或者primitive 类型。

2. 静态工厂方法的名字更有意义，例如Collections.synchronizedMap()

3. 静态工厂方法可以封装创建对象的逻辑，还可以做其他事情，让构造方法只初始化成员变量。

4. 静态工厂方法可以控制创建实例的个数。例如单例模式，或者多例模式，使用本质上是可以用静态工厂方法实现。



