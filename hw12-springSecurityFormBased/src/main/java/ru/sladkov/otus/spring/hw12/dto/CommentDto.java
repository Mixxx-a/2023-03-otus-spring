package ru.sladkov.otus.spring.hw12.dto;

public record CommentDto(Long id, String text, Long bookId) {

    public CommentDto(String text, Long bookId) {
        this(null, text, bookId);
    }
}