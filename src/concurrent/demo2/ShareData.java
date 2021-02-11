package concurrent.demo2;

public class ShareData {
    public static int count = 0;
    public static void main(String[] args) {
        final ShareData data = new ShareData();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //进入的时候暂停1毫秒，增加并发问题出现的几率
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                        data.addCount();
                    }
                    System.out.print(count + " ");
                }
            }).start();
        }
        try {
            //主程序暂停3秒，以保证上面的程序执行完成
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
    }

    /**
     * 增加 synchronized 关键字
     */
    public synchronized void addCount() {
//    public void addCount() {
        count++;
    }
}
