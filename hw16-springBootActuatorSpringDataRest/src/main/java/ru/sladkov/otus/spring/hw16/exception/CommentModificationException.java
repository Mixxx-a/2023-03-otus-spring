package ru.sladkov.otus.spring.hw16.exception;

public class CommentModificationException extends RuntimeException {

    public CommentModificationException(String message) {
        super(message);
    }
}
