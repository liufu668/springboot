package com.study.springboot_verifyCode.filter;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * VerifyCodeFilter 是一个自定义过滤器，用于在用户登录时验证验证码的正确性。
 */
@Component
public class VerifyCodeFilter extends GenericFilter {

    // 默认处理登录请求的URL
    private String defaultFilterProcessUrl = "/doLogin";

    /**
     * 过滤器的核心方法，用于拦截和处理请求。
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 将ServletRequest和ServletResponse转换为HttpServletRequest和HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 检查请求是否是POST方法，并且请求路径是否匹配登录路径
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            // 获取请求中的验证码和会话中生成的验证码
            String requestCaptcha = request.getParameter("code");
            String genCaptcha = (String) request.getSession().getAttribute("verify_code");

            // 验证验证码是否为空
            if (StringUtils.isEmpty(requestCaptcha)) {
                throw new AuthenticationServiceException("验证码不能为空!");
            }

            // 验证验证码是否匹配（忽略大小写）
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码错误!");
            }
        }

        // 如果验证码验证通过，或者不是登录请求,则继续执行后续过滤器链
        chain.doFilter(request, response);
    }
}