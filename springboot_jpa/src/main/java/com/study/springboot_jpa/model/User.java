package com.study.springboot_jpa.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 实体类
 */
@Entity //指定该类是一个实体类(和数据库表映射的类)
@Table(name = "user")//没有给出表名@Table,默认表名为实体类类名的首字母小写,多个单词之间用下划线连接
@Data
public class User {

    @Id//指定实体类的主键
    @GeneratedValue//自动增长机制
    private long id;

    @Column(nullable = false,unique = true)//不允许为空,且唯一
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;
}
