package com.study.springboot_redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration //声明该类为配置类
public class RedisConfig {
    /**
     * RedisTemplate使用JdkSeriallizationRedisSerializer来序列化数据,以二进制的形式存储,不便可视化,所以在此自定义序列化器
     * 在此自定义一个JSON格式的序列化类
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //GenericJackson2JsonRedisSerializer这个序列化器是一个通用的 JSON 序列化器，
        // 它可以序列化和反序列化任意类型的对象，而不需要指定对象的类型
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}