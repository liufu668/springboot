package com.study.springboot_rabbitmq.controller;

import com.study.springboot_rabbitmq.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制器类
 * 1.注入生产者对象
 * 2.当前端发来请求时,将用户提交的消息发送给RabbitMQ
 */
@Controller
public class IndexController {

    @Autowired
    RabbitMQProducer producer;

    //被这两个注解标记,直接在页面中显示方法返回的字符串
    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(String message){
        //生产者发送消息
        producer.send(message);
        return "发送成功!";
    }

    //只被@RequestMapping标记,而未被@ResponseBody标记,方法的返回值即为要跳转的URL
    @RequestMapping("/index")
    public String index(){
        return "index";//index.html
    }
}
