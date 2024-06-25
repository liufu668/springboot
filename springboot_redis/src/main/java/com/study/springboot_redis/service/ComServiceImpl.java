package com.study.springboot_redis.service;

import com.study.springboot_redis.mapper.ComMapper;
import com.study.springboot_redis.model.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 业务接口的实现类
 * 用模板操作Redis实际就是通过set()/get()方法存取数据
 */
@Service //将一个类标识为服务层组件
public class ComServiceImpl implements ComService{

    @Autowired
    ComMapper comMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//StringRedisTemplate模板用于从Redis存取字符串,注入该模板

    @Autowired
    RedisTemplate<String,Object> redisTemplate;//RedisTemplate模板用于从Redis存取对象,注入该模板

    @Override
    public String getPNameFromRedis(int pid) {
        Commodity commodity = comMapper.queryByPid(pid);//从MyBatis接口读取商品记录
        stringRedisTemplate.opsForValue().set("pname",commodity.getPname());//使用set()方法存入Redis
        return stringRedisTemplate.opsForValue().get("pname");//使用get()方法从Redis中获取
    }

    @Override
    public Commodity getComFromRedis(int pid) {
        Commodity commodity = comMapper.queryByPid(pid);
        redisTemplate.opsForValue().set(String.valueOf(commodity.getPid()),commodity);
        return (Commodity) redisTemplate.opsForValue().get(String.valueOf(pid));
    }
}
