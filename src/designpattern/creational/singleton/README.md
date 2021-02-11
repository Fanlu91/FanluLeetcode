一个类只允许创建一个对象（或者实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式。

## 实现单例需要考虑的问题

构造函数需要是 private 访问权限的，这样才能避免外部通过 new 创建实例；
考虑对象创建时的线程安全问题；
考虑是否支持延迟加载；
考虑 getInstance() 性能是否高（是否加锁）。

## 几种Java单例实现方式
### 饿汉式
```java

public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private static final IdGenerator instance = new IdGenerator();
  private IdGenerator() {}
  public static IdGenerator getInstance() {
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}
```
缺点：不支持延迟加载,提前初始化实例可能是一种浪费资源的行为。
当然这可能不是一个问题 
1. 如果初始化耗时长，那我们最好不要等到真正要用它的时候，才去执行这个耗时长的初始化过程，这会影响到系统的性能
2. 如果实例占用资源多，按照 fail-fast 的设计原则，那我们也希望在程序启动时就将这个实例初始化好。如果资源不够，就会在程序启动的时候触发报错，立即去处理。

### 懒汉式
```java

public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private static IdGenerator instance;
  private IdGenerator() {}
  public static synchronized IdGenerator getInstance() {
    if (instance == null) {
      instance = new IdGenerator();
    }
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}
```
缺点：getInstance 加了synchronized。频繁地用到，那频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，这种实现方式就不可取。


### 双重检测
```java

public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private static IdGenerator instance;
  private IdGenerator() {}
  public static IdGenerator getInstance() {
    if (instance == null) {
      synchronized(IdGenerator.class) { // 此处为类级别的锁
        if (instance == null) {
          instance = new IdGenerator();
        }
      }
    }
    return instance;
  }
  public long getId() { 
    return id.incrementAndGet();
  }
}
```
既支持延迟加载、又支持高并发的单例实现方式。
潜在问题： 因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，并且赋值给 instance 之后，还没来得及初始化（执行构造函数中的代码逻辑），就被另一个线程使用了。在并发时，某个请求会去到一个没有实例化完的对象。

JVM的优化和多核CPU会对指令进行reordering，volatile才能解决双检锁问题：http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html


解决方式：
1. 只有低版本的 Java 才会有这个问题。我们现在用的高版本的 Java 已经在 JDK 内部实现中解决了这个问题
（解决的方法很简单，只要把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序）
2. 给 instance 成员变量加上 volatile 关键字，禁止指令重排序

关于1的解决方式
要做到争哥说的“把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序”这一点，搜了一圈只有这个说法靠谱：
In short, we emit a trailing barrier in three cases: ... 3. A field write was detected, and -XX:+UnlockExperimentalVMOptions -XX:+AlwaysSafeConstructors was requested.
而AlwaysSafeConstructors这个vm-option是jdk9才有的experimental选项。
ref:
https://shipilev.net/blog/2014/safe-public-construction/
https://chriswhocodes.com/vm-options-explorer.html


#### 实战级双重检测

```java
public class Singleton {
    private static volatile Singleton instance=null;
    private Singleton() {
    }

    public static Singleton getInstance() {
        // 为什么要用局部变量来接收?
        // volatile修饰的静态变量访问比较慢，如果不用局部变量则getInstance需要多次访问instance变量，使用局部变量可以有一定的性能提升。
        Singleton temp=instance; 
        
        if (null == temp) {
            synchronized (Singleton.class) {
                temp=instance;
                if (null == temp) {
                    temp=new Singleton();
                    instance=temp;
                }
            }
        }
        return instance;
    }
}
```

用 局部变量 来接收 静态的成员变量
jdk 官方的文档（JMM）有说明 指令重排发生的地方有很多 ，编译器，及时编译，CPU在硬件层面的优化，看spring 比较新的代码也使用volatile来修饰


volatile修饰的静态变量访问比较慢，volatile 修饰不走寄存器内存，每次操作都是直接访问的内存，性能较差。
如果不用局部变量则getInstance需要多次访问instance变量，使用局部变量可以有一定的性能提升。
Using localRef, we are reducing the access of volatile variable to just one for positive usecase. 
If we do not use localRef, then we would have to access volatile variable twice - once for checking null and then at method return time.
Accessing volatile memory is quite an expensive affair because it involves reaching out to main memory.
参考链接：https://www.javacodemonk.com/threadsafe-singleton-design-pattern-java-806ad7e6

### 静态内部类
```java

public class IdGenerator { 
  private AtomicLong id = new AtomicLong(0);
  private IdGenerator() {}

  private static class SingletonHolder{
    private static final IdGenerator instance = new IdGenerator();
  }
  
  public static IdGenerator getInstance() {
    return SingletonHolder.instance;
  }
 
  public long getId() { 
    return id.incrementAndGet();
  }
}
```
类似饿汉式，但又能做到了延迟加载。
SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象。只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一性、创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载。

