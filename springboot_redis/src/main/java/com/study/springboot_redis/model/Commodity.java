package com.study.springboot_redis.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class Commodity implements Serializable {//序列化

    private int pid;//商品号
    private String tcode;//商品分类编码
    private String scode;//商家编码
    private String pname;//商品名称
    private float pprice;//商品价格
    private int stocks;//商品库存
}
