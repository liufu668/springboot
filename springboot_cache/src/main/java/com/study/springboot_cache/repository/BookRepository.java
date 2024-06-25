package com.study.springboot_cache.repository;

import com.study.springboot_cache.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Book getById(Integer id);
}
