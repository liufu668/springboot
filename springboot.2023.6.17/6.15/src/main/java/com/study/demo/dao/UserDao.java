package com.study.demo.dao;

import com.study.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<Users,Integer>, JpaSpecificationExecutor<Users> {
    Users findByUsername(String username);
}
