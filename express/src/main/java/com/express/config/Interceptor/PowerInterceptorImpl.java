package com.express.config.Interceptor;

import com.express.common.SessionHelper;
import com.express.mapper.power.AdminPower;
import com.express.mapper.power.AfterLogin;
import com.express.mapper.power.CourierPower;
import com.express.mapper.power.CustomerPower;
import com.express.model.ResultMsg;
import com.express.model.ServerUserInfo;
import com.express.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限拦截器
 */
public class PowerInterceptorImpl implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(PowerInterceptorImpl.class);
    private static List whiteList;

    public PowerInterceptorImpl() {
        whiteList = new ArrayList();
        whiteList.add("login");
        whiteList.add("verifyCode");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取控制器的名字
//        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        //获取方法名
//        System.out.println(((HandlerMethod) handler).getMethod().getName());

        ServerUserInfo serverUserInfo = (ServerUserInfo) SessionHelper.getSessionByName(request, "user");
        if (serverUserInfo != null) { // 已登录
            var uri = request.getRequestURI();
            if (!(handler instanceof HandlerMethod)) {
                return false;
            }
            var h = (HandlerMethod) handler;
            if (h.hasMethodAnnotation(AdminPower.class) && serverUserInfo.getUserType() == UserType.AdminUser) {
                return true;
            }
            if (h.hasMethodAnnotation(CustomerPower.class) && serverUserInfo.getUserType() == UserType.Customer) {
                return true;
            }
            if (h.hasMethodAnnotation(CourierPower.class) && serverUserInfo.getUserType() == UserType.Courier) {
                return true;
            }
            if (h.hasMethodAnnotation(AfterLogin.class)) {
                return true;
            }
            if (whiteList.contains(h.getMethod().getName())) {
                return true;
            }
            SessionHelper.writeResponse(response, log, ResultMsg.error("你没有权限操作"));
            return false;
        }
        return true;
    }
}
