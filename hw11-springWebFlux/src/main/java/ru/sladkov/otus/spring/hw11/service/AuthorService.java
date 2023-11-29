package ru.sladkov.otus.spring.hw11.service;

import ru.sladkov.otus.spring.hw11.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getById(long id);

    List<Author> getAll();
}
