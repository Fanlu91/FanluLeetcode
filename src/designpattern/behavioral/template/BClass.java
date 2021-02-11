package designpattern.behavioral.template;

public class BClass {
    /**
     * 这里的callback来自调用B的类（ A 类）
     *
     * @param callback
     */
    public void process(ICallback callback) {
        //...
        callback.methodToCallback();
        //...
    }
}
