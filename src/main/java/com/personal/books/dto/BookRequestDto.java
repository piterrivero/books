package com.personal.books.dto;

import com.personal.books.enums.Format;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Book request DTO for creating books")
public class BookRequestDto {

    @Schema(description = "Title of the book", example = "The Great Gatsby")
    private String title;

    @Schema(description = "Year the book was published", example = "1925")
    private int publicationYear;

    @Schema(description = "Author of the book", example = "F. Scott Fitzgerald")
    private String author;

    @Schema(description = "Language of the book", example = "English")
    private String language;

    @Schema(description = "Format of the book", example = "PAPERBACK")
    private Format format;
}