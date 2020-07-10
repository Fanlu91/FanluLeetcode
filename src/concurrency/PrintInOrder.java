package concurrency;

// Source : https://leetcode.com/problems/print-in-order/
// Id     : 1114
// Author : Fanlu Hai | https://github.com/Fanlu91/FanluLeetcode
// Date   : 2020-07-03
// Topic  : Concurrency
// Level  : Easy
// Other  :
// Tips   :
// Result : 100% 100%

import java.util.concurrent.Semaphore;

public class PrintInOrder {
//    private volatile boolean firstJobDone = false;
//    private volatile boolean secondJobDone = false;
//
//    public PrintInOrder() {
////    public Foo() {
//
//    }
//
//    public void first(Runnable printFirst) throws InterruptedException {
//
//        // printFirst.run() outputs "first". Do not change or remove this line.
//        printFirst.run();
//        firstJobDone = true;
//    }
//
//    public void second(Runnable printSecond) throws InterruptedException {
//
//        while (!firstJobDone)
//            ;
//        // printSecond.run() outputs "second". Do not change or remove this line.
//        printSecond.run();
//        secondJobDone = true;
//    }
//
//    public void third(Runnable printThird) throws InterruptedException {
//
//        while (!secondJobDone)
//            ;
//        // printThird.run() outputs "third". Do not change or remove this line.
//        printThird.run();
//        firstJobDone = false;
//        secondJobDone = false;
//    }


//    Semaphore semaphore1 = new Semaphore(0);
//    Semaphore semaphore2 = new Semaphore(0);
//
//    public void first(Runnable printFirst) throws InterruptedException {
//
//        // printFirst.run() outputs "first". Do not change or remove this line.
//        printFirst.run();
//        semaphore1.release();
//    }
//
//    public void second(Runnable printSecond) throws InterruptedException {
//        semaphore1.acquire();
//        // printSecond.run() outputs "second". Do not change or remove this line.
//        printSecond.run();
//        semaphore2.release();
//    }
//
//    public void third(Runnable printThird) throws InterruptedException {
//        semaphore2.acquire();
//        // printThird.run() outputs "third". Do not change or remove this line.
//        printThird.run();
//    }
    volatile int cnt;

    public PrintInOrder() {
//    public Foo() {
        cnt = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        ++cnt;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (cnt != 1) {}
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        ++cnt;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (cnt != 2) {}
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
