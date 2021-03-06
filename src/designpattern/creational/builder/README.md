
# 建造者模式
或者**构建者**模式，也有人叫它**生成器**模式。

原理和代码实现非常简单，掌握起来并不难，难点在于应用场景。

建造者模式有两种实现方法，一种是单独定义一个 Builder 类，另一种是将 Builder 实现为原始类的内部类。



### 应用场景分析

- 若构造函数的参数列表过长，在使用构造函数的时候，我们就容易搞错各参数的顺序，传递进错误的参数值，导致非常隐蔽的 bug。

- 用 set() 函数来给成员变量赋值，以替代冗长的构造函数，校验这些必填项是否已经填写的逻辑就无处安放

- 假设配置项之间有一定的依赖关系，判断逻辑无处安放

- 如果想要不可变对象，对象在创建好之后，就不能再修改内部的属性值

- 使用建造者模式创建对象，还能避免对象存在无效状态。



### 缺点

使用建造者模式来构建对象，代码实际上是有点重复的，类中的成员变量，要在 Builder 类中重新再定义一遍。

### 与工厂模式的区别

工厂模式是用来创建不同但是相关类型的对象（继承同一父类或者接口的一组子类），由给定的参数来决定创建哪种类型的对象。

建造者模式是用来创建一种类型的复杂对象，通过设置不同的可选参数，“定制化”地创建不同的对象。



实际上我们也不要太学院派，非得把工厂模式、建造者模式分得那么清楚，我们需要知道的是，每个模式为什么这么设计，能解决什么问题。只有了解了这些最本质的东西，我们才能不生搬硬套，才能灵活应用，甚至可以混用各种模式创造出新的模式，来解决特定场景的问题。

