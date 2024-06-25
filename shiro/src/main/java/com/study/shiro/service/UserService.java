package com.study.shiro.service;

import com.study.shiro.entity.User;

public interface UserService{
    public User findUserByName(String username);
}
