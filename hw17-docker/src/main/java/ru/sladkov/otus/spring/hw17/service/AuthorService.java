package ru.sladkov.otus.spring.hw17.service;

import ru.sladkov.otus.spring.hw17.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
