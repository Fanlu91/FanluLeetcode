package designpattern.behavioral.template;

/**
 * 回调跟模板模式一样，也具有复用和扩展的功能。
 * 除了回调函数之外，BClass 类的 process() 函数中的逻辑都可以复用。
 * 如果 ICallback、BClass 类是框架代码，AClass 是使用框架的客户端代码，我们可以通过 ICallback 定制 process() 函数，
 * 也就是说，框架因此具有了扩展的能力。
 */
public class AClass {
    public static void main(String[] args) {
        /**
         * A 注册 ICallback匿名函数给B
         *
         * 当A执行B的process，B实际上会来执行A里注册的匿名函数
         */
        BClass b = new BClass();
        b.process(new ICallback() { //回调对象
            @Override
            public void methodToCallback() {
                System.out.println("Call back me.");
            }
        });
    }
}
