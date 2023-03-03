package com.application.bookstore.controller;

import com.application.bookstore.domain.dto.Book;
import com.application.bookstore.domain.request.BookRequest;
import com.application.bookstore.domain.response.BookListResponse;
import com.application.bookstore.domain.response.BookResponse;
import com.application.bookstore.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    BookController bookController;

    @Mock
    BookService bookService;

    private final Integer idBook = 1;
    private final String titleBook = "The Hexagonal Design Pattern";
    private final String authorBook = "Author";
    private final Double priceBook = 1000000.0;
    private final String isbnBook = "ISBN-2023-01";

    @Test
    public void givenParameterBookRequestAndShouldBeCreateBookTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(bookService.createBook(any(BookRequest.class)))
                .thenReturn(mockReturnBookData());

        BookResponse response = bookController.createBook(mockBookRequest());
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.isSuccess()).isEqualTo(true);
        assertThat(response.getData()).isEqualTo(mockReturnBookData());
    }

    @Test
    public void givenParameterBookRequestAndShouldBeUpdateBookTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(bookService.updateBook(anyInt(), any(BookRequest.class)))
                .thenReturn(mockReturnBookData());

        BookResponse response = bookController.updateBook(idBook, mockBookRequest());
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.isSuccess()).isEqualTo(true);
        assertThat(response.getData()).isEqualTo(mockReturnBookData());
    }

    @Test
    public void givenParameterBookIdAndShouldBeFindByIdBookTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(bookService.findByBookId(anyInt())).thenReturn(mockReturnBookData());

        BookResponse response = bookController.findBookById(idBook);
        assertThat(response.getStatusCode()).isEqualTo(201);
        assertThat(response.isSuccess()).isEqualTo(true);
        assertThat(response.getData()).isEqualTo(mockReturnBookData());
    }

    @Test
    public void shouldBeFindAllBookTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(bookService.findAllBook()).thenReturn(mockReturnListBookData());

        BookListResponse response = bookController.findAllBook();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.isSuccess()).isEqualTo(true);
        assertThat(response.getData()).isEqualTo(mockReturnListBookData());
    }

    @Test
    public void shouldDeleteBookById() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        bookService.deleteBook(anyInt());
        verify(bookService).deleteBook(anyInt());
    }

    private Book mockReturnBookData() {
        return Book.builder().id(1).title(titleBook).
               author(authorBook).price(priceBook).
               isbn(isbnBook).build();
    }

    private List<Book> mockReturnListBookData() {
        return Collections.singletonList(mockReturnBookData());
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