package com.myweb.bai6.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Book {
    private String title;
    private String author;
    private int pages;
    private String isbn;
    private String summary;

}
