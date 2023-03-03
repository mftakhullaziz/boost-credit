package com.application.bookstore.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private String isbn;
}
