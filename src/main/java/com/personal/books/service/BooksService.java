package com.personal.books.service;

import com.personal.books.dto.BookDto;
import com.personal.books.mapper.BookMapper;
import com.personal.books.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BooksService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getBookById(Long id) {
        com.personal.books.model.Book book = bookRepository.findById(id);
        return book != null ? bookMapper.toDto(book) : null;
    }

    public BookDto createBook(BookDto bookDto) {
        com.personal.books.model.Book book = bookMapper.toModel(bookDto);
        book.setId(getNextId());
        com.personal.books.model.Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    private Long getNextId() {
        return bookRepository.findAll().stream()
                .mapToLong(com.personal.books.model.Book::getId)
                .max()
                .orElse(0L) + 1;
    }

    public int uploadBooksFromCsv(org.springframework.web.multipart.MultipartFile file) throws Exception {
        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(file.getInputStream()));
        String line;
        int count = 0;
        int lineNumber = 0;
        
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            if (line.trim().isEmpty()) {
                System.out.println("Skipping empty line " + lineNumber);
                continue;
            }
            
            try {
                String[] fields = parseCsvLine(line);
                System.out.println("Line " + lineNumber + ": " + fields.length + " fields - " + line);
                
                if (fields.length >= 6) {
                    com.personal.books.model.Book book = com.personal.books.model.Book.builder()
                        .id(getNextId())
                        .title(fields[0].trim())
                        .publicationYear(Integer.parseInt(fields[1].trim()))
                        .readYear(Integer.parseInt(fields[2].trim()))
                        .author(fields[3].trim())
                        .language(fields[4].trim())
                        .format(fields[5].trim())
                        .build();
                    
                    bookRepository.save(book);
                    count++;
                    System.out.println("Successfully saved book: " + fields[0].trim());
                } else {
                    System.out.println("Insufficient fields (" + fields.length + ") in line " + lineNumber + ": " + line);
                }
            } catch (Exception e) {
                System.out.println("Error processing line " + lineNumber + ": " + line + " - " + e.getMessage());
                e.printStackTrace();
            }
        }
        reader.close();
        System.out.println("Total lines processed: " + lineNumber + ", Books saved: " + count);
        return count;
    }
    
    private String[] parseCsvLine(String line) {
        java.util.List<String> fields = new java.util.ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ';' && !inQuotes) {
                fields.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        fields.add(field.toString());
        return fields.toArray(new String[0]);
    }

    public Long getBooksCount() {
        return (long) bookRepository.findAll().size();
    }




}