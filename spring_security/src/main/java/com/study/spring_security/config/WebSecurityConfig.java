package com.study.spring_security.config;

import com.study.spring_security.config.handler.MyAuthenticationFailureHandler;
import com.study.spring_security.config.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity//声明这是一个Spring Security安全配置类,无需再另加注解@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    //配置当前项目的登录和拦截信息
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //允许所有用户访问/home,/login
                .antMatchers("/home","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//指定登录页面
                .successHandler(authenticationSuccessHandler)//验证成功的处理器
                .failureHandler(authenticationFailureHandler)//验证失败的处理器
                //身份验证成功后默认重定向的页面
                .defaultSuccessUrl("/home",true).permitAll()
                .and()
                .logout().permitAll();
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
