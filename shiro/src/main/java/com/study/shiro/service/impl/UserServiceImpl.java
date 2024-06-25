package com.study.shiro.service.impl;

import com.study.shiro.entity.User;
import com.study.shiro.mapper.UserMapper;
import com.study.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}




