package com.study.shiro.config;

import com.study.shiro.entity.User;
import com.study.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实现用户登录认证逻辑
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    /**
     * 用户授权的逻辑代码
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了===>用户授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        //获取User对象
        User currentUser = (User) subject.getPrincipal();
        //设置权限
        info.addStringPermission(currentUser.getPermissions());

        return info;
    }

    /**
     * 用户认证的逻辑代码
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了===>用户认证");
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        //连接真实的数据库,调取用户信息
        User user = userService.findUserByName(token.getUsername());
        //该用户不存在
        if(user==null){
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        //将登录用户放入Session中
        subject.getSession().setAttribute("loginUser",user);
        //密码认证
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
