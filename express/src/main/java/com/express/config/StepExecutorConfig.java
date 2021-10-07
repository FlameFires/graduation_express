package com.express.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时自动打开http://localhost:9036/swagger-ui.html
 */
@Component
public class StepExecutorConfig implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(StepExecutorConfig.class);

    @Value("${spring.web.loginurl}")
    private String loginUrl;

    @Value("${spring.web.googleexcute}")
    private String googleExcutePath;

    @Value("${spring.web.isopenurl}")
    private boolean isOpen;

    @Override
    public void run(ApplicationArguments args) {
        //先判断当前环境
        if (!exitisVersion()) {
            //启动swagger-ui
            application();
        }
    }


    private Boolean exitisVersion() {
        String osName = System.getProperties().getProperty("os.name");
        if (osName.equals("Linux")) {
            log.info("running in Linux");
            return true;
        } else {
            log.info("don't running in Linux");
            return false;
        }
    }

    private void application() {

        if (isOpen) {
            String cmd = googleExcutePath + " " + loginUrl;
            log.info("浏览地址：" + cmd);
            Runtime run = Runtime.getRuntime();
            try {
                run.exec(cmd);
                log.info("启动浏览器打开项目成功");
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }
}