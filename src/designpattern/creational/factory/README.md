# 工厂设计模式



相对简单工厂，去掉if else，利用多态。

仅仅利用多态，实际使用时，工厂类对象的创建逻辑又耦合进了调用函数中，和没有用设计模式的版本很相似。因此要为工厂类再创建一个简单工厂，也就是工厂的工厂，用来创建工厂类对象。



这样当我们新增一种 parser 的时候，只需要新增一个实现了 IRuleConfigParserFactory 接口的 Factory 类即可。所以，工厂方法模式比起简单工厂模式更加符合开闭原则。



### 简单工厂和工厂的取舍

之所以将某个代码块剥离出来，独立为函数或者类，原因是这个代码块的逻辑过于复杂，剥离之后能让代码更加清晰，更加可读、可维护。但是，如果代码块本身并不复杂，就几行代码而已，我们完全没必要将它拆分成单独的函数或者类。



当对象的创建逻辑比较复杂，不只是简单的 new 一下就可以，而是要组合其他类对象，做各种初始化操作的时候，我们推荐使用**工厂方法模式**，将复杂的创建逻辑拆分到多个工厂类中，让每个工厂类都不至于过于复杂。而使用简单工厂模式，将所有的创建逻辑都放到一个工厂类中，会导致这个工厂类变得很复杂。

除此之外，在某些场景下，如果对象不可复用，那工厂类每次都要返回不同的对象。如果我们使用**简单工厂模式**来实现，就只能选择第一种包含 if 分支逻辑的实现方式。如果我们还想避免烦人的 if-else 分支逻辑，这个时候，我们就推荐使用工厂方法模式。



复杂度无法被消除，只能被转移：

- 不用工厂模式，if-else 逻辑、创建逻辑和业务代码耦合在一起
- 简单工厂是将不同创建逻辑放到一个工厂类中，if-else 逻辑在这个工厂类中
- 工厂方法是将不同创建逻辑放到不同工厂类中，用一个**工厂类的工厂**来来得到某个工厂并用它来创建，if-else 逻辑在**工厂类的工厂**中



## 抽象工厂

在简单工厂和工厂方法中，类只有一种分类方式，如果类有两种分类方式，类的数量可能就要翻倍。

抽象工厂就是针对这种非常特殊的场景而诞生的。我们可以让一个工厂负责创建多个不同类型的对象，有效地减少工厂类的个数。

```java

public interface IConfigParserFactory {
  IRuleConfigParser createRuleParser();
  ISystemConfigParser createSystemParser();
  //此处可以扩展新的parser类型，比如IBizConfigParser
}

public class JsonConfigParserFactory implements IConfigParserFactory {
  @Override
  public IRuleConfigParser createRuleParser() {
    return new JsonRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new JsonSystemConfigParser();
  }
}

public class XmlConfigParserFactory implements IConfigParserFactory {
  @Override
  public IRuleConfigParser createRuleParser() {
    return new XmlRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new XmlSystemConfigParser();
  }
}

// 省略YamlConfigParserFactory和PropertiesConfigParserFactory代码
```



工厂方法模式：定义了一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让类把实例化推迟到子类

抽象工厂模式：提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类



### 工厂模式的应用举例

Java 中的 Calendar、DateFormat 类



### 基于spring框架解决工厂模式解决的问题

传统的工厂模式太麻烦了，除非业务真的很复杂，通常我会选择以下方案。

1.将不同的RuleConfigParser实现按照约定格式指定beanName注入，比方说`@Component(“XmlRuleConfigParser”)`，取的时候`applicationContext.getBean(typeSuffix+RuleConfigParser)`即可，拓展的话，自己写一个`xxRuleConfigParser`，就注入进去了，也不需要在map容器新增。
整个工厂方法就是

```java
public RuleConfigParser getInstance(suffix){
  return InstanceLocator.getBean(suffix+"RuleConfigParser");
}
```

