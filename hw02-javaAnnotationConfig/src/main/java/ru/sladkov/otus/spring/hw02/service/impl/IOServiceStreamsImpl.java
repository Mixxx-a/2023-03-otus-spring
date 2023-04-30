package ru.sladkov.otus.spring.hw02.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw02.service.IOService;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreamsImpl implements IOService {

    private final Scanner input;

    private final PrintStream out;

    public IOServiceStreamsImpl(@Value("#{T(System).in}") InputStream input,
                                @Value("#{T(System).out}") OutputStream output) {
        this.input = new Scanner(input);
        this.out = new PrintStream(output);
    }

    @Override
    public void print(String line) {
        out.print(line);
    }

    @Override
    public void println(String line) {
        out.println(line);
    }

    @Override
    public void println() {
        out.println();
    }

    @Override
    public String scanNext() {
        return input.next();
    }
}
