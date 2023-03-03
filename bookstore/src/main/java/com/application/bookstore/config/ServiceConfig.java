package com.application.bookstore.config;

import com.application.bookstore.repo.BookRepository;
import com.application.bookstore.service.BookService;
import com.application.bookstore.service.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }

}
