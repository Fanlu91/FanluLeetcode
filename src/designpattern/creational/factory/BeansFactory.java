package designpattern.creational.factory;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeansFactory 是如何设计和实现的
 *
 * 委托的BeanFactory在Spring源码里是DefaultListableBeanFactory
 *
 * <p>
 * 创建对象用到的主要技术点就是 Java 中的反射语法：一种动态加载类和创建对象的机制。
 */
public class BeansFactory {
    //如果对象的 scope 属性是 singleton，那对象创建之后会缓存在 singletonObjects 这样一个 map 中，下次再请求此对象的时候，直接从 map 中取出返回
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>();

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList) throws InvocationTargetException {
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
        }

        for (BeanDefinition beanDefinition : beanDefinitionList) {
            if (beanDefinition.isLazyInit() == false && beanDefinition.isSingleton()) {
                createBean(beanDefinition);
            }
        }
    }

    public Object getBean(String beanId) throws InvocationTargetException {
        BeanDefinition beanDefinition = beanDefinitions.get(beanId);
        if (beanDefinition == null) {
//            throw new NoSuchBeanDefinitionException("Bean is not defined: " + beanId);
        }
        return createBean(beanDefinition);
    }

    //    @VisibleForTesting
    protected Object createBean(BeanDefinition beanDefinition) throws InvocationTargetException {
        if (beanDefinition.isSingleton() && singletonObjects.contains(beanDefinition.getId())) {
            return singletonObjects.get(beanDefinition.getId());
        }

        Object bean = null;
        try {
            Class beanClass = Class.forName(beanDefinition.getClassName());
            List<BeanDefinition.ConstructorArg> args = beanDefinition.getConstructorArgs();
            if (args.isEmpty()) {
                bean = beanClass.newInstance();
            } else {
                Class[] argClasses = new Class[args.size()];
                Object[] argObjects = new Object[args.size()];
                for (int i = 0; i < args.size(); ++i) {
                    BeanDefinition.ConstructorArg arg = args.get(i);
                    if (!arg.getIsRef()) {
                        argClasses[i] = arg.getType();
                        argObjects[i] = arg.getArg();
                    } else {
                        BeanDefinition refBeanDefinition = beanDefinitions.get(arg.getArg());
                        if (refBeanDefinition == null) {
//                            throw new NoSuchBeanDefinitionException("Bean is not defined: " + arg.getArg());
                        }
                        argClasses[i] = Class.forName(refBeanDefinition.getClassName());
                        /**
                         * 此处递归调用可能出现环
                         *
                         * 循环依赖解决是三级缓存，提前暴露还没有初始化结束的bean。
                         * 检测是Map存一下过程，aba这样顺序判断，有重复（a出现两次）就是环了。
                         */
                        argObjects[i] = createBean(refBeanDefinition);
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
//            throw new BeanCreationFailureException("", e);
        }

        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.putIfAbsent(beanDefinition.getId(), bean);
            return singletonObjects.get(beanDefinition.getId());
        }
        return bean;
    }
}