package com.study.springboot_exception.exception;

import lombok.Data;

/**
 * 自定义异常类: 用于及时处理一些不符合业务逻辑的数据
 * 注意:
 * 1.必须继承RuntimeException运行时异常,并重写父类构造方法
 */
@Data
public class MyBusinessException extends RuntimeException{

    private static final long serialVersionUID=1L;
    private int code;
    private String message;

    public MyBusinessException(String message){
        super(message);
        this.message=message;
    }
    public MyBusinessException(int code,String message){
        super(message);
        this.code=code;
        this.message=message;
    }

}
