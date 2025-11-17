package com.personal.books.controller;

import com.personal.books.model.Book;
import com.personal.books.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BooksController {

    private BooksService booksService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        var books = booksService.getAllBooks();
        return ResponseEntity.ok().body(books);
    }
}