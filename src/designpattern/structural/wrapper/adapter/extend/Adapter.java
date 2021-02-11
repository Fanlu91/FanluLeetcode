package designpattern.structural.wrapper.adapter.extend;


import designpattern.structural.wrapper.adapter.Adaptee;
import designpattern.structural.wrapper.adapter.ITarget;

/**
 * 继承模式
 * 类适配器
 *
 * 如果 Adaptee 接口很多，而且 Adaptee 和 ITarget 接口定义大部分都相同，那我们推荐使用类适配器，
 * 因为 Adapter 复用父类 Adaptee 的接口，比起对象适配器的实现方式，Adaptor 的代码量要少一些。
 */
public class Adapter extends Adaptee implements ITarget {
    public void f1() {
        super.fa();
    }

    public void f2() {
    } // 这里fc()不需要实现，直接继承自Adaptee，这是跟对象适配器最大的不同点
}