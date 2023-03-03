package com.application.bookstore.domain.response;

import com.application.bookstore.domain.dto.Book;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookResponse {
    private Integer statusCode;
    private String message;
    private boolean isSuccess;
    private Book data;

    public boolean isSuccess() {
        return this.statusCode < 300;
    }

}
