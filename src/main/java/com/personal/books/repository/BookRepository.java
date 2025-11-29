package com.personal.books.repository;

import com.personal.books.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class BookRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    private DynamoDbTable<Book> getTable() {
        return dynamoDbEnhancedClient.table("books", TableSchema.fromBean(Book.class));
    }

    public List<Book> findAll() {
        return getTable().scan().items().stream().collect(Collectors.toList());
    }

    public Book findById(Long id) {
        return getTable().getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public Book save(Book book) {
        getTable().putItem(book);
        return book;
    }


}