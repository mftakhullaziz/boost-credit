package com.application.bookstore.service;

import com.application.bookstore.domain.dto.Book;
import com.application.bookstore.domain.request.BookRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    Book createBook(BookRequest request);
    Book updateBook(Integer bookId, BookRequest request);
    Book findByBookId(Integer bookId);
    List<Book> findAllBook();
    void deleteBook(Integer bookId);
}
