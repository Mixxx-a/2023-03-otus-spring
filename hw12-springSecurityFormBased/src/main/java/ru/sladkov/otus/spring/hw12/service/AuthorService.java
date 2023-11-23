package ru.sladkov.otus.spring.hw12.service;

import ru.sladkov.otus.spring.hw12.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
