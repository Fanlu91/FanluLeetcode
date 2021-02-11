# 观察者模式

观察者模式（Observer Design Pattern）也被称为发布订阅模式（Publish-Subscribe Design Pattern）。

Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。

观察者模式是一个比较抽象的模式，根据不同的应用场景和需求，有完全不同的实现方式。



设计模式要干的事情就是解耦。创建型模式是将创建和使用代码解耦，结构型模式是将不同功能代码解耦，行为型模式是将不同的行为代码解耦，具体到观察者模式，它是将观察者和被观察者代码解耦。



### 不同名称

Subject-Observer、Publisher-Subscriber、Producer-Consumer、EventEmitter-EventListener、Dispatcher-Listener。不管怎么称呼，只要应用场景符合刚刚给出的定义，都可以看作观察者模式。



### 实现方式

**同步阻塞**是最经典的实现方式，主要是为了代码解耦；**异步非阻塞**除了能实现代码解耦之外，还能提高代码的执行效率；**进程间**的观察者模式解耦更加彻底，一般是基于**消息队列**来实现，用来实现不同进程间的被观察者和观察者之间的交互。



而基于消息队列的实现方式，被观察者和观察者解耦更加彻底，两部分的耦合更小。被观察者完全不感知观察者，同理，观察者也完全不感知被观察者。被观察者只管发送消息到消息队列，观察者只管从消息队列中读取消息来执行相应的逻辑。



### 生产者消费者与观察者模式的区别

生产消费模型以异步形式实现，消费者之间存在竞争关系。发布订阅以同步或异步的方式实现，订阅者之间没有竞争关系。

最大的区别在于：发布者（可观测对象）是知道订阅者（观察对象）的存在，因为它需要遍历订阅列表去发布事件；而生产消费模型因为有中间消息代理的存在，生产者和消费者完全不知道对方的存在，完全解耦。





### EventBus 框架实现的观察者模式

实现思路大致一样，都需要定义 **Observer**，并且通过 register() 函数注册 Observer，也都需要通过调用某个函数（比如，EventBus 中的 post() 函数）来给 Observer 发送消息（在 EventBus 中消息被称作事件 event）。

在实现细节方面，它们又有些区别。基于 EventBus，我们不需要定义 Observer 接口，任意类型的对象都可以注册到 EventBus 中，通过 @Subscribe 注解来标明类中哪个函数可以接收被观察者发送的消息。



Guava EventBus 的几个主要的类和函数

1. EventBus、AsyncEventBus

```java
EventBus eventBus = new EventBus(); // 同步阻塞模式
EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(8))；// 异步阻塞模式
```

Guava EventBus 对外暴露的所有可调用接口，都封装在 EventBus 类中。其中，EventBus 实现了同步阻塞的观察者模式，AsyncEventBus 继承自 EventBus，提供了异步非阻塞的观察者模式。

2. register() 函数 unregister() 函数

EventBus 类提供了 register() 函数用来注册观察者，它可以接受任何类型（Object）的观察者。而在经典的观察者模式的实现中，register() 函数必须接受实现了同一 Observer 接口的类对象。

```java
public void register(Object object);
public void unregister(Object object);
```

3. post() 函数

EventBus 类提供了 post() 函数，用来给观察者发送消息。

```java
public void post(Object event);
```

跟经典的观察者模式的不同之处在于，当我们调用 post() 函数发送消息的时候，并非把消息发送给所有的观察者，而是发送给**可匹配的观察者**。所谓可匹配指的是，能接收的消息类型是发送消息（post 函数定义中的 event）类型的**父类**。具体来讲AObserver 能接收的消息类型是 XMsg，BObserver 能接收的消息类型是 YMsg，CObserver 能接收的消息类型是 ZMsg。其中，XMsg 是 YMsg 的父类。当我们如下发送消息的时候，相应能接收到消息的可匹配观察者如下所示：

```java
XMsg xMsg = new XMsg();
YMsg yMsg = new YMsg();
ZMsg zMsg = new ZMsg();
post(xMsg); => AObserver接收到消息
post(yMsg); => AObserver、BObserver接收到消息
post(zMsg); => CObserver接收到消息
```

4. @Subscribe 注解

EventBus 通过 @Subscribe 注解来标明，某个函数能接收哪种类型的消息。

```java
public DObserver {
  //...省略其他属性和方法...
  
  @Subscribe
  public void f1(PMsg event) { //... }
  
  @Subscribe
  public void f2(QMsg event) { //... }
}
```

当通过 register() 函数将 DObserver 类对象注册到 EventBus 的时候，EventBus 会根据 @Subscribe 注解找到 f1() 和 f2()，并且将两个函数能接收的消息类型记录下来（PMsg->f1，QMsg->f2）。当我们通过 post() 函数发送消息（比如 QMsg 消息）的时候，EventBus 会通过之前的记录（QMsg->f2），调用相应的函数（f2）。