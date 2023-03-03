package com.application.bookstore.service;

import com.application.bookstore.domain.dto.Book;
import com.application.bookstore.domain.request.BookRequest;
import com.application.bookstore.entities.BookEntity;
import com.application.bookstore.repo.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    public Book createBook(BookRequest request) {
        BookEntity bookEntity = bookRepository.saveAndFlush(constructCreateBook(request));
        return constructConvertEntityToCreateOrUpdateOrFindIdBookResult(bookEntity);
    }

    @Override
    public Book updateBook(Integer bookId, BookRequest request) {
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setPrice(request.getPrice());
        bookEntity.setPrice(request.getPrice());
        bookEntity = bookRepository.saveAndFlush(bookEntity);
        return constructConvertEntityToCreateOrUpdateOrFindIdBookResult(bookEntity);
    }

    @Override
    public Book findByBookId(Integer bookId) {
        BookEntity bookEntity = bookRepository.findById(bookId).get();
        return constructConvertEntityToCreateOrUpdateOrFindIdBookResult(bookEntity);
    }

    @Override
    public List<Book> findAllBook() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return constructConvertListEntityToFindAllBook(bookEntities);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    private BookEntity constructCreateBook(BookRequest request) {
        return BookEntity.builder().
               title(request.getTitle()).
               author(request.getAuthor()).
               price(request.getPrice()).
               isbn(request.getIsbn()).build();
    }

    private Book constructConvertEntityToCreateOrUpdateOrFindIdBookResult(BookEntity entity) {
        return Book.builder().
               id(entity.getId()).
               title(entity.getTitle()).
               author(entity.getAuthor()).
               price(entity.getPrice()).
               isbn(entity.getIsbn()).build();
    }

    private List<Book> constructConvertListEntityToFindAllBook(List<BookEntity> bookEntities) {
        List<Book> books = new ArrayList<>();
        for (BookEntity book : bookEntities) {
            Book bookData = Book.builder().id(book.getId()).title(book.getTitle())
                    .author(book.getAuthor()).price(book.getPrice()).isbn(book.getIsbn())
                    .build();
            books.add(bookData);
        }
        return books;
    }

}
