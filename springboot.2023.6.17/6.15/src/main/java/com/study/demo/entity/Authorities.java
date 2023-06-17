package com.study.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter //提供getter方法
@Setter //提供setter方法
@Entity //定义该类为实体类
@Table(name="authorities")//name属性指定与实体类对应的表
public class Authorities {
    @Id//标识id字段为该表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//定义id生成策略
    private Integer id;
    private String authority;

    @ManyToMany(mappedBy = "authorities",cascade=CascadeType.ALL)
    private Set<Users> user=new HashSet<>();
}
