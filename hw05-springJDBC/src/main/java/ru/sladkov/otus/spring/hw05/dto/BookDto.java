package ru.sladkov.otus.spring.hw05.dto;

public record BookDto(Long id, String title, Long authorId, Long genreId) {

    public BookDto(String title, Long authorId, Long genreId) {
        this(null, title, authorId, genreId);
    }
}
