package ru.sladkov.otus.spring.hw17.exception;

public class CommentModificationException extends RuntimeException {

    public CommentModificationException(String message) {
        super(message);
    }
}
