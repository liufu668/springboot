package com.study.springboot_mybatis.controller;

import com.study.springboot_mybatis.mapper.UserMapper;
import com.study.springboot_mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设置当前控制器类为RESTful风格，等同于 @Controller 与 @ResponseBody两个注解的组合功能
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/findAll")
    ResponseEntity<List<User>> findAllUser(){
        List<User> list = userMapper.selectUserList();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/findById/{id}")
    ResponseEntity<User> findById(@PathVariable("id") Integer id){
        if(id==null || id<1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User user = userMapper.findById(id);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user){
        if(user==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Integer count = userMapper.save(user);
        if(count == null || count == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(count > 0){
            return ResponseEntity.status(HttpStatus.CREATED).body("添加新用户信息成功!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("添加新用户信息失败!");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user){
        if(user==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Integer count = userMapper.update(user);
        if(count == null || count == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if(count > 0){
            return ResponseEntity.status(HttpStatus.OK).body("修改用户信息成功!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改用户信息失败!");
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> delete(@PathVariable("id") Integer id){
        if(id == null || id < 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Integer count = userMapper.delete(id);
        if(count == null || count == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(count > 0){
            return ResponseEntity.status(HttpStatus.OK).body("删除用户信息成功!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("删除用户信息失败!");
        }
    }


}
