package com.study.springboot_filter.config;

import com.study.springboot_filter.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean<>();
        bean.setFilter(new LoginFilter());
        bean.addUrlPatterns("/main/*");//过滤"/main"下的所有子路径
        bean.setName("loginfilter");
        return bean;
    }
}
