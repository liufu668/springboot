package com.study.shiro.mapper;

import com.study.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper{
    public User findUserByName(String username);
}
