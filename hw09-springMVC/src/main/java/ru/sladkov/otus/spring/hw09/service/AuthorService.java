package ru.sladkov.otus.spring.hw09.service;

import ru.sladkov.otus.spring.hw09.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
