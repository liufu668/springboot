package com.study.springboot_redis.controller;

import com.study.springboot_redis.model.Commodity;
import com.study.springboot_redis.service.ComServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("com")
public class ComController {

    @Autowired //注入业务层操作Redis的服务实体
    ComServiceImpl comService;

    @RequestMapping("getpname")//从Redis获取商品名称
    public String getPNameByPid(int pid){
        return comService.getPNameFromRedis(pid);
    }

    @RequestMapping("getcom")//从Redis获取商品记录对象
    public Commodity getComByPid(int pid){
        return comService.getComFromRedis(pid);
    }
}
