package com.personal.books.controller;

import com.personal.books.model.Book;
import com.personal.books.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "books", description = "Books API")
public class BooksController {

    private BooksService booksService;

    @GetMapping("/books")
    @Operation(summary = "Get all books", description = "Retrieve all books from the personal collection")
    @ApiResponse(responseCode = "200", description = "List of books", 
                content = @Content(schema = @Schema(implementation = Book.class)))
    public ResponseEntity<List<Book>> getBooks() {
        var books = booksService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}