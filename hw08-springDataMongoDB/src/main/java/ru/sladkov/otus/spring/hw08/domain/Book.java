package ru.sladkov.otus.spring.hw08.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    @Id
    private String id;

    private String title;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    public Book() {
    }

    public Book(String id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, Author author, Genre genre) {
        this(null, title, author, genre);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book[id=" + this.id
                + ", title=" + this.title
                + ", author=" + this.author
                + ", genre=" + this.genre + "]";
    }
}
