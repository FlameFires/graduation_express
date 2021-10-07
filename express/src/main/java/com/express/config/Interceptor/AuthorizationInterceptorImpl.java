package com.express.config.Interceptor;

import com.express.common.SessionHelper;
import com.express.config.WebConfig;
import com.express.model.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * 登录拦截器
 */
public class AuthorizationInterceptorImpl implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptorImpl.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        var h = (HandlerMethod) handler;
        String method = h.getMethod().getName();
        if (!WebConfig.isPassMethod(method)) {// 是否是登录链接
            var obj = request.getSession().getAttribute("user");
            if (obj == null) {
                SessionHelper.writeResponse(response, log, ResultMsg.error("请先登录"));
                return false;
            }
        }

        return true;
    }
}
