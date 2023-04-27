package ru.sladkov.otus.spring.hw02.exception;

public class PrintException extends RuntimeException {

    public PrintException(String message, Throwable cause) {
        super(message, cause);
    }
}
