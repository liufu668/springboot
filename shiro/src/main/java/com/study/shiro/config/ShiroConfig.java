package com.study.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * ShiroDialect类是为了支持在Thymeleaf中使用的Shiro标签
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        // 设置安全管理器
        bean.setSecurityManager(securityManager);

        // Shiro内置过滤器，可以实现权限相关的拦截器
        /*
            常用的过滤器：
                anon: 无需认证（登录）可以访问
                authc: 必须认证才可以访问
                user: 如果使用rememberMe的功能可以直接访问
                perms: 该资源必须得到资源权限才可以访问
                role: 该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/doLogin", "anon"); // 无需认证即可访问
        filterMap.put("/logout", "logout"); // Shiro自带的退出登录
        filterMap.put("/**", "authc"); // 拦截其他所有请求，需要认证
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/delete","perms[user:delete]");

        // 设置默认登录的 URL，身份认证失败会访问该 URL
        bean.setLoginUrl("/login");
        // 设置登录成功后要跳转的链接
        bean.setSuccessUrl("/index");
        //未授权时跳转的页面
        bean.setUnauthorizedUrl("/noauth");
        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm对象
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
