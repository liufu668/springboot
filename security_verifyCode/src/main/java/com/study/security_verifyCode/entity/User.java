package com.study.security_verifyCode.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * UserDetails是一个用于提供核心用户信息的接口
 */
@Data
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private String mobile;
    private String roles;


    /**
     * 返回授予用户的权限,该方法不能返回null
     * AuthorityUtils是一个工具类,该类的静态方法commaSeparatedStringToAuthorityList()
     * 可以将以逗号分隔的字符串表示形式的权限转换为GrantedAuthority对象数组
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    /**
     * 指示用户账户是否已过期,过期的账户无法进行身份验证
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指示用户是否被锁定或解锁,无法对被锁定的用户进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * 指示用户的凭据(密码)是否已过期,过期的凭据阻止身份验证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 指示用户是被启用还是被禁用,无法对被禁用的用户进行身份验证
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
