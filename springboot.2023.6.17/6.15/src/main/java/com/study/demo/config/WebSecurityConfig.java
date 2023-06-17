package com.study.demo.config;

import com.study.demo.service.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import sun.security.util.Password;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//用户认证接口
    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//关闭csrf
        http.formLogin()//自定义登录页面
                .loginPage("/loginPage")//登录页面的url
                .loginProcessingUrl("/login")//登录访问路径
                .failureUrl("/exception")//登录失败时跳转的路径
                .defaultSuccessUrl("/index",true);//登录成功后跳转的路径
        http.authorizeRequests()
                .antMatchers("/loginPage","/hello","/exception","/*.jpg").permitAll()//除了括号内的，其他url路径都会被拦截
                .anyRequest().authenticated();
        http.logout().logoutUrl("/logout");//注销用户
        //记住密码，自动登录，基于token的记住我实现
        http.rememberMe().tokenRepository(persistentTokenRepository).tokenValiditySeconds(60*60).userDetailsService(userDetailsService);



        //认证和授权的相关代码：
        //http.csrf().disable();//关闭csrf校验
        //http.formLogin().loginPage("/login").permitAll();//配置登录页面
        //http.formLogin().successHandler(new LoginSuccessHandler());//配置登录成功后的操作
        //http.logout().permitAll();//登录授权
        //http.authorizeRequests()//配置授权
        //        .antMatchers("/js/**","/css/**","/images/**").permitAll()//可以访问所有静态资源
        //        .antMatchers("/add/**").permitAll()//可以访问所有以add开头的add页面
        //        .antMatchers("/home2").hasRole("user")
        //        .anyRequest().fullyAuthenticated();
    }
    //基于application.yml配置的spring security仅仅可以提供基础的认证与授权管理
    //而spring security的配置管理在项目中往往基于bean配置完成
    @Bean
    public AuthenticationProvider authenticationProvider(){//登录提示
        //提示用户找不到异常，默认无论用户名和密码哪个错误，都提示密码错误
        //DaoAuthenticationProvider：从数据源中加载身份信息
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(passwordEncoder());//密码加密
        provider.setUserDetailsService(userDetailsService);//配置用户认证服务
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){//密码加密器
        //PasswordEncoderFactories：工厂类
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();//返回加密器实例
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){//记住密码，并存储Token
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);//数据存储在数据库中
        return jdbcTokenRepository;
    }

    //@Override
    //protected UserDetailsService userDetailsService() {
    //    InMemoryUserDetailsManager manager=new InMemoryUserDetailsManager();
    //    manager.createUser(User.withUsername("admin").password("123456").roles("admin").build());
    //    return manager;
    //}
}
/**
 * csrf()：开启跨站请求伪造拦截
 * formLogin：开启HTTP表单认证
 * loginPage()：指定登录界面路径
 * permitAll()：放行所有用户，包括认证和匿名
 * logout()：开启退出功能
 * successHandler()：指定自定义登录成功处理器
 * antMatchers()：配置授权访问路径
 */