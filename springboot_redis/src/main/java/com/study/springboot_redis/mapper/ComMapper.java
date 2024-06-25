package com.study.springboot_redis.mapper;

import com.study.springboot_redis.model.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * 这是一个公共接口，定义了与commodity表进行交互的方法
 */
@Mapper //这个注解表示该接口是一个Mapper接口，用于与数据库进行交互
public interface ComMapper {

    //@Param("pid")注解用于将方法参数与SQL语句中的pid参数进行映射
    @Select("SELECT * FROM commodity WHERE Pid = #{pid}")
    Commodity queryByPid(@Param("pid") int pid);
}
