package com.personal.books.controller;

import com.personal.books.api.BooksApi;

import com.personal.books.dto.Book;
import com.personal.books.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class BooksController implements BooksApi {

    private BooksService booksService;

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        var books = booksService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}