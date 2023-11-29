package ru.sladkov.otus.spring.hw11.dto;

public record CommentDto(String id, String text, String bookId) {

    public CommentDto(String text, String bookId) {
        this(null, text, bookId);
    }
}
