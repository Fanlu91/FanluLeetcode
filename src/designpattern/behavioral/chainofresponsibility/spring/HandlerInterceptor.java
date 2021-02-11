package designpattern.behavioral.chainofresponsibility.spring;

public class HandlerInterceptor {
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
