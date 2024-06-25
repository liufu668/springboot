package com.study.security_verifyCode.config;

import com.study.security_verifyCode.config.handler.MyAuthenticationFailureHandler;
import com.study.security_verifyCode.config.handler.MyAuthenticationSuccessHandler;
import com.study.security_verifyCode.filter.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity//声明这是一个Spring Security安全配置类,无需再另加注解@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private VerifyCodeFilter verifyCodeFilter;

    //配置当前项目的登录和拦截信息
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //确保在用户尝试进行用户名和密码验证之前，先进行验证码的验证,如果验证码不对,就不需要再验证用户名和密码了
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                //允许所有用户访问
                .antMatchers("/home","/login","/verifyCode").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")//指定登录页面
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)//验证成功的处理器
                .failureHandler(authenticationFailureHandler)//验证失败的处理器
                //身份验证成功后默认重定向的页面
                .defaultSuccessUrl("/home",true).permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
