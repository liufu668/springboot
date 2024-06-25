package com.study.spring_security.service;

import com.study.spring_security.entity.User;
import com.study.spring_security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 在loadUserByUsername()方法中访问自定义数据库的用户表和角色表,然后返回一个UserDetails对象即可
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getByUsername(username);
        //该方法不允许返回空,如果没有找到用户,或者用户没有授予的权限,则抛出异常
        if(user==null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        return user;
    }
}
