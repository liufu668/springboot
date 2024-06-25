package com.study.springboot_interceptor.intercepter;

import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * preHandle()方法会在控制器类中的方法被执行之前,对请求进行处理时被执行
     * @param request   请求对象
     * @param response  响应对象
     * @param handler   请求的处理程序对象
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        if(handler instanceof HandlerMethod){//如果是HandlerMethod对象
            HandlerMethod method=(HandlerMethod) handler;
            //通过handler对象的类型知晓请求将进入控制器类中的哪个方法
            System.out.println("(1)请求访问控制器类中的方法是:"+method.getMethod().getName()+"()");
            Object value=request.getAttribute("value");//读取请求的某个属性,默认为null
            System.out.println("   执行方法前:value="+value);
            return true;
        }
        return false;
    }
    //postHandle()方法会在控制器类中的方法被执行之后,对请求进行处理时被执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response,Object handler,
                            @Nullable ModelAndView modelAndView){
        Object value=request.getAttribute("value");//执行完请求,再读取此属性
        System.out.println("(2)执行方法后:value="+value);
    }
    //afterCompletion()方法会在整个请求结束之后被执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,Object handler,
                           @Nullable  Exception ex){
        request.removeAttribute("value");//执行完请求,再读取此属性
        System.out.println("(3)整个请求都执行完毕,在此做一些资源释放工作");
    }
}
