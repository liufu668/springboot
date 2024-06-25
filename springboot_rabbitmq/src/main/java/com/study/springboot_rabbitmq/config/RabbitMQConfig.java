package com.study.springboot_rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类: 绑定队列和交换器
 */
@Configuration
public class RabbitMQConfig {

    private static final Logger log= LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Value("${rabbit.queue.name}")
    String queueName;

    @Value("${rabbit.exchange.name}")
    String exchangeName;

    @Value("${rabbit.routing.key}")
    String routingKey;

    @Bean()
    public Queue initQueue(){//创建队列
        return new Queue(queueName);
    }

    @Bean()
    public DirectExchange initDirectExchange(){//创建交换器
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding bindingDirect(){//将队列与交换器绑定
        return BindingBuilder.bind(initQueue()).to(initDirectExchange()).with(routingKey);
    }

    //RabbitMQ收到消息后,把结果反馈给服务器,服务器将打印日志
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //消息发送成功后触发确认方法
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        //消息发送失败后触发回调方法
        connectionFactory.setPublisherReturns(true);
        //通过连接工厂对象创建RabbitTemplate对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //若交换器无法匹配到指定队列,则取消发送消息
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {//ack:RabbitMQ返回的应答
                if(ack){
                    log.info("消息发送成功");
                }else{
                    log.info("消息发送失败,原因: {}",cause);
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("消息发送失败: {}",message);

            }
        });
        return rabbitTemplate;
    }
}
