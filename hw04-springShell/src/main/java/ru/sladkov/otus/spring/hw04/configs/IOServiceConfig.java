package ru.sladkov.otus.spring.hw04.configs;

import java.io.InputStream;
import java.io.OutputStream;

public interface IOServiceConfig {
    InputStream getInputStream();

    OutputStream getOutputStream();
}
