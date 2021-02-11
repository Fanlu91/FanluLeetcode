package designpattern.behavioral.chainofresponsibility;

public class HandlerA extends Handler {
    @Override
    protected boolean doHandle() {
        boolean handled = false;
        //...
        return handled;
    }
}
