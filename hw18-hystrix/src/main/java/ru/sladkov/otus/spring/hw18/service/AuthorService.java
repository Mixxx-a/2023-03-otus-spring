package ru.sladkov.otus.spring.hw18.service;

import ru.sladkov.otus.spring.hw18.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
