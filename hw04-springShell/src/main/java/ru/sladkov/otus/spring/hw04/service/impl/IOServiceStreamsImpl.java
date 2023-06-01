package ru.sladkov.otus.spring.hw04.service.impl;

import org.springframework.stereotype.Service;
import ru.sladkov.otus.spring.hw04.configs.IOServiceConfig;
import ru.sladkov.otus.spring.hw04.service.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreamsImpl implements IOService {

    private final Scanner input;

    private final PrintStream out;

    public IOServiceStreamsImpl(IOServiceConfig ioServiceConfig) {
        this.input = new Scanner(ioServiceConfig.inputStream());
        this.out = new PrintStream(ioServiceConfig.outputStream());
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
