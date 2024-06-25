package com.study.springboot_exception.event;

import lombok.Data;

@Data
public class Result<T> {
    private String message;
    private int code;
    private T data;
}
