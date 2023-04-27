package ru.sladkov.otus.spring.hw02.exception;

public class ScanException extends RuntimeException {

    public ScanException(String message, Throwable cause) {
        super(message, cause);
    }
}
