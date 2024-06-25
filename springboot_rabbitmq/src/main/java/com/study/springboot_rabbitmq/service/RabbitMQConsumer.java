package com.study.springboot_rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 */
@Service
public class RabbitMQConsumer {

    private static final Logger log= LoggerFactory.getLogger(RabbitMQConsumer.class);

    //使用@RabbitListener监听配置文件中的队列,当收到消息后,将其打印在控制台上
    @RabbitListener(queues = "${rabbit.queue.name}")
    public void getMessage(String message){
        log.info("消费者收到消息:{}",message);
    }
}
