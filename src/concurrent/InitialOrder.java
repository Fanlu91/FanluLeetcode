package concurrent;

public class InitialOrder {
    public InitialOrder() {//构造函数
        System.out.println("构造函数");
    }

    {//构造代码块
        System.out.println("构造代码块");
    }

    static {//静态代码块
        System.out.println("静态代码块");
    }

    /**
     * 静态代码块
     * main函数
     * 构造代码块
     * 构造函数
     *
     * @param args no idea
     */
    public static void main(String[] args) {
        System.out.println("main函数");
        InitialOrder i = new InitialOrder();
    }
}