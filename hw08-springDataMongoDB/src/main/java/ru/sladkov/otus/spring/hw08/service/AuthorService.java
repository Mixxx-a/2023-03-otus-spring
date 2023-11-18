package ru.sladkov.otus.spring.hw08.service;

import ru.sladkov.otus.spring.hw08.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
