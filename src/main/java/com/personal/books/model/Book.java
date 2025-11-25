package com.personal.books.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Book entity representing a book in the personal collection")
public class Book {

    @Schema(description = "Unique identifier of the book", example = "1")
    private long id;

    @Schema(description = "Title of the book", example = "The Great Gatsby")
    private String title;

    @Schema(description = "Year the book was published", example = "1925")
    private int publicationYear;

    @Schema(description = "Year the book was read", example = "2023")
    private int readYear;

    @Schema(description = "Author of the book", example = "F. Scott Fitzgerald")
    private String author;

    @Schema(description = "Language of the book", example = "English")
    private String language;

    @Schema(description = "Format of the book", example = "PAPERBACK", allowableValues = {"PAPERBACK", "EBOOK"})
    private String format;

    @Schema(description = "Date when the book was finished", example = "2020-01-15")
    private LocalDate finishDate;

    @Schema(description = "Number of days it took to read the book", example = "14")
    private int readingTimeInDays;

}
