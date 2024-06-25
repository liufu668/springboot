package com.springboot_thymeleaf.controller;

import com.springboot_thymeleaf.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Controller：这是一个Spring框架的注解，它用于将类标记为控制器（Controller）。
 * 使用@Controller注解的类被Spring认为是处理HTTP请求的控制器，
 * 并且可以处理来自客户端的请求。它通常与@RequestMapping注解一起使用，用于映射URL路径到相应的处理方法。
 */
@Controller
public class BookController {

    /**
     * @GetMapping("/books"): 这是一个Spring框架的注解，它用于映射HTTP GET请求到处理方法。
     * 在BookController中，@GetMapping("/books")注解表示当浏览器发起GET请求到"/books"路径时，
     * 将调用books()方法来处理该请求。books()方法会返回一个ModelAndView对象，用于渲染书籍列表的视图。
     */
    @GetMapping("/books")
    public String books(Model model){
        // 添加两本书的记录
        List<Book> books = new ArrayList<>();

        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Spring Boot企业级应用开发");
        book1.setCreateTime(new Date());
        book1.setAuthor("李白");

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("Node.js Web开发实战");
        book2.setCreateTime(new Date());
        book2.setAuthor("白居易");

        books.add(book1);
        books.add(book2);

        // 使用addAttribute(String attributeName, Object attributeValue)方法向Model中添加属性。
        // 属性的名称是一个字符串，可以在视图中使用它来检索属性值。属性值可以是任何Java对象，例如字符串、数字、集合等。
        model.addAttribute("books", books);

        return "bookList.html";
    }
}
