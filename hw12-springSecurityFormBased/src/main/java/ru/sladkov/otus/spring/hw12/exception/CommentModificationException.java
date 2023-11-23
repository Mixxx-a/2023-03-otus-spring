package ru.sladkov.otus.spring.hw12.exception;

public class CommentModificationException extends RuntimeException {

    public CommentModificationException(String message) {
        super(message);
    }
}
