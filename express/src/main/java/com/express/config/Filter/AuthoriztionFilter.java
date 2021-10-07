package com.express.config.Filter;

import com.express.common.JsonHelper;
import com.express.model.ResultMsg;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "AuthorizationFilter")
public class AuthoriztionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 查看是否登录
        var obj = request.getAttribute("user");
        if (obj == null) { // 返回未登录信息
            System.out.println(request.getLocalAddr());
            var writer = response.getWriter();
            String userJson = JsonHelper.getJsonStr(ResultMsg.error("请先登录"));
            writer.write(userJson);
            writer.flush();
            return;
        }
        chain.doFilter(request, response);


    }

    @Override
    public void destroy() {

    }
}
