package com.express.config;

import com.express.config.Filter.PassHttpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean regsitFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new PassHttpFilter());
        registration.addUrlPatterns("/*");
        registration.setName("PassHttpFilter");
        registration.setOrder(1);
        return registration;
    }


}