2.直接用`java.util.functional`实现现代**函数式编程范式的设计模式**
像文中的例子,可以看作工厂,也可以看作获取一种parse策略。
可以有一个`FunctionFactory`内部维护一组`Function<String,String>`函数，再有一个Map容器 map type和Function的关系。这样是简化了类的数量，如果业务简单没必要整太多类，function铺在一个factory里可读性不会有什么问题。如果是没有返回值的操作，也可以用Consumer函数。打个比方

```java
public BiConsumer<AbstractProductServiceRequest, Function<ProductServiceQueryRequest,ProductServiceQueryResponse>> operateConsumer() {
  switch (serviceOperationEnum) {
    case OPEN:
      return openConsumer();
    case CLOSE:
      return closeConsumer();
    default:
      throw new RuntimeException("not support OperationType");
  }
}
```

如果是对象，那更简单，Map<Supply>函数即可。

```java
public class ShapeFactory {
 final static Map<String, Supplier<Shape>> map = new HashMap<>();
 static {
  map.put("CIRCLE", Circle::new);
  map.put("RECTANGLE", Rectangle::new);
 }
 public Shape getShape(String shapeType){
   Supplier<Shape> shape = map.get(shapeType.toUpperCase());
   if(shape != null) {
    return shape.get();
   }
   throw new IllegalArgumentException("No such shape " + shapeType.toUpperCase());
 }
}
```


对于比较简单的场景，lambda function等方式代替类，会显得不那么臃肿，具体还是要看需求。至于OOP等原则，也不是完全要遵守的，就像争哥说的少量if可以不管，一样的道理，灵活运用。



## DI容器

DI 容器底层最基本的设计思路就是基于工厂模式的。DI 容器相当于一个大的工厂类，负责在程序启动的时候，根据配置（要创建哪些类对象，每个类对象的创建需要依赖哪些其他类对象）事先创建好对象。当应用程序需要使用某个类对象的时候，直接从容器中获取即可。正是因为它持有一堆对象，所以这个框架才被称为“容器”。

DI 容器负责的事情要比单纯的工厂模式要多。比如除**对象创建**外它还包括**配置的解析**、**对象生命周期的管理**。

**配置的解析**：作为一个通用的框架来说，框架代码跟应用代码应该是高度解耦的，DI 容器事先并不知道应用会创建哪些对象，不可能把某个应用要创建的对象写死在框架代码中。我们将需要由 DI 容器来创建的类对象和创建类对象的必要信息（使用哪个构造函数以及对应的构造函数参数都是什么等等），放到配置文件中。容器读取配置文件，根据配置文件提供的信息来创建对象。

**对象创建**：如果每个类都对应创建一个工厂类，那项目中类的个数会成倍增加，这会增加代码的维护成本。需要将所有类对象的创建都放到一个工厂类中完成，比如 BeansFactory。要创建的类对象非常多，BeansFactory 中的代码会不会线性膨胀（代码量跟创建对象的个数成正比）呢？实际上并不会。DI 容器的具体实现使用了“反射”这种机制，它能在程序运行的过程中，动态地加载类、创建对象，不需要事先在代码中写死要创建哪些对象。

**生命周期的管理**：简单工厂模式有两种实现方式，一种是每次都返回新创建的对象，另一种是每次都返回同一个事先创建好的对象，也就是所谓的单例对象。在 Spring 框架中，我们可以通过配置 scope 属性，来区分这两种不同类型的对象。scope=prototype 表示返回新创建的对象，scope=singleton 表示返回单例对象。还可以配置对象是否支持懒加载。如果 lazy-init=true，对象在真正被使用到的时候（比如：BeansFactory.getBean(“userService”)）才被被创建；如果 lazy-init=false，对象在应用启动的时候就事先创建好。不仅如此，我们还可以配置对象的 init-method 和 destroy-method 方法，比如 init-method=loadProperties()，destroy-method=updateConfigFile()。DI 容器在创建好对象之后，会主动调用 init-method 属性指定的方法来初始化对象。在对象被最终销毁之前，DI 容器会主动调用 destroy-method 属性指定的方法来做一些清理工作，比如释放数据库连接池、关闭文件。

