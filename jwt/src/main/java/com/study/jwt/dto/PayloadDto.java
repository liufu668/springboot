package com.study.jwt.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用于封装JWT中的有效载荷
 */
@Data
//这个注解单独应用时会生成 equals 和 hashCode 方法,callSuper = false 表示生成的方法不会调用父类的 equals 和 hashCode 方法
@EqualsAndHashCode(callSuper = false)
//用于生成 Builder 模式相关的代码。它会生成一个静态内部类 ExampleBuilder，使得创建对象更加简洁和灵活
@Builder
public class PayloadDto {
    private String sub;//主题
    private Long iat;//签发时间
    private Long exp;//过期时间
    private String jti;//JWT的ID
    private String username;//用户名称
    private List<String> authorities;//用户拥有的权限
}
