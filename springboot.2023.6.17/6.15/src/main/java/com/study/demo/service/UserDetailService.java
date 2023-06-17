package com.study.demo.service;

import com.study.demo.entity.Authorities;
import com.study.demo.dao.UserDao;
import com.study.demo.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Set;

@Service("userDetailService")//把类标识成自动装配的bean的类
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Override
    @Transactional//为该方法添加事务
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users=usersDao.findByUsername(s);
        if (users == null) {//用户不存在
            log.error("用户名：[{}]不存在",s);
            throw new UsernameNotFoundException("用户名不存在");
        }
        //获取该用户的角色信息
        Set<Authorities> authoritiesSet=users.getAuthorities();
        ArrayList<GrantedAuthority> list=new ArrayList<>();
        for(Authorities authorities:authoritiesSet){
            list.add(new SimpleGrantedAuthority(authorities.getAuthority()));
        }
        return new User(users.getUsername(),users.getPassword(),list);
    }
}
