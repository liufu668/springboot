package com.study.security_verifyCode.mapper;

import com.study.security_verifyCode.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper//标注该接口为MyBatis映射器
public interface UserMapper {

    @Select("select * from t_users where username=#{username}")
    User getByUsername(String username);

    @Insert("insert into t_users(username,password,mobile,roles)"+
            " values (#{username},#{password},#{mobile},#{roles})")
    //插入数据后,获取自增长的主键值
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int saveUser(User user);
}
