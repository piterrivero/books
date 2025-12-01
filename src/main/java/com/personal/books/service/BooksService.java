package com.personal.books.service;

import com.personal.books.dto.BookRequestDto;
import com.personal.books.dto.BookResponseDto;
import com.personal.books.mapper.BookMapper;
import com.personal.books.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BooksService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto getBookById(Long id) {
        com.personal.books.model.Book book = bookRepository.findById(id);
        return book != null ? bookMapper.toResponseDto(book) : null;
    }

    public BookResponseDto createBook(BookRequestDto bookDto) {
        com.personal.books.model.Book book = bookMapper.toModel(bookDto);
        book.setId(getNextId());
        LocalDate currentDate = LocalDate.now();
        book.setFinishDate(currentDate);
        book.setReadYear(currentDate.getYear());

        Optional<com.personal.books.model.Book> lastBook = bookRepository.findAll().stream()
                .max((b1, b2) -> Long.compare(b1.getId(), b2.getId()));
        
        if (lastBook.isPresent() && lastBook.get().getFinishDate() != null) {
            long daysBetween = ChronoUnit.DAYS.between(lastBook.get().getFinishDate(), currentDate);
            book.setReadingTimeInDays((int) daysBetween);
        }
        
        com.personal.books.model.Book savedBook = bookRepository.save(book);
        return bookMapper.toResponseDto(savedBook);
    }

    private Long getNextId() {
        return bookRepository.findAll().stream()
                .mapToLong(com.personal.books.model.Book::getId)
                .max()
                .orElse(0L) + 1;
    }



    public Long getBooksCount() {
        return (long) bookRepository.findAll().size();
    }

    public List<BookResponseDto> getBooksByTitle(String title) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<BookResponseDto> getBooksByAuthor(String author) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }




}