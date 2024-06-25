package com.study.springboot_rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 消息生产者
 */
@Service
public class RabbitMQProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //读取配置文件中的交换器名称和路由键名称
    @Value("${rabbit.exchange.name}")
    String exchangeName;

    @Value("${rabbit.routing.key}")
    String routingKey;

    //将消息发送给RabbitMQ的交换机,交换机通过路由键决定将消息路由到哪些队列
    public void send(String message){
        rabbitTemplate.convertAndSend(exchangeName,routingKey,message);
    }
}