### 枚举
```java

public enum IdGenerator {
  INSTANCE;
  private AtomicLong id = new AtomicLong(0);
 
  public long getId() { 
    return id.incrementAndGet();
  }
}
```

基于枚举类型的单例实现。这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。

### 其他实现

"做WP开发的时候用到的硬件如Camera大多是单例，之前看微软的源码有的单例使用并发字典来实现的"


## 单例的问题
我们在项目中使用单例，都是用它来表示一些全局唯一类，比如配置信息类、连接池类、ID 生成器类。单例模式书写简洁、使用方便，在代码中，我们不需要创建对象，直接通过类似 IdGenerator.getInstance().getId() 这样的方法来调用就可以了。但是，这种使用方法有点类似硬编码（hard code），会带来诸多问题。

1. 违背基于接口而非实现的设计
IdGenerator 的使用方式违背了基于接口而非实现的设计原则，也就违背了广义上理解的 OOP 的抽象特性。
如果未来某一天，我们希望针对不同的业务采用不同的 ID 生成算法。比如，订单 ID 和用户 ID 采用不同的 ID 生成器来生成。
为了应对这个需求变化，我们需要修改所有用到 IdGenerator 类的地方，这样代码的改动就会比较大。
2. 对继承、多态特性的支持也不友好。
单例类也可以被继承、也可以实现多态，只是实现起来会非常奇怪，会导致代码的可读性变差。不明白设计意图的人，看到这样的设计，会觉得莫名其妙。
所以，一旦你选择将某个类设计成到单例类，也就意味着放弃了继承和多态这两个强有力的面向对象特性，也就相当于损失了可以应对未来需求变化的扩展性。
3. 单例对代码的扩展性不友好
设计成单例类，无法适应各种需求变更
4. 缺乏代码可测性
如果单例类依赖比较重的外部资源，比如 DB，我们在写单元测试的时候，希望能通过 mock 的方式将它替换掉。而单例类这种硬编码式的使用方式，导致无法实现 mock 替换。
5. 不支持有参数的构造函数


## 单例代解决方案
为了保证全局唯一，除了使用单例，我们还可以用静态方法来实现。不过，静态方法这种实现思路，并不能解决我们之前提到的问题。如果要完全解决这些问题，我们可能要从根上，寻找其他方式来实现全局唯一类了。
比如，通过工厂模式、IOC 容器（比如 Spring IOC 容器）来保证，由程序员自己来保证（自己在编写代码的时候自己保证不要创建两个类对象）。
有人把单例当作反模式，主张杜绝在项目中使用。我个人觉得这有点极端。模式没有对错，关键看你怎么用。如果单例类并没有后续扩展的需求，并且不依赖外部系统，那设计成单例类就没有太大问题。对于一些全局的类，我们在其他地方 new 的话，还要在类之间传来传去，不如直接做成单例类，使用起来简洁方便。


## 单例模式中的唯一性
单例类中对象的唯一性的作用范围是进程内的，在进程间是不唯一的。
实际上，对于 Java 语言来说，单例类对象的唯一性的作用范围并非进程，而是类加载器（Class Loader）,classloader有两个作用：1. 用于将class文件加载到JVM中；2. 确认每个类应该由哪个类加载器加载，并且也用于判断JVM运行时的两个类是否相等。
通过双亲委派模型保证唯一性。

“线程唯一”指的是线程内唯一，线程间可以不唯一，Java 语言本身提供了 ThreadLocal 工具类，可以更加轻松地实现线程唯一单例。
ThreadLocal 底层实现原理也是基于下面代码中所示的 HashMap。

```java
public class IdGenerator {
  private AtomicLong id = new AtomicLong(0);

  private static final ConcurrentHashMap<Long, IdGenerator> instances
          = new ConcurrentHashMap<>();

  private IdGenerator() {}

  public static IdGenerator getInstance() {
    Long currentThreadId = Thread.currentThread().getId();
    instances.putIfAbsent(currentThreadId, new IdGenerator());
    return instances.get(currentThreadId);
  }

  public long getId() {
    return id.incrementAndGet();
  }
}
```

集群环境下的单例，需要把这个单例对象序列化并存储到外部共享存储区（比如文件）。
进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对对象的加锁。

```
public class IdGenerator {
  private AtomicLong id = new AtomicLong(0);
  private static IdGenerator instance;
  private static SharedObjectStorage storage = FileSharedObjectStorage(/*入参省略，比如文件地址*/);
  private static DistributedLock lock = new DistributedLock();
  
  private IdGenerator() {}

  public synchronized static IdGenerator getInstance() 
    if (instance == null) {
      lock.lock();
      instance = storage.load(IdGenerator.class);
    }
    return instance;
  }
  
  public synchroinzed void freeInstance() {
    storage.save(this, IdGeneator.class);
    instance = null; //释放对象
    lock.unlock();
  }
  
  public long getId() { 
    return id.incrementAndGet();
  }
}

// IdGenerator使用举例
IdGenerator idGeneator = IdGenerator.getInstance();
long id = idGenerator.getId();
IdGenerator.freeInstance();
```
### 