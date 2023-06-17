package com.study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //表示该类为一个controller对象，spring容器在启动时会将该类实例化
public class AddController {//用于返回add页面

    @GetMapping("/add")//映射一个请求路径
    // 相当于RequestMapping(value="/add",method=RequestMethod.GET)
    public String ad(){
        return "add";//响应信息
    }
}
