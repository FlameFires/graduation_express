package com.express.config.Filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 配置跨域
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "authFilter") //这里的“/*” 表示的是需要拦截的请求路径
public class PassHttpFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将ServletResponse转换为HttpServletResponse
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        // 如果不是80端口,需要将端口加上,如果是集群,则用Nginx的地址,同理不是80端口要加上端口
        String[] allowDomain = {"http://www.baidu.com", "http://localhost:8080"};
        Set allowedOrigins = new HashSet(Arrays.asList(allowDomain));
        String originHeader = ((HttpServletRequest) servletRequest).getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            httpResponse.setHeader("Access-Control-Allow-Origin", originHeader);
            httpResponse.setContentType("application/json;charset=UTF-8");
            httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");
            // 如果要把Cookie发到服务器，需要指定Access-Control-Allow-Credentials字段为true
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Expose-Headers", "*");
        }
        filterChain.doFilter(servletRequest, httpResponse);
    }
}