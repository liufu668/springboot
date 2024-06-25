package com.study.jwt.filter;

import cn.hutool.crypto.SecureUtil;
import com.nimbusds.jose.JOSEException;
import com.study.jwt.dto.PayloadDto;
import com.study.jwt.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * JwtAuthenticationFilter是一个过滤器，拦截用户请求,验证token
 * 继承OncePerRequestFilter，保证在任何servlet容器上每次请求调度都能执行一次。
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 过滤器逻辑，提取和验证JWT，如果有效则设置认证信息。
     *
     * @param request     HttpServletRequest对象
     * @param response    HttpServletResponse对象
     * @param filterChain FilterChain对象
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization"); // 从请求头中获取JWT
        if (token == null) {
            // 如果请求头中没有token，则直接放行
            filterChain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token,则进行解析,并且设置认证信息
        try {
            // 验证并解析token，并将认证信息设置到SecurityContext中
            //它将经过认证的用户信息设置到当前的安全上下文中 (SecurityContext)，从而使得后续的安全决策可以基于该用户的信息进行
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
            filterChain.doFilter(request, response); // 继续执行过滤链
        } catch (ParseException | JOSEException e) {
            e.printStackTrace(); // 打印异常信息
        }
    }

    /**
     * 验证token,并解析token，返回以用户名和密码所表示的经过身份验证的主体的令牌。
     *
     * @param token JWT字符串
     * @return UsernamePasswordAuthenticationToken对象
     * @throws ParseException JSON解析异常
     * @throws JOSEException  JWT处理异常
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws ParseException, JOSEException {
        // 使用HMAC算法验证并解析JWT，获取PayloadDto对象
        PayloadDto payloadDto = JwtUtil.verifyTokenByHMAC(token, SecureUtil.md5(JwtUtil.DEFAULT_SECRET));
        String username = payloadDto.getUsername(); // 从Payload中获取用户名
        List<String> roles = payloadDto.getAuthorities(); // 从Payload中获取角色列表
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // 将角色列表转换为Spring Security的SimpleGrantedAuthority集合
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null; // 如果用户名为空，返回null
    }
}
