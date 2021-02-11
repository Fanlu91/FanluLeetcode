package designpattern.behavioral.chainofresponsibility;

/**
 * Tomcat 中提供的 FilterChain 的实现类简化
 */
public final class ApplicationFilterChain implements FilterChain {
    private static final int INCREMENT = 1;
    private int pos = 0; //当前执行到了哪个filter
    private int n; //filter的个数
    private ApplicationFilterConfig[] filters;
    private Servlet servlet;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) {
        if (pos < n) {
            ApplicationFilterConfig filterConfig = filters[pos++];
            Filter filter = filterConfig.getFilter();
            // 此处是一个递归
            // 因为递归调用，所以能做到双向拦截。递归函数压栈时，对request拦截；递归函数出栈时，对response拦截。
            filter.doFilter(request, response, this);
            //把filter.doFilter的代码实现展开替换到这里 chain就是this
//            System.out.println("拦截客户端发送来的请求.");
//            chain.doFilter(request, response);
//            System.out.println("拦截发送给客户端的响应.")
        } else {
            // filter都处理完毕后，执行servlet
            servlet.service(request, response);
        }
    }

    public void addFilter(ApplicationFilterConfig filterConfig) {
        for (ApplicationFilterConfig filter : filters)
            if (filter == filterConfig)
                return;

        if (n == filters.length) {//扩容
            ApplicationFilterConfig[] newFilters = new ApplicationFilterConfig[n + INCREMENT];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }
        filters[n++] = filterConfig;
    }
}