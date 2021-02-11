package designpattern.behavioral.observer.jdk;

import java.util.Vector;

public class Observable {
    private boolean changed = false;
    private Vector<Observer> obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    /**
     * notifyObservers() 函数之所以没有像其他函数那样，一把大锁加在整个函数上，主要还是出于性能的考虑。
     *
     * notifyObservers() 函数依次执行每个观察者的 update() 函数，每个 update() 函数执行的逻辑提前未知，有可能会很耗时。
     * 如果在 notifyObservers() 函数上加 synchronized 锁，notifyObservers() 函数持有锁的时间就有可能会很长，
     * 这就会导致其他线程迟迟获取不到锁，影响整个 Observable 类的并发性能。
     *
     * @param arg
     */
    public void notifyObservers(Object arg) {
        Object[] arrLocal;

        /**
         * 我们先拷贝一份观察者列表，赋值给函数的局部变量
         *
         * 局部变量是线程私有的，并不在线程间共享。这个拷贝出来的线程私有的观察者列表就相当于一个快照。
         * 我们遍历快照，逐一执行每个观察者的 update() 函数。
         * 而这个遍历执行的过程是在快照这个局部变量上操作的，不存在线程安全问题，不需要加锁。
         *
         * 所以，我们只需要对拷贝创建快照的过程加锁，加锁的范围减少了很多，并发性能提高了。
         *
         * 问题：
         * 在创建好快照之后，添加、删除观察者都不会更新快照，新加入的观察者就不会被通知到，新删除的观察者仍然会被通知到。
         * 这种权衡是否能接受完全看你的业务场景。实际上，这种处理方式也是多线程编程中减小锁粒度、提高并发性能的常用方法。
         */
        // 拷贝创建快照过程
        synchronized (this) {
            if (!changed)
                return;
            arrLocal = obs.toArray();
            clearChanged();
        }
        // 遍历过程
        for (int i = arrLocal.length - 1; i >= 0; i--)
            ((Observer) arrLocal[i]).update(this, arg);
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }
}
