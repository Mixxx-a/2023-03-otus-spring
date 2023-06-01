package ru.sladkov.otus.spring.hw04.configs.impl;

import ru.sladkov.otus.spring.hw04.configs.IOServiceConfig;

import java.io.InputStream;
import java.io.OutputStream;

public record IOServiceStreamsConfig(InputStream inputStream, OutputStream outputStream)
        implements IOServiceConfig {
    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }
}
