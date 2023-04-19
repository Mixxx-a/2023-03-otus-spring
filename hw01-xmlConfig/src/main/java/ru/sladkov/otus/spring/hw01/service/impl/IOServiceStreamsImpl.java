package ru.sladkov.otus.spring.hw01.service.impl;

import ru.sladkov.otus.spring.hw01.service.IOService;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceStreamsImpl implements IOService {

    private final Scanner input;

    private final PrintStream out;

    public IOServiceStreamsImpl(InputStream input, OutputStream output) {
        this.input = new Scanner(input);
        this.out = new PrintStream(output);
    }

    @Override
    public void print(String string) {
        out.print(string);
    }

    @Override
    public void println(String string) {
        out.println(string);
    }
}
