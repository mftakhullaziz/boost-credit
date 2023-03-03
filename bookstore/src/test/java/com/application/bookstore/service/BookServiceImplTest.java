package com.application.bookstore.service;

import com.application.bookstore.domain.dto.Book;
import com.application.bookstore.domain.request.BookRequest;
import com.application.bookstore.entities.BookEntity;
import com.application.bookstore.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    private final String titleBook = "The Hexagonal Design Pattern";
    private final String authorBook = "Author";
    private final Double priceBook = 1000000.0;
    private final String isbnBook = "ISBN-2023-01";

    @Test
    public void givenRequestBodyAndShouldBeCreateBook() {
        when(bookRepository.saveAndFlush(any(BookEntity.class))).
                thenReturn(mockReturnBookEntity());

        assertThat(bookService.createBook(mockBookRequest()))
                .usingRecursiveComparison()
                .isEqualTo(mockReturnBookData());

        verify(bookRepository, times(1))
                .saveAndFlush(any(BookEntity.class));

        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void givenRequestBodyAndRequestIdAndShouldBeUpdateBook() {
        when(bookRepository.findById(anyInt())).
                thenReturn(Optional.ofNullable(mockReturnBookEntity()));
        when(bookRepository.saveAndFlush(any(BookEntity.class))).
                thenReturn(mockReturnBookEntity());

        assertThat(bookService.updateBook(anyInt(), mockBookRequest()))
                .usingRecursiveComparison()
                .isEqualTo(mockReturnBookData());

        verify(bookRepository, times(1))
                .findById(anyInt());
        verify(bookRepository, times(1))
                .saveAndFlush(any(BookEntity.class));

        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void givenRequestIdAndShouldBeFindByIdBook() {
        when(bookRepository.findById(anyInt())).
                thenReturn(Optional.ofNullable(mockReturnBookEntity()));

        assertThat(bookService.findByBookId(anyInt()))
                .usingRecursiveComparison()
                .isEqualTo(mockReturnBookData());

        verify(bookRepository, times(1))
                .findById(anyInt());

        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void shouldBeFindAllBook() {
        when(bookRepository.findAll()).
                thenReturn(Collections.singletonList(mockReturnBookEntity()));

        assertThat(bookService.findAllBook())
                .usingRecursiveComparison()
                .isEqualTo(Collections.singletonList(mockReturnBookData()));

        verify(bookRepository, times(1))
                .findAll();

        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void shouldBeDeleteByIdBook() {
        bookRepository.deleteById(anyInt());
        bookService.deleteBook(anyInt());
        verify(bookRepository, times(2)).deleteById(anyInt());
        verifyNoMoreInteractions(bookRepository);
    }

    private BookEntity mockReturnBookEntity() {
        return BookEntity.builder().id(1).title(titleBook).
                author(authorBook).price(priceBook).
                isbn(isbnBook).build();
    }

    private Book mockReturnBookData() {
        return Book.builder().id(1).title(titleBook).
                author(authorBook).price(priceBook).
                isbn(isbnBook).build();
    }

    private BookRequest mockBookRequest() {
        BookRequest request = new BookRequest();
        request.setAuthor(authorBook);
        request.setTitle(titleBook);
        request.setPrice(priceBook);
        request.setIsbn(isbnBook);
        return request;
    }
}