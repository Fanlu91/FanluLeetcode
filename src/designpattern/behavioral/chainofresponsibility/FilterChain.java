package designpattern.behavioral.chainofresponsibility;

public interface FilterChain {
    void doFilter(ServletRequest request, ServletResponse response);
}
