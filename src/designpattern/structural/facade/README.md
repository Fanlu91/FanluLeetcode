# 门面模式

Provide a **unified interface** to a set of interfaces in a subsystem. Facade Pattern defines a higher-level interface that makes the subsystem easier to use.
门面模式为子系统提供一组统一的接口，定义一组高层接口让子系统更易用。



应用场景比较明确，主要在接口设计方面使用，关于接口粒度的问题。

假设有一个系统 A，提供了 a、b、c、d 四个接口。系统 B 完成某个业务功能，需要调用 A 系统的 a、b、d 接口。利用门面模式，我们提供一个包裹 a、b、d 接口调用的门面接口 x，给系统 B 直接使用。

接口粒度设计得太大，太小都不好。太大会导致接口不可复用，太小会导致接口不易用。在实际的开发中，接口的可复用性和易用性需要“微妙”的权衡。针对这个问题，我的一个基本的处理原则是，尽量保持接口的可复用性，但针对特殊情况，允许提供冗余的门面接口，来提供更易用的接口。



门面模式定义中的“子系统（subsystem）”也可以有多种理解方式。它既可以是一个完整的系统，也可以是更细粒度的类或者模块。





### 应用场景举例

1. 解决易用性问题

   Linux 系统调用函数就可以看作一种“门面”。它是 Linux 操作系统暴露给开发者的一组“特殊”的编程接口，它封装了底层更基础的 Linux 内核调用。

2. 解决性能问题

   通过将多个接口调用替换为一个门面接口调用，减少网络通信成本，提高 App 客户端的响应速度。

   如果门面接口不多，我们完全可以将它跟非门面接口放到一块，也不需要特殊标记，当作普通接口来用即可。如果门面接口很多，我们可以在已有的接口之上，再重新抽象出一层，专门放置门面接口，从类、包的命名上跟原来的接口层做区分。如果门面接口特别多，并且很多都是跨多个子系统的，我们可以将门面接口放到一个新的子系统中。

3. 解决分布式事务问题

   要支持两个接口调用在一个事务中执行，是比较难实现的，这涉及分布式事务问题。虽然我们可以通过引入分布式事务框架或者事后补偿的机制来解决，但代码实现都比较复杂。

   最简单的解决方案是，利用数据库事务或者 Spring 框架提供的事务（如果是 Java 语言的话），在一个事务中，执行创建用户和创建钱包这两个 SQL 操作。这就要求两个 SQL 操作要在一个接口中完成，所以，我们可以借鉴门面模式的思想，再设计一个包裹这两个操作的新接口，让新接口在一个事务中执行两个 SQL 操作。



### 适配器与门脸

适配器是做接口转换，解决的是原接口和目标接口不匹配的问题。
门面模式做接口整合，解决的是多接口调用带来的问题。

适配器在代码结构上主要是继承加组合，门面模式在代码结构上主要是封装

适配器可以看作是事后行为，是一种“补偿模式”，主要是用来完善设计上的不足，而门面模式是在设计接口时就需要考虑的，是一种事前行为。





设计接口时考虑复用，幂等性，限流，安全
