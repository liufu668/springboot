package com.study.jwt.config;

import com.study.jwt.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // 启用Spring Security的Web安全支持
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler; // 成功处理器注入
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler; // 失败处理器注入
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * 配置HTTP安全
     *
     * @param http HttpSecurity对象
     * @throws Exception 异常处理
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home", "/login").permitAll() // 允许所有人访问
                .antMatchers("/admin/**").hasRole("ADMIN") // 只有具有 "ADMIN" 角色的用户才能访问以 "/admin/" 开头的路径
                .anyRequest().hasRole("USER") // 其他请求必须有USER角色
                .and()
            .formLogin()
                .loginPage("/login") // 指定登录页面
                .successHandler(authenticationSuccessHandler) // 登录成功处理器
                .failureHandler(authenticationFailureHandler) // 登录失败处理器
                .and()
            .logout() // 启用注销功能
                .and()
            // 添加自定义的 JWT 过滤器
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable(); // 禁用CSRF保护
    }

    /**
     * 配置密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用BCrypt加密密码
    }
}
