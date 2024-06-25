package com.study.springboot_cache.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 这是一个自定义的缓存键生成器（KeyGenerator）配置类。
 * 在这个配置类中，你重写了 CachingConfigurerSupport 类的 keyGenerator() 方法，
 * 用于生成缓存的键，避免出现重复的键。
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    @Override //alt+insert
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            /**
             * 将目标对象的类名、方法名和参数数组拼接起来作为键的生成规则,以确保缓存的唯一性
             */
            @Override
            public Object generate(Object target, Method method, Object... objects) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName())
                        .append(".")
                        .append(method.getName())
                        .append(Arrays.toString(objects));
                return sb.toString();
            }
        };
    }
}
