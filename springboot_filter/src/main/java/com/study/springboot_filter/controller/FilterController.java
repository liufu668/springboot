package com.study.springboot_filter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
public class FilterController {

    @RequestMapping("/vedio/710.mp4")
    public String index(HttpServletRequest request){
        ServletContext context = request.getServletContext();
        Integer count= (Integer) context.getAttribute("count");
        return "当前访问量: "+count;//打印该地址被访问的次数
    }
}
