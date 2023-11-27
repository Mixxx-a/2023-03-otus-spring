package ru.sladkov.otus.spring.hw14.domain.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class BookMongo {
    @Id
    private String id;

    private String title;

    @DBRef
    private AuthorMongo author;

    @DBRef
    private GenreMongo genre;

    public BookMongo() {
    }

    public BookMongo(String id, String title, AuthorMongo author, GenreMongo genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public BookMongo(String title, AuthorMongo author, GenreMongo genre) {
        this(null, title, author, genre);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorMongo getAuthor() {
        return author;
    }

    public GenreMongo getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(AuthorMongo author) {
        this.author = author;
    }

    public void setGenre(GenreMongo genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "BookMongo[id=" + this.id
                + ", title=" + this.title
                + ", author=" + this.author
                + ", genre=" + this.genre + "]";
    }
}
