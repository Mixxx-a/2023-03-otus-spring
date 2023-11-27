package ru.sladkov.otus.spring.hw14.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class CommentMongo {
    @Id
    private String id;

    private String text;

    @DBRef
    private BookMongo book;

    public CommentMongo() {
    }

    public CommentMongo(String id, String text, BookMongo book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    public CommentMongo(String text, BookMongo book) {
        this(null, text, book);
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public BookMongo getBook() {
        return book;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CommentMongo[id=" + this.id + ", text=" + this.text + ", bookId=" + this.book.getId() + "]";
    }
}
