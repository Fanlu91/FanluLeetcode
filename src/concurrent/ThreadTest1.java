package concurrent;

public class ThreadTest1 {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000L);
        thread.stop();
        while (thread.isAlive()) {
        }
        thread.print();

        /**
         * x=1 y=0
         * 没有异常，但破坏了我们的预期:
         *
         * run方法里是一个同步的原子操作，x和y必须要共同增加
         * x=y=1
         *
         * 因此stop需要被废弃
         *
         */
    }

    private static class StopThread extends Thread {
        private int x = 0;
        private int y = 0;

        @Override
        public void run() {
            synchronized (this) {
                ++x;
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ++y;
            }
        }

        public void print() {
            System.out.println("x=" + x + " y=" + y);
        }
    }
}