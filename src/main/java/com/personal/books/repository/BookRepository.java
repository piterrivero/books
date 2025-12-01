package com.personal.books.repository;

import com.personal.books.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    
    @Value("${aws.dynamodb.table-name}")
    private String tableName;
    
    public BookRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    private DynamoDbTable<Book> getTable() {
        return dynamoDbEnhancedClient.table(tableName, TableSchema.fromBean(Book.class));
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