package designpattern.structural.wrapper.bridge;

public class TrivialNotification extends Notification {
    public TrivialNotification(MsgSender msgSender) {
        super(msgSender);
    }

    @Override
    public void notify(String message) {

    }
    // 与SevereNotification代码结构类似，所以省略...
}
