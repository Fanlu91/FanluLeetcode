package designpattern.creational.singleton;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter writer;
    private static final Logger instance = new Logger();

    private Logger() {
        File file = new File("/Users/wangzheng/log.txt");
        try {
            writer = new FileWriter(file, true); //true表示追加写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        try {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Logger类的应用示例：
class UserController {
    public void login(String username, String password) {
        // ...省略业务逻辑代码...
        Logger.getInstance().log(username + " logined!");
    }
}

class OrderController {
    public void create(OrderVo order) {
        // ...省略业务逻辑代码...
        Logger.getInstance().log("Created a order: " + order.toString());
    }
}

class OrderVo{

}