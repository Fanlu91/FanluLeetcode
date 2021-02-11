package concurrent;

/**
 * Inner class 的应用
 */
public class InnerClassTest {
    public static void main(String[] args) {
        // 初始化Bean1
        InnerClassTest innerClassTest = new InnerClassTest();
        Bean1 bean1 = innerClassTest.new Bean1();
        bean1.I++;
        // 初始化Bean2
        Bean2 bean2 = new Bean2();
        bean2.J++;
        //初始化Bean3
        Bean bean = new Bean();
        Bean.Bean3 bean3 = bean.new Bean3();
        bean3.k++;

        bean.new Inner().print();
    }

    /**
     * 局部内部类和匿名内部类只能访问局部final变量
     *
     * 否则有生命周期不一致 数据不一致问题
     *
     * 从JDK 1.8开始，会默认给这两种内部类访问的field 加上final(隐式地)，
     * 所以可能会在编译器中看到可以访问没有加final的变量，只有去修改它时，编译器才会报错
     * @param b
     */
    public void test(final int b) {
        final int a = 10;
        new Thread(){
            public void run() {
                System.out.println(a);
                System.out.println(b);
            };
        }.start();
    }

    class Bean1 {
        public int I = 0;
    }

    static class Bean2 {
        public int J = 0;
    }


}

class Bean {
    private int a = 1;

    class Bean3 {
        public int k = 0;
    }

    class Inner {
        private int a = 2;

        public void print() {
            int a = 3;
            System.out.println("局部变量：" + a);
            System.out.println("内部类变量：" + this.a);
            System.out.println("外部类变量：" + Bean.this.a);
        }
    }
}

/**
 *  一般来说，内部类是很少用来作为继承用的。但是当用来继承的话，要注意两点：
 *
 * 　　1）成员内部类的引用方式必须为 Outter.Inner.
 * 　　2）构造器中必须有指向外部类对象的引用，并通过这个引用调用super()。这段代码摘自《Java编程思想》
 */
class WithInner {
    class Inner{

    }
}
class InheritInner extends WithInner.Inner {

    // InheritInner() 是不能通过编译的，一定要加上形参
    InheritInner(WithInner wi) {
        wi.super(); //必须有这句调用
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner obj = new InheritInner(wi);
    }
}