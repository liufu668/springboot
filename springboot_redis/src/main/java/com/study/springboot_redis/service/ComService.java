package com.study.springboot_redis.service;

import com.study.springboot_redis.model.Commodity;

/**
 * 业务层
 */
public interface ComService {

    public String getPNameFromRedis(int pid);//从Redis获取商品名称
    public Commodity getComFromRedis(int pid);//从Redis获取商品记录对象
}
