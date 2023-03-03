package com.application.bookstore.controller;

import com.application.bookstore.domain.dto.Book;
import com.application.bookstore.domain.request.BookRequest;
import com.application.bookstore.domain.response.BookListResponse;
import com.application.bookstore.domain.response.BookResponse;
import com.application.bookstore.service.BookService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = "Book Rest Api")
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "books")
    public BookResponse createBook(@RequestBody BookRequest request) {
        Book bookData = bookService.createBook(request);
        return constructBuildCreateOrUpdateOrFindOrAnotherResponse(bookData,
                "Create Book Successfully!");
    }

    @PutMapping(value = "books/{bookId}")
    public BookResponse updateBook(@PathVariable Integer bookId, @RequestBody BookRequest request) {
        Book bookData = bookService.updateBook(bookId, request);
        return constructBuildCreateOrUpdateOrFindOrAnotherResponse(bookData,
                "Update Book Successfully!");
    }

    @GetMapping(value = "books/{bookId}")
    public BookResponse findBookById(@PathVariable Integer bookId) {
        Book bookData = bookService.findByBookId(bookId);
        return constructBuildCreateOrUpdateOrFindOrAnotherResponse(bookData,
                "Request Book Successfully!");
    }

    @GetMapping(value = "books")
    public BookListResponse findAllBook() {
        List<Book> bookData = bookService.findAllBook();
        return constructBuildListResponse(bookData,
                "Request Book Successfully!");
    }

    @DeleteMapping(value = "books/{bookId}")
    public BookResponse deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
        return constructBuildCreateOrUpdateOrFindOrAnotherResponse(null,
                "Delete Book Successfully!");
    }

    private BookResponse constructBuildCreateOrUpdateOrFindOrAnotherResponse(Book book, String message) {
        BookResponse response = new BookResponse();
        response.setData(book);
        response.setMessage(message);
        response.setStatusCode(HttpStatus.CREATED.value());
        return response;
    }

    private BookListResponse constructBuildListResponse(List<Book> books, String message) {
        BookListResponse response = new BookListResponse();
        response.setData(books);
        response.setMessage(message);
        response.setTotalData(books.size());
        response.setStatusCode(HttpStatus.OK.value());
        return response;
    }

}
