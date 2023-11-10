package ru.sladkov.otus.spring.hw05.service;

import ru.sladkov.otus.spring.hw05.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(long id);

    List<Author> getAllAuthors();
}
