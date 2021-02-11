package concurrent;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        StopThread thread = new StopThread();
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
        while (thread.isAlive()) {
        }
        thread.print();
    }

    private static class StopThread extends Thread {

        private int x = 0;
        private int y = 0;

        @Override
        public void run() {
            synchronized (this) {
                ++x;
                try {
                    Thread.sleep(3000L);
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