package ru.sladkov.otus.spring.hw08.dto;

public record BookDto(String id, String title, String authorId, String genreId) {

    public BookDto(String title, String authorId, String genreId) {
        this(null, title, authorId, genreId);
    }
}
