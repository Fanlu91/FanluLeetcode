package designpattern.behavioral.observer.eventbus;

import java.util.concurrent.Executor;

/**
 * MoreExecutors.directExecutor() 是 Google Guava 提供的工具类，
 * 看似是多线程，实际上是单线程。之所以要这么实现，主要还是为了跟 AsyncEventBus 统一代码逻辑，做到代码复用。
 */
public class MoreExecutors {
    public static Executor directExecutor() {
        return null;
    }
}
