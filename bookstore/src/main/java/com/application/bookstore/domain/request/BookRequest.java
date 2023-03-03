package com.application.bookstore.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
//@Builder
@Setter
@Getter
public class BookRequest {
    private String title;
    private String author;
    private Double price;
    private String isbn;
}
