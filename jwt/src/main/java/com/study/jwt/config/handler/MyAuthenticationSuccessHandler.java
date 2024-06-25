package com.study.jwt.config.handler;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.nimbusds.jose.JOSEException;
import com.study.jwt.dto.PayloadDto;
import com.study.jwt.util.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 修改验证成功处理器,用户成功登录后,在响应报头中发送token
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取认证主体信息
        Object principal = authentication.getPrincipal();

        // 用户登录成功后，在响应头中发送 token
        if(principal instanceof UserDetails){
            // 如果主体信息是用户详情实例
            UserDetails user=(UserDetails)principal;

            // 获取用户的权限信息
            Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
            List<String> authoritiesList = new ArrayList<>(authorities.size());

            // 将用户权限列表转换为字符串列表
            authorities.forEach(authority -> {
                authoritiesList.add(authority.getAuthority());
            });

            // 定义 token 的创建时间和过期时间
            Date now = new Date();
            DateTime exp = DateUtil.offsetSecond(now, 60 * 60); // 1小时后过期

            // 创建 payload 数据传输对象
            PayloadDto payloadDto=PayloadDto.builder()
                    .sub(user.getUsername())
                    .iat(now.getTime())
                    .exp(exp.getTime())
                    .jti(UUID.randomUUID().toString())
                    .username(user.getUsername())
                    .authorities(authoritiesList)
                    .build();
            String token=null;
            try {
                // 生成 JWT token
                token= JwtUtil.generateTokenByHMAC(
                        //nimbus-jose-jwt所使用的HMAC SHA256算法所需密钥长度为256位(32字节),因此先用md5加密
                        JSONUtil.toJsonStr(payloadDto),
                        SecureUtil.md5(JwtUtil.DEFAULT_SECRET)
                );

                // 设置响应头和响应内容类型
                response.setHeader("Authorization",token);
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write("登录成功!");
                out.close();
            } catch (JOSEException e) {
                e.printStackTrace();
            }
        }
    }
}
