package ru.sladkov.otus.spring.hw08.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private String text;

    @DBRef
    private Book book;

    public Comment() {
    }

    public Comment(String id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    public Comment(String text, Book book) {
        this(null, text, book);
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Book getBook() {
        return book;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment[id=" + this.id + ", text=" + this.text + ", bookId=" + this.book.getId() + "]";
    }
}
