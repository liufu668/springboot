package com.study.springboot_listener.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * 自定义监听器:
 * 用于监听每一个前端请求的URL,IP和session id
 */
@Component //标注该类,保证SpringBoot能扫描到此监听器,即该注解让该监听器生效
public class MyRequestListener implements ServletRequestListener {

    //请求初始化时触发
    public void requestInitialized(ServletRequestEvent sre){
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String ip=request.getRemoteAddr();
        String url=request.getRequestURL().toString();
        String sessionID=request.getSession().getId();//由服务器随机生成

        System.out.println("前端请求的IP地址为: "+ip);
        System.out.println("前端请求的URL地址为: "+url);
        System.out.println("前端请求的session id地址为: "+sessionID);

    }

    //请求被销毁时触发
    public void requestDestroyed(ServletRequestEvent sre){
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String sessionID=request.getSession().getId();
        System.out.println("session id为: "+sessionID+"的请求已被销毁");
    }
}
