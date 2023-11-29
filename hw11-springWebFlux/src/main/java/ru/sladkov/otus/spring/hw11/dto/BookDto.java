package ru.sladkov.otus.spring.hw11.dto;

import ru.sladkov.otus.spring.hw11.domain.Author;
import ru.sladkov.otus.spring.hw11.domain.Book;
import ru.sladkov.otus.spring.hw11.domain.Genre;

public class BookDto {

    private String id;

    private String title;

    private String authorId;

    private String authorInfo;

    private String genreId;

    private String genreInfo;

    public BookDto() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public String getGenreId() {
        return genreId;
    }

    public String getGenreInfo() {
        return genreInfo;
    }

    public BookDto setId(String id) {
        this.id = id;
        return this;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDto setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public BookDto setGenreId(String genreId) {
        this.genreId = genreId;
        return this;
    }

    public BookDto setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
        return this;
    }

    public BookDto setGenreInfo(String genreInfo) {
        this.genreInfo = genreInfo;
        return this;
    }

    public static BookDto fromDomainObject(Book book) {
        Author author = book.getAuthor();
        Genre genre = book.getGenre();
        return new BookDto()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setAuthorId(author.getId())
                .setAuthorInfo(author.toString())
                .setGenreId(genre.getId())
                .setGenreInfo(genre.toString());
    }
}
