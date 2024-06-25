package com.study.springboot_mybatis.mapper;

import com.study.springboot_mybatis.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

//通过在类上添加@Repository注解，Spring可以自动创建该类的实例，
// 并将其注入到其他需要访问数据库或持久化的组件中。
@Repository
public interface UserMapper {

    List<User> selectUserList();
    User findById(int id);
    int save(User user);
    int update(User user);
    int delete(Integer id);
}
