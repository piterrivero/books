package com.personal.books.controller;

import com.personal.books.dto.BookDto;
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
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = booksService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieve a specific book by its ID")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = booksService.getBookById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Add a new book to the collection")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBook = booksService.createBook(bookDto);
        return ResponseEntity.ok(createdBook);
    }

    @PostMapping("/upload-csv")
    @Operation(summary = "Upload books from CSV", description = "Bulk upload books from CSV file")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") org.springframework.web.multipart.MultipartFile file) {
        try {
            int count = booksService.uploadBooksFromCsv(file);
            return ResponseEntity.ok("Successfully uploaded " + count + " books");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading CSV: " + e.getMessage());
        }
    }

    @GetMapping("/count")
    @Operation(summary = "Get books count", description = "Get the total number of books in the collection")
    public ResponseEntity<Long> getBooksCount() {
        Long count = booksService.getBooksCount();
        return ResponseEntity.ok(count);
    }


}