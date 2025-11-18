package com.personal.books.service;

import com.personal.books.enums.Format;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksService {

    public List<com.personal.books.dto.Book> getAllBooks() {
        return getModelBooks().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    private List<com.personal.books.model.Book> getModelBooks() {
        return Arrays.asList(
            com.personal.books.model.Book.builder().id(1L).title("The Great Gatsby").author("F. Scott Fitzgerald").publicationYear(1925).readYear(2023).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2020, 1, 15)).build(),
            com.personal.books.model.Book.builder().id(2L).title("To Kill a Mockingbird").author("Harper Lee").publicationYear(1960).readYear(2022).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2020, 3, 22)).build(),
            com.personal.books.model.Book.builder().id(3L).title("1984").author("George Orwell").publicationYear(1949).readYear(2023).language("English").Format(Format.EBOOK.name()).finishDate(LocalDate.of(2020, 5, 10)).build(),
            com.personal.books.model.Book.builder().id(4L).title("Pride and Prejudice").author("Jane Austen").publicationYear(1813).readYear(2021).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2020, 7, 8)).build(),
            com.personal.books.model.Book.builder().id(5L).title("The Catcher in the Rye").author("J.D. Salinger").publicationYear(1951).readYear(2022).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2020, 9, 14)).build(),
            com.personal.books.model.Book.builder().id(6L).title("Lord of the Flies").author("William Golding").publicationYear(1954).readYear(2023).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2020, 11, 25)).build(),
            com.personal.books.model.Book.builder().id(7L).title("The Lord of the Rings").author("J.R.R. Tolkien").publicationYear(1954).readYear(2021).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2021, 2, 18)).build(),
            com.personal.books.model.Book.builder().id(8L).title("Harry Potter and the Sorcerer's Stone").author("J.K. Rowling").publicationYear(1997).readYear(2020).language("English").Format(Format.EBOOK.name()).finishDate(LocalDate.of(2021, 4, 30)).build(),
            com.personal.books.model.Book.builder().id(9L).title("The Hobbit").author("J.R.R. Tolkien").publicationYear(1937).readYear(2022).language("English").Format(Format.PAPERBACK.name()).finishDate(LocalDate.of(2021, 7, 12)).build(),
            com.personal.books.model.Book.builder().id(10L).title("Brave New World").author("Aldous Huxley").publicationYear(1932).readYear(2023).language("English").Format(Format.EBOOK.name()).finishDate(LocalDate.of(2021, 10, 5)).build()
        );
    }
    
    private com.personal.books.dto.Book mapToDto(com.personal.books.model.Book modelBook) {
        return new com.personal.books.dto.Book()
                .id(modelBook.getId())
                .title(modelBook.getTitle())
                .author(modelBook.getAuthor())
                .publicationYear(modelBook.getPublicationYear())
                .readYear(modelBook.getReadYear())
                .language(modelBook.getLanguage())
                .format(com.personal.books.dto.Book.FormatEnum.fromValue(modelBook.getFormat()))
                .finishDate(modelBook.getFinishDate())
                .readingTimeInDays(modelBook.getReadingTimeInDays());
    }
}