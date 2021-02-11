package concurrent;

public class AccountingSyncBad implements Runnable {
    static int i = 0;
    static int iShared = 0;

    /**
     * 作用于静态方法,锁是当前class对象,也就是
     * AccountingSyncClass类对应的class对象
     */
    public static synchronized void increase() {
        i++;
    }

    /**
     * 非静态,访问时锁不一样不会发生互斥
     */
    public synchronized void increase4Obj() {
        iShared++;
    }


    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
            increase4Obj();
        }
    }

    public static void main(String[] args) throws InterruptedException {
/*        AccountingSyncBad instance=new AccountingSyncBad();
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);*/

        // 两个不同的实例对象，这也就意味着存在着两个不同的实例对象锁，因此t1和t2都会进入各自的对象锁
        // 解决这种困境的的方式是将synchronized作用于静态的increase方法，这样的话，对象锁就当前类对象
        //new新实例
        Thread t1 = new Thread(new AccountingSyncBad());
        //new新实例
        Thread t2 = new Thread(new AccountingSyncBad());
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i + " " + iShared);
    }
}