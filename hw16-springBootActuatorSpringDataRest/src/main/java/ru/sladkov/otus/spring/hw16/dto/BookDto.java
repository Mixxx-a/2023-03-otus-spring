package ru.sladkov.otus.spring.hw16.dto;

import ru.sladkov.otus.spring.hw16.domain.Author;
import ru.sladkov.otus.spring.hw16.domain.Book;
import ru.sladkov.otus.spring.hw16.domain.Genre;

public class BookDto {

    private Long id;

    private String title;

    private Long authorId;

    private String authorInfo;

    private Long genreId;

    private String genreInfo;

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public Long getGenreId() {
        return genreId;
    }

    public String getGenreInfo() {
        return genreInfo;
    }

    public BookDto setId(Long id) {
        this.id = id;
        return this;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public BookDto setGenreId(Long genreId) {
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
