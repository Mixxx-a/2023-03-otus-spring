package ru.sladkov.otus.spring.hw04.configs;

import java.io.InputStream;
import java.io.OutputStream;

public record IOServiceConfig(InputStream inputStream, OutputStream outputStream) {
}
