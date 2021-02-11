# 职责链模式

Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request. Chain the receiving objects and pass the request along the chain until an object handles it.

将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。将这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止。



在 GoF 给出的定义中，如果处理器链上的某个处理器能够处理这个请求，那就**不会继续往下传递**请求。实际上，职责链模式还有一种变体，那就是请求会被所有的处理器都处理一遍，**不存在中途终止**的情况



### 常用实现

职责链模式有两种常用的实现。一种是使用链表来存储处理器，另一种是使用数组来存储处理器，后面一种实现方式更加简单。

1. 链表，只记录head和tail，结合模板方法模式，显式调用下一个处理器，具体处理器只要实现自己的处理逻辑即可。
2. 数组列表，将处理器放进一个list里，Java的arraylist底层就是一个数组，for循环调用所有的处理器

### 职责链模式如何应对代码的复杂性

将大块代码逻辑拆分成函数，将大类拆分成小类，是应对代码复杂性的常用方法。

职责链模式的实现方式更加优雅，只需要新添加一个 Filter 类，并且通过 addFilter() 函数将它添加到 FilterChain 中即可，其他代码完全不需要修改。满足开闭原则，提高代码的扩展性。





职责链模式最常用来开发框架的**过滤器**和拦截器





项目开发中，类似权限这样的访问控制功能，我们该选择三者（AOP、Servlet Filter、Spring Interceptor）中的哪个来实现呢？有什么参考标准吗？

1. Servlet Filter是针对Servlet容器里的方法都能生效. 就是说Servlet容器里就算要把Spring换成别的框架，鉴权代码依然能生效.
2. Spring开头的就只能在Spring中生效，但更好还是在interceptor，因为interceptor天然的设计背景就是[在请求前，在响应后.]
3.  如果用AOP实现，就很依赖于AOP的pointcut设置，一不小心就会在[一次请求响应里]执行了[多次重复的鉴权服务]



AOP、Servlet Filter、Spring Interceptor这三者可以从不同权限检查的范围大小的视角来应用：

1. Servlet Filter
    运维部门需要对只供内部访问的服务进行IP限制或访问审查时，在容器这一层增加一个Filter，在发布时发布系统自动加挂这个Filter，这样对上层应用就是透明的，内网IP地址段增减或审查规则调整都不需要上层应用的开发人员去关心。
2. Spring Interceptor
    由框架或基础服务部门来提供的微服务间相互调用的授权检查时，可以提供统一的SDK，由程序员在需要的服务上配置。
3. AOP
    业务应用内权限检查，可以把权限检查在统一模块中实现，通过配置由AOP加插拦截检查。