package com.study.springboot_filter.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 使用@WebFilter注解可以快速配置过滤器,必须同时与@Component一起使用
 * urlPatterns为过滤器所过滤的地址,在此模拟了一个在线的视频文件
 */
@Component
@WebFilter(urlPatterns = "/vedio/710.mp4")
public class LoginFilter implements Filter {//Filter接口有如下三大方法

    //过滤器初始化时会调用该方法,可不重写
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext context = filterConfig.getServletContext();//获取上下文对象
        context.setAttribute("count",0);//计数器初始值为0
    }

    //过滤器的核心方法,在该方法中实现过滤业务,必须被重写
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        ServletContext context = req.getServletContext();//获取上下文对象
        Integer count= (Integer) context.getAttribute("count");//获取计数器的值
        context.setAttribute("count",++count);//让计数器自增
        chain.doFilter(request, response);//过滤链对象,可将请求交给下一个过滤器处理
    }


    //过滤器销毁时会调用的方法,可不重写
    @Override
    public void destroy() {

    }

}
