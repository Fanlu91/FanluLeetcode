package designpattern.structural.wrapper.adapter.combination;

import designpattern.structural.wrapper.adapter.Adaptee;
import designpattern.structural.wrapper.adapter.ITarget;

/**
 * 组合模式
 * 基于接口
 * 对象适配器
 *
 * 如果 Adaptee 接口很多，而且 Adaptee 和 ITarget 接口定义大部分都不相同，那我们推荐使用对象适配器，
 * 因为组合结构相对于继承更加灵活。
 */
public class Adapter implements ITarget {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void f1() {
        adaptee.fa(); //委托给Adaptee
    }

    public void f2() { //...重新实现f2()...
    }

    public void fc() {
        adaptee.fc();
    }
}