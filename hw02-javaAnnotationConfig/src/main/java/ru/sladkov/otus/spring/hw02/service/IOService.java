package ru.sladkov.otus.spring.hw02.service;

import ru.sladkov.otus.spring.hw02.exception.PrintException;
import ru.sladkov.otus.spring.hw02.exception.ScanException;

public interface IOService {
    void print(String string) throws PrintException;

    void println(String string) throws PrintException;

    void println() throws PrintException;

    String scanNext() throws ScanException;
}
