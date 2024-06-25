package com.study.springboot_cache.controller;


import com.study.springboot_cache.entity.Book;
import com.study.springboot_cache.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController//返回的是JSON/XML格式数据而非HTML页面
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public String saveBook(@RequestBody Book book){
        Book resultBook = bookService.saveBook(book);
        return resultBook.toString();
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Integer id){//将URL获取的参数映射到方法参数上以获取路径上的参数
        Book resultBook = bookService.getBookById(id);
        return resultBook.toString();
    }

    @PutMapping
    public String updateBook(@RequestBody Book book){
        Book resultBook = bookService.updateBook(book);
        return resultBook.toString();
    }

    @DeleteMapping
    public String deleteBook(@PathVariable Integer id){
        bookService.deleteBook(id);
        return "删除成功!";
    }
}
