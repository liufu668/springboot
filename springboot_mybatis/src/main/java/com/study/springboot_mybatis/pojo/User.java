package com.study.springboot_mybatis.pojo;

import lombok.Data;

import java.io.Serializable;

@Data //添加lombok依赖,自动生成getter和setter
public class User implements Serializable {

    private Integer id;
    private String uname;
    private int age;
    private int roles;
    private String address;
}
