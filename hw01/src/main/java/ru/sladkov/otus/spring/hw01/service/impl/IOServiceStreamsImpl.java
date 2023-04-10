package ru.sladkov.otus.spring.hw01.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sladkov.otus.spring.hw01.service.IOService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOServiceStreamsImpl implements IOService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IOService.class);

    private final InputStream input;

    private final OutputStream out;

    public IOServiceStreamsImpl(InputStream input, OutputStream output) {
        this.input = input;
        this.out = output;
    }

    @Override
    public void print(String string) {
        try {
            out.write(string.getBytes());
            out.flush();
        } catch (IOException e) {
            LOGGER.error("Can't print " + string);
        }
    }

    @Override
    public void println(String string) {
        this.print(string + "\n");
    }
}
