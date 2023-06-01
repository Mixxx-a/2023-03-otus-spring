package ru.sladkov.otus.spring.hw04.service;

public interface LocalizationService {
    String getLocalizedMessage(String code, Object... args);
}
