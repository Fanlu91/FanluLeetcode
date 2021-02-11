package concurrent;

public class ThreadTest3 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Task("fanlutask"));
        t.start();
        t.interrupt();
    }

    static class Task implements Runnable {
        String name;

        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread has been interrupt!");
            }
            /**
             * 抛出异常后线程恢复非中断状态
             */
            System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted());
            System.out.println("task " + name + " is over");
        }
    }
}