package com.personal.books.service;

import com.personal.books.enums.Format;
import com.personal.books.model.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BooksService {

    public List<Book> getAllBooks() {
        return Arrays.asList(
            Book.builder().id(1L).title("The Great Gatsby").author("F. Scott Fitzgerald").publicationYear(1925).readYear(2023).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(2L).title("To Kill a Mockingbird").author("Harper Lee").publicationYear(1960).readYear(2022).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(3L).title("1984").author("George Orwell").publicationYear(1949).readYear(2023).language("English").Format(Format.EBOOK.name()).build(),
            Book.builder().id(4L).title("Pride and Prejudice").author("Jane Austen").publicationYear(1813).readYear(2021).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(5L).title("The Catcher in the Rye").author("J.D. Salinger").publicationYear(1951).readYear(2022).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(6L).title("Lord of the Flies").author("William Golding").publicationYear(1954).readYear(2023).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(7L).title("The Lord of the Rings").author("J.R.R. Tolkien").publicationYear(1954).readYear(2021).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(8L).title("Harry Potter and the Sorcerer's Stone").author("J.K. Rowling").publicationYear(1997).readYear(2020).language("English").Format(Format.EBOOK.name()).build(),
            Book.builder().id(9L).title("The Hobbit").author("J.R.R. Tolkien").publicationYear(1937).readYear(2022).language("English").Format(Format.PAPERBACK.name()).build(),
            Book.builder().id(10L).title("Brave New World").author("Aldous Huxley").publicationYear(1932).readYear(2023).language("English").Format(Format.EBOOK.name()).build()
        );
    }
}