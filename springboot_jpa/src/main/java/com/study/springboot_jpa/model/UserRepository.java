package com.study.springboot_jpa.model;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository 接口是 Spring Data JPA 提供的一个泛型接口，用于简化数据访问层的开发。
// 它提供了许多内置的方法，用于对实体进行常见的 CRUD 操作
public interface UserRepository extends JpaRepository<User,Long> {
    // 这里可以定义一些自定义的查询方法
    User findByUserName(String userName);//根据名字查询用户
}
