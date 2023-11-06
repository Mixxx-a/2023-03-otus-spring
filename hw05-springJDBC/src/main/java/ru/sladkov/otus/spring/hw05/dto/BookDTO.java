package ru.sladkov.otus.spring.hw05.dto;

public record BookDTO(Long id, String title, Long authorId, Long genreId) {

    public BookDTO(String title, Long authorId, Long genreId) {
        this(null, title, authorId, genreId);
    }
}
