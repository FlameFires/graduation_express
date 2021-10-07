package com.express.config.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter(urlPatterns = "/*", filterName = "logFilter2")
public class LogCostFilter implements Filter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        System.out.println(LocalDateTime.now().format(formatter) + " ！！1.日志过滤器");
        chain.doFilter(request, response);
        System.out.println(LocalDateTime.now().format(formatter) + " ！！2.日志过滤器 cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {

    }
}
