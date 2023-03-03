package com.application.bookstore.domain.response;

import com.application.bookstore.domain.dto.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookListResponse {
    private Integer statusCode;
    private String message;
    private Integer totalData;
    private boolean isSuccess;
    private List<Book> data;
    public boolean isSuccess() {
        return this.statusCode < 300;
    }
}
