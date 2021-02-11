package designpattern.structural.wrapper.proxy;


public class UserControllerProxyBaseOnExtends extends UserController {
    private MetricsCollector metricsCollector;

    public UserControllerProxyBaseOnExtends() {
        this.metricsCollector = new MetricsCollector();
    }

    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = super.login(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }

    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();

        UserVo userVo = super.register(telephone, password);

        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        return userVo;
    }
}
//UserControllerProxy使用举例
//    UserController userController = new UserControllerProxyBaseOnExtends();
