package com.express.common;

import com.express.mapper.entity.AdminUser;
import com.express.mapper.entity.Courier;
import com.express.mapper.entity.Customer;
import com.express.model.ServerUserInfo;
import com.express.model.UserType;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpServletRequest，HttpServletResponse 帮助类
 */
public class SessionHelper {
    public static Object getSessionByName(HttpServletRequest request, String name) {
        return request.getSession().getAttribute(name);
    }

    /*

     */
    public static void setSessionByName(HttpServletRequest request, String name, Object val) {
        request.getSession().setAttribute(name, val);
    }

    /**
     * 获取登录用户信息
     *
     * @param request
     * @return
     */
    public static ServerUserInfo getLoginUserInfo(HttpServletRequest request) {
        var obj = (ServerUserInfo) getSessionByName(request, "user");
        return obj;
    }

    public static ServerUserInfo setLoginUserInfo(HttpServletRequest request,ServerUserInfo serverUserInfo) {
        HttpSession session = request.getSession();
        session.setAttribute("user", serverUserInfo);
        return serverUserInfo;
    }

    public static String getLoginUserName(HttpServletRequest request) {
        var obj = getLoginUserInfo(request);
        if (obj.getUserType() == UserType.Customer) {
            return ((Customer) obj.getUserObject()).getUserName();
        }
        if (obj.getUserType() == UserType.Courier) {
            return ((Courier) obj.getUserObject()).getUserName();
        }
        if (obj.getUserType() == UserType.AdminUser) {
            return ((AdminUser) obj.getUserObject()).getAccount();
        }
        return "";
    }

    public static void writeResponse(HttpServletResponse response, Logger log, Object obj) {
        if (obj == null) return;
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        try (var writer = response.getWriter()) {
            String userJson = JsonHelper.getJsonStr(obj);
            writer.write(userJson);
            response.flushBuffer();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
