package com.personal.books.controller;

import com.personal.books.dto.BookRequestDto;
import com.personal.books.dto.BookResponseDto;
import com.personal.books.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Books management API")
public class BooksController {

    private final BooksService booksService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve all books from the collection")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = booksService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieve a specific book by its ID")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id) {
        BookResponseDto book = booksService.getBookById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Add a new book to the collection")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookDto) {
        BookResponseDto createdBook = booksService.createBook(bookDto);
        return ResponseEntity.ok(createdBook);
    }

    @GetMapping("/count")
    @Operation(summary = "Get books count", description = "Get the total number of books in the collection")
    public ResponseEntity<Long> getBooksCount() {
        Long count = booksService.getBooksCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/search/title")
    @Operation(summary = "Get books by title", description = "Search books by title using partial matching")
    public ResponseEntity<List<BookResponseDto>> getBooksByTitle(@RequestParam String title) {
        List<BookResponseDto> books = booksService.getBooksByTitle(title);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/author")
    @Operation(summary = "Get books by author", description = "Search books by author using partial matching")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(@RequestParam String author) {
        List<BookResponseDto> books = booksService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }


}