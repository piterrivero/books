package com.personal.books.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private long id;

    private String title;

    private int publicationYear;

    private int readYear;

    private String author;

    private String language;

    private String Format;

}
