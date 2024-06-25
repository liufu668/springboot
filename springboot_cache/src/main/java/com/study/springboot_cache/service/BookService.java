package com.study.springboot_cache.service;

import com.study.springboot_cache.entity.Book;
import com.study.springboot_cache.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @CacheConfig(cacheNames = "book")在类级别共享一些共同的缓存相关配置
 * 设置缓存的名称为book
 */
@Service
@CacheConfig(cacheNames = "book")
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * @Cacheable根据方法参数将方法结果保存到缓存中,在后续使用相同参数调用方法时,
     * 会直接返回缓存中的值,而不再调用目标方法
     */
    @Cacheable
    public Book getBookById(Integer id){
        System.out.println("getBookById: "+id);
        return bookRepository.getById(id);
    }

    /**
     * @CachePut(key = "#result.id")调用方法以更新缓存
     * key = "#result.id": 缓存的key,其值为SpEL表达式
     */
    @CachePut(key = "#result.id")
    public Book saveBook(Book book){
        System.out.println("saveBook: "+book);
        book = bookRepository.save(book);
        return book;
    }

    @CachePut(key = "#result.id")
    public Book updateBook(Book book){
        System.out.println("updateBook: "+book);
        book = bookRepository.save(book);
        return book;
    }

    /**
     * @CacheEvict(beforeInvocation = true)删除缓存中过时或未使用的数据
     * beforeInvocation = true: 在方法执行前删除
     */
    @CacheEvict(beforeInvocation = true)
    public void deleteBook(Integer id){
        System.out.println("deleteBook: "+id);
        bookRepository.deleteById(id);
    }
}
