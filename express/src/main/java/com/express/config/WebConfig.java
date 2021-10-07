package com.express.config;

import org.springframework.context.annotation.Configuration;

/**
 * 网页配置帮助类
 */
@Configuration
public class WebConfig {

    public static String[] enableVersion = new String[]{"v1", "v2"};
    public static String[] escapeURI = new String[]{"/api/v1/user/c/login", "/api/v1/user/d/login", "/api/v1/user/a/login", "/api/v1/user/verifyCode"};
    public static String[] escapeMethod = new String[]{"login", "verifyCode", "regist"};
    public static Integer loginErrorMaxNum = 3;

    public static boolean isPassURI(String uri) {
        for (String s : escapeURI) {
            if (s.equals(uri.strip())) return true;
        }
        return false;
    }

    public static boolean isPassMethod(String name) {
        for (String s : escapeMethod) {
            if (s.equals(name.strip())) return true;
        }
        return false;
    }
}
