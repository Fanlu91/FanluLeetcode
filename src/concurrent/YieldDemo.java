package concurrent;

public class YieldDemo {
    public static int count = 0;
    final private static Object object = new Object();
  /*  public static void main(String[] arg) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count);
    }*/

   /* public static void main(String[] arg) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        synchronized (object) {
                            object.notify();
                            object.wait();
                        }
                        count++;
                        System.out.println("t1 " + count);
                    }
                    synchronized (object) {
                        object.notify();
                    }
                } catch (Throwable e) {
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000; i++) {
                        synchronized (object) {
                            object.notify();
                            object.wait();
                        }
                        count++;
                        System.out.println("t2 " + count);
                    }
                    synchronized (object) {
                        object.notify();
                    }
                } catch (Throwable e) {
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count: " + count);
    }*/

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    synchronized (object) {
                        count++;
                        System.out.println("t1111 " + count);
                    }
                    Thread.yield();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    synchronized (object) {
                        count++;
                        System.out.println("t2 " + count);
                    }
                    Thread.yield();
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count: " + count);
    }
}
