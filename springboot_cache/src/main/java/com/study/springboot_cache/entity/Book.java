package com.study.springboot_cache.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@ToString
@Entity
@Table(name = "books")
public class Book {

    //主键自动增长
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键ID
    private String title;//书名
    private String author;//作者
    private String bookConcern;//出版社
    private LocalDate publishDate;//出版日期
    private Float price;//价格
}
