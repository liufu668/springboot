package com.study.springboot_verifyCode.config;

import com.study.springboot_verifyCode.filter.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private VerifyCodeFilter verifyCodeFilter;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 忽略对以下路径的安全性检查,即未登录就可以访问,前者为登录页面,后者为验证码
        web.ignoring().antMatchers("/doLogin","/verifyCode","login.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers("/doLogin").permitAll() // 允许所有用户访问登录页面
                .anyRequest().authenticated() // 其他请求需要认证
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(authenticationSuccessHandler) // 配置验证成功的处理器
                .failureHandler(authenticationFailureHandler) // 配置验证失败的处理器
                .and()
                .logout().permitAll(); // 配置登出功能
        http.csrf().disable(); // 关闭CSRF校验，因为我们的验证码机制已经提供了足够的防护
    }
}
